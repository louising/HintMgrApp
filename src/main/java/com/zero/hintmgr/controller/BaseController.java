package com.zero.hintmgr.controller;

import static com.zero.hintmgr.constants.Constants.ERR_NOT_LOGIN;
import static com.zero.hintmgr.constants.Constants.ERR_USER_INACTIVE;
import static com.zero.hintmgr.constants.Constants.ERR_USER_NO_PROJECT;
import static com.zero.hintmgr.constants.Constants.SESSION_ID;
import static com.zero.hintmgr.constants.Constants.STATUS_ERR;
import static com.zero.hintmgr.util.BaseUtil.checkIsTrue;
import static com.zero.hintmgr.util.BaseUtil.checkNotNull;
import static com.zero.hintmgr.util.BaseUtil.getUserIdFromSessionId;
import static com.zero.hintmgr.util.BaseUtil.getUserInfoFromSessionId;
import static com.zero.hintmgr.util.I18nUtil.getMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zero.core.domain.AccessVO;
import com.zero.core.domain.ResponseVO;
import com.zero.core.tasks.AppCallable;
import com.zero.core.tasks.AppRunnable;
import com.zero.core.tasks.AppTask;
import com.zero.hintmgr.ServiceException;
import com.zero.hintmgr.dao.DummyDao;
import com.zero.hintmgr.service.DummyService;
import com.zero.hintmgr.util.BaseUtil;
import com.zero.hintmgr.util.HttpUtil;
import com.zero.hintmgr.vo.UserInfoBean;
import com.zero.hintmgr.vo.UserSession;

public abstract class BaseController {
    static final Logger log = LoggerFactory.getLogger(BaseController.class);

    static final Map<String, String> userTokenMap = new ConcurrentHashMap<>(); //Map(userID, Token)

    @Autowired
    private DummyService dummyService;

    @Autowired
    protected DummyDao dao;    

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    protected void checkUserLogin() throws ServiceException {
        String sessionId = HttpUtil.getHeader(SESSION_ID);  
        //TODO Delete testing code
        if ("1234".equals(sessionId)) {
            return;
        }
        if ("root".equals(BaseUtil.getUserIdFromSessionId())) {
            return;
        }
        
        //1) Check session is valid
        checkNotNull(sessionId, ERR_NOT_LOGIN);        
        UserSession us = getUserInfoFromSessionId();
        String userId = us.getUserId();
        checkNotNull(userId, ERR_NOT_LOGIN);
        
        //2) Check user is active
        Map<String, Object> userMap = null;
        if ("ADMIN".equals(us.getRolePosition())) {
            userMap = dao.getSysUser(userId);
        } else {
            userMap = dao.getUserByUserId(userId);
        }
        checkIsTrue("1".equals(userMap.get("user_status")), ERR_USER_INACTIVE);
        
        //3) Check User has project if user is not ADMIN
        checkUserHasProject();
    }
    
    protected void checkAdminLogin() {
        String sessionId = HttpUtil.getHeader(SESSION_ID); 
        checkNotNull(sessionId, ERR_NOT_LOGIN);
        
        String userId = getUserIdFromSessionId();
        checkNotNull(userId, ERR_NOT_LOGIN);
    }
    
    protected String getUserId() {
        String userId = getUserIdFromSessionId();
        return userId;
    }

    protected ResponseVO process(AppRunnable task) {
        return processTask(task);
    }
    
    protected ResponseVO process(AppCallable task) {
        return processTask(task);
    }    
    
    private ResponseVO processTask(AppTask task) {
        ResponseVO result = new ResponseVO();
        try {
            checkUserLogin();
           
            logVisit();            
            if (task instanceof AppCallable) {
                Object value = ((AppCallable) task).run();
                result.setData(value);
            } else {
                ((AppRunnable) task).run();
            }

        } catch (ServiceException e) {
            log.error(e.getErrCode());
            result.setStatusCode(e.getErrCode());
            result.setStatusMsg(getMessage(e.getErrCode(), e.getArgs()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            result.setStatusCode(STATUS_ERR);
            result.setStatusMsg(getMessage(STATUS_ERR));
        }

        return result;
    }
    
    private void logVisit() {
        StringBuilder sb = new StringBuilder("\n#### ");
        HttpServletRequest req = HttpUtil.getRequest();
        sb.append(req.getRemoteHost() + " visit " + req.getRequestURL()); //URL full url, URI just path without host:port
        System.out.println();
        log.info(sb.toString());
        HttpUtil.prtRequest();
    }

    protected UserInfoBean getUserInfoBean() {
        UserInfoBean uiBean = new UserInfoBean("default_user_01");
        //HttpUtil.getRequest().setAttribute(SESSION_USER_KEY, new DummyVO(100, "Louis"));

        HttpServletRequest request = HttpUtil.getRequest();
        if (request != null) {
            //HttpUtil.prtRequest(request);

            /*
            AccessVO accessVO = getAccessVO(request);
            HttpSession session = (request).getSession();
            uiBean = (UserInfoBean) session.getAttribute(SsoConstants.SESSION_USER_INFO_KEY);
            saveAccessRecord(accessVO);
            */
        }
        return uiBean;
    }

    void saveAccessRecord(AccessVO vo) {
        executorService.execute(new Runnable() {
            public void run() {
                try {
                    dummyService.addAccessRecord(vo);
                } catch (ServiceException e) {
                    log.error(e.getMessage());
                }
            }
        });
    }

    AccessVO getAccessVO(HttpServletRequest req) {
        AccessVO vo = new AccessVO();

        vo.setRequestURI(req.getRequestURI());
        vo.setServletPath(req.getServletPath());
        vo.setContextPath(req.getContextPath());

        vo.setMethod(req.getMethod());
        vo.setContentType(req.getContentType());

        vo.setLocalAddress(req.getLocalAddr());
        vo.setLocalName(req.getLocalName());
        vo.setLocalPort(req.getLocalPort());

        vo.setRemoteAddress(req.getRemoteAddr());
        vo.setRemoteHost(req.getRemoteHost());
        vo.setRemotePort(req.getRemotePort());

        return vo;
    }
    
    
    protected void checkUserHasProject() {
        UserSession us = BaseUtil.getUserInfoFromSessionId();
        
        String rolePosition = us.getRolePosition();
        if ("ADMIN".equals(rolePosition)) {
            return;
        } else {
            int projectCount = getUserProjectCount(dao);
            if (projectCount == 0) {
                throw new ServiceException(ERR_USER_NO_PROJECT);
            }
        }
    }
    
    protected int getUserProjectCount(DummyDao dao) {
        UserSession us = BaseUtil.getUserInfoFromSessionId();        
        String rolePosition = us.getRolePosition(), userId = us.getUserId();

        int projectCount = 0;
        log.info("RolePosition: {}", rolePosition);
        if ("1".equals(rolePosition) ) {
            projectCount = dao.getAreaAdminProjectCount(userId);
        } else if ("2".equals(rolePosition)) {
            projectCount = dao.getProjectAdminProjectCount(userId);
        } else {
            Map<String, Object> userMap = dao.getUserByUserId(userId);
            if (userMap.containsKey("project_id")) {
                Object mProjectId = userMap.get("project_id");
                log.info("m ProjectId {}", mProjectId);
                if (mProjectId != null && !mProjectId.equals("") ) {
                    projectCount = 1;
                }               
            }       
        }
        log.info("Project Count {}", projectCount);
        return projectCount;
    }
}
