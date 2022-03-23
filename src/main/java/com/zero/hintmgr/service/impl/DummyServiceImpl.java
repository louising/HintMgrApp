package com.zero.hintmgr.service.impl;

import static com.zero.hintmgr.constants.Constants.*;
import static com.zero.hintmgr.constants.Constants.DIR_CURR;
import static com.zero.hintmgr.constants.Constants.ERR_INVALID_ADMIN_PWD;
import static com.zero.hintmgr.constants.Constants.ERR_INVALID_INVITE_CODE;
import static com.zero.hintmgr.constants.Constants.ERR_REGISTER_FIELD_NULL;
import static com.zero.hintmgr.constants.Constants.ERR_USER_INACTIVE;
import static com.zero.hintmgr.constants.Constants.ERR_USER_NOT_REGISTERED;
import static com.zero.hintmgr.constants.Constants.ROOT_AUTHORIES;
import static com.zero.hintmgr.constants.Constants.SESSION_ID;
import static com.zero.hintmgr.constants.I18NConstants.ERR_FAIL;
import static com.zero.hintmgr.constants.I18NConstants.ERR_UPLOAD;
import static com.zero.hintmgr.util.BaseUtil.checkNotNull;
import static com.zero.hintmgr.util.BaseUtil.getSessionId;
import static com.zero.hintmgr.util.BaseUtil.getTmpDir;
import static com.zero.hintmgr.util.BaseUtil.writeFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zero.core.domain.AccessVO;
import com.zero.core.domain.PageResultVO;
import com.zero.core.domain.PageVO;
import com.zero.core.domain.ResponseVO;
import com.zero.hintmgr.ServiceException;
import com.zero.hintmgr.conf.AppConf;
import com.zero.hintmgr.conf.ConfigValueConf;
import com.zero.hintmgr.constants.Constants;
import com.zero.hintmgr.dao.DummyDao;
import com.zero.hintmgr.service.DummyService;
import com.zero.hintmgr.util.BaseUtil;
import com.zero.hintmgr.util.FileUtil;
import com.zero.hintmgr.util.HttpUtil;
import com.zero.hintmgr.util.IoUtil;
import com.zero.hintmgr.util.SecurityUtil;
import com.zero.hintmgr.util.ZipUtils;
import com.zero.hintmgr.vo.DummyVO;
import com.zero.hintmgr.vo.UserInfoBean;
import com.zero.hintmgr.vo.UserSession;

/**
 * Dummy service implementation
 * 
 * @author Louisling
 * @version 2018-07-01
 */
@Component
public class DummyServiceImpl extends BaseServiceImpl implements DummyService {
    Logger log = LoggerFactory.getLogger(DummyServiceImpl.class);

    final static String LOG_DIR = "/hintmgr/logs/"; //refer to logback.xml
    final static String LOG_ZIP_FULL_FILE_NAME = LOG_DIR + "logs.zip";

    static final List<String> supportedDocTypes = new ArrayList<>();

    @Autowired
    private ConfigValueConf configVO;

    @Autowired
    private AppConf appConf;
    
    @Autowired
    private DummyDao dao;
    
    @Value("${appConf.rootPwd}")
    private String rootPwd;
    
    @Value("${appConf.appId}")
    private String appId;
    
    @Value("${appConf.appSecret}")
    private String appSecret;
    
    @Autowired
    private AppUserService appUserService;
    
    
    //Hint@376#  4Hrsokx0xCGDwfyLsy6zJQ==
    //static String APP_ID = "wxb84b8cf1b6b44823";
    //static String APP_SECRET = "1b47bc0ef4a4360734c7b859618129bc";
    //static String URL_WX_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APP_ID + "&secret=" + APP_SECRET + "&grant_type=authorization_code";
    private String URL_WX_SESSION;
    private String ROOT_USER = "root";
    private String ROOT_PWD;
    
    static {
        supportedDocTypes.add("zip");
    }
    
    @PostConstruct
    void init() {
        URL_WX_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&grant_type=authorization_code";
        try {
            ROOT_PWD = rootPwd;
            log.info("Root Pwd: {}", ROOT_PWD);
            log.info("Root Ori: {}", SecurityUtil.decode(ROOT_PWD)); // Hint@376#
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResponseVO register(UserInfoBean u) {
        ResponseVO result = new ResponseVO();
        Map<String, String> dataMap = new HashMap<>();
        result.setData(dataMap);
        
        //1) InviteCode is null
        log.info("Register: " + u);
        String code = u.getCode();
        String userName = u.getUserName();
        String tel = u.getTel();
        String inviteCode = u.getInviteCode();
        checkNotNull(ERR_REGISTER_FIELD_NULL, code, userName, tel, inviteCode);
        
        //2) inviteCode is invalid
        Map<String, Object> inviteCodeMap = dao.getInviteCode(inviteCode);
        if (inviteCodeMap == null) {
            dataMap.put(KEY_USER_STATUS, ERR_USER_STATUS_7_INVALID_CODE); //无效的邀请码
            return result;
        } else {
            String inviteCodeStatus = inviteCodeMap.get("code_status") + "";
            if ("1".equals(inviteCodeStatus)) {
                dataMap.put(KEY_USER_STATUS, ERR_USER_STATUS_8_INVITECODE_USED); 
                return result;
            } else if ("2".equals(inviteCodeStatus)) {
                dataMap.put(KEY_USER_STATUS, ERR_USER_STATUS_9_INVITECODE_DISCARDED); 
                return result;
            }
        }

        //1) To get openId by wx(code, appId,appSecret)
        String url = URL_WX_SESSION + "&js_code=" + code;
        //{"errcode":40029,"errmsg":"invalid code, rid: 617833f5-4d39a245-36d3a896"}
        //{"errcode":40013,"errmsg":"invalid appid, rid: 617834e0-2c745b02-3446b1eb"} 
        //{"errcode":40125,"errmsg":"invalid appsecret, rid: 61783524-6e9fd6bc-59d4a1b8"} 
        String res = HttpUtil.sendGet(url, null);
        log.info("WX Session Result: " + res);
        JSONObject obj = JSONObject.parseObject(res);
        String errCode = obj.getString("errcode");      
        //40163 code been used
        if ("40029".equals(errCode) || "40013".equals(errCode) || "40125".equals(errCode) || "40163".equals(errCode)) {
            result.setStatusCode(obj.getString("errmsg"));
            return result;
        }
        
        String openId = obj.getString("openid");
        Map<String, Object> mUser = dao.getUserByOpenId(openId);
        if (mUser != null) {
            dataMap.put(KEY_USER_STATUS, ERR_USER_STATUS_10_OPENID_REGISTED);
            return result;
        }
        
        String userId = BaseUtil.getPkCode();
        String rolePosition = inviteCodeMap.get("role_position") + "";
        String sessionId = getSessionId(userId, rolePosition);
        
        //2) DB: Add (openId, userName, tel)
        String roleId = inviteCodeMap.get("role_id").toString();
        String projectId = inviteCodeMap.get("project_id").toString();
        Map<String, Object> roleMap = dao.getRole(roleId);
        
        String objectName = appUserService.saveUrlToOss(u.getAvatarUrl());
        
        Map<String, String> userMap = new HashMap<>();
        userMap.put("user_id", userId);
        userMap.put("open_id", openId);
        userMap.put("user_name", userName);
        userMap.put("tel", tel);
        userMap.put("role_id", roleId);
        userMap.put("project_id", projectId); //Current project
        userMap.put("invite_code", inviteCode);
        userMap.put("nickName", u.getNickName());
        userMap.put("avatarObjectName", objectName);
        
        dao.addUser(userMap);
        
        //3) DB: Update the code has been used
        userMap.put("invite_code", inviteCode);
        dao.updateInviteCode(userMap);   
        
        // Update project
        Map<String, String> projMap = new HashMap<>();
        projMap.put("project_id", projectId);
        if ("2".equals(rolePosition)) {
            projMap.put("project_admin_id", userId);
        } else if ("1".equals(rolePosition)) {
            projMap.put("project_area_admin_id", userId);
        }
        dao.setProjectAdmin(projMap);
               
        //5) Return sessioncode,roleCode,roleName
        String roleName = roleMap.get("role_name").toString();
        String userStatus = roleMap.get("role_position") + "";
        
        Map<String, Object> projectMap = dao.getProject(projectId);
        String projectName = projectMap.get("project_name").toString();
        
        Map<String, String> map = new HashMap<>();        
        map.put(SESSION_ID, sessionId);   
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("roleId", roleId);
        map.put("roleName", roleName);
        map.put("hintLevelId", roleMap.get("hint_level") + "");
        map.put("projectId", projectId);
        map.put("projectName", projectName);
        map.put(KEY_USER_STATUS, userStatus);
        result.setData(map);
        
        return result;
    }
    
    /*
    403_ERR_USER_NOT_REGISTERED
    405_ERR_USER_INACTIVE
     */
    public ResponseVO login(String code) {
        log.info("Login code {}", code);        
        ResponseVO result = new ResponseVO();
        Map<String, String> resultData = new HashMap<>();
        result.setData(resultData);
        
        //1) Get openId by (code, appId,appSecret)
        String url = URL_WX_SESSION + "&js_code=" + code;
        String res = HttpUtil.sendGet(url, null);
        log.info("WX Session Result: " + res);
        JSONObject obj = JSONObject.parseObject(res);
        String errCode = obj.getString("errcode");                
        if ("40029".equals(errCode) || "40013".equals(errCode) || "40125".equals(errCode)) {
            result.setStatusCode(obj.getString("errmsg"));
            return result;
        }
        String openId = obj.getString("openid");
        
        //2) User not registered
        Map<String, Object> userMap = dao.getUserByOpenId(openId);
        if (userMap == null) {
            resultData.put(KEY_USER_STATUS, ERR_USER_STATUS_5_NO_USER);
            return result;
        }
        
        //3) Login
        //Even uUser has logined, login again to refresh info
        String user_status = userMap.get("user_status").toString();
        if ("0".equals(user_status)) {
            resultData.put(KEY_USER_STATUS, ERR_USER_STATUS_6_USER_CLOSED);
            return result;
        }
        
        String roleId = userMap.get("role_id").toString();
        Map<String, Object> roleMap = dao.getRole(roleId);
        String roleName = roleMap.get("role_name") + "";
        String hintLevel = roleMap.get("hint_level") + "";
        
        String projectId = "";
        log.info("UserMap {}", userMap);
        if (userMap.containsKey("project_id") && userMap.get("project_id") != null) {
            projectId = userMap.get("project_id") + "";
        }
        log.info("ProjectId {}", projectId);
        
        Map<String, Object> projectMap = dao.getProject(projectId);
        if (projectMap == null) {
            result.setStatusCode(ERR_USER_NO_PROJECT);
            return result;
        }
        
        String projectName = projectMap.get("project_name").toString();

        String userId = userMap.get("user_id") + "";
        String rolePosition = roleMap.get("role_position") + "";
        String sessionId = getSessionId(userId, rolePosition);
        
        int projectCount = 0;
        log.info("RolePosition: {}", rolePosition);
        if ("1".equals(rolePosition) ) {
            projectCount = dao.getAreaAdminProjectCount(userId);
        } else if ("2".equals(rolePosition)) {
            projectCount = dao.getProjectAdminProjectCount(userId);
        } else {
            if (projectMap.containsKey("project_id")) {
                Object mProjectId = projectMap.get("project_id");
                log.info("m ProjectId {}", mProjectId);
                if (mProjectId != null && !mProjectId.equals("") ) {
                    projectCount = 1;
                }               
            }       
        }
        log.info("Project Count {}", projectCount);
        if (projectCount == 0) {
            result.setStatusCode(ERR_USER_NO_PROJECT);
            return result;
        }
        
        Map<String, String> map = new HashMap<>();
        map.put(SESSION_ID, sessionId);
        map.put("userId", userMap.get("user_id") + "");
        map.put("userName", userMap.get("user_name") + "");
        map.put("roleId", roleId);
        map.put("roleName", roleName);
        map.put("hintLevelId", hintLevel);
        map.put("projectId", projectId);
        map.put("projectName", projectName);
        map.put(KEY_USER_STATUS,  rolePosition);
        
        Object avatarObjectName = userMap.get("avatarObjectName");
        if (!BaseUtil.isEmpty(avatarObjectName)) {
            String avatarUrl = appUserService.getFileUrl(avatarObjectName + "");
            map.put("avatarUrl", avatarUrl);
        } else {
            map.put("avatarUrl", "");
        }
        
        result.setData(map);
        return result;
    }
    

    
    //Return 200 OK
    //400_ERR_INVALID_ADMIN_PWD
    public ResponseVO loginAdmin(String loginName, String originalPassword) {
        boolean isValid = false;
        String pwd = SecurityUtil.encode(originalPassword);
        //log.info("LoginName {} OriginalPwd:{} Pwd: {}", loginName, originalPassword, pwd);
        if (ROOT_USER.equals(loginName)) {
            if (ROOT_PWD.equals(pwd)) {
                String sessionId = getSessionId(loginName, "ROOT");
                
                Map<String, String> map = new HashMap<>();
                map.put(SESSION_ID, sessionId);
                map.put(AUTHRORIES, ROOT_AUTHORIES);
                
                ResponseVO result = new ResponseVO();
                result.setData(map);
                return result;
            }
        } else {
            int count = findSysUserCount(loginName, pwd);
            isValid = count > 0;
        }
        
        ResponseVO result = new ResponseVO();
        if (isValid) {
            String userId = dao.getSysUserIdByLoginName(loginName);
            String sessionId = getSessionId(userId, "ADMIN");
            
            String authories = findSysUserAuthories(loginName);
            
            Map<String, String> map = new HashMap<>();
            map.put(SESSION_ID, sessionId);
            map.put(AUTHRORIES, authories);
            result.setData(map);
        } else {
            result.setStatusCode(ERR_INVALID_ADMIN_PWD);            
        }
        
        return result;
    }
    
    public String findSysUserAuthories(String loginName) {
        return dao.findSysUserAuthories(loginName);
    }
    
    public Map<String, String> getSysInfo() {
        Map<String, String> map = BaseUtil.getSysInfo();
        map.put("version", appConf.getVersion() + "");
        map.put("configPath", configVO.getConfigPath());
        
        map.put("URL_WX_SESSION", URL_WX_SESSION);
        map.put("TMP", ROOT_PWD);
        
        return map;
    }
    public int findSysUserCount(String loginName, String loginPwd) {
        return dao.findSysUserCount(loginName, loginPwd);
    }
    
    public PageResultVO findDummyPage(DummyVO param, PageVO pageVO) throws ServiceException {
        log.info("Param: {}, PageVO: {}", param, pageVO);
        return doPagedQuery(dao, "findDummyPage", param, pageVO);
    }
    
    @Transactional //rollbackFor = ServiceException.class
    public Integer addDummy(DummyVO dummyVO) throws ServiceException {
        log.info("add dummy: " + dummyVO);
        //dummyDao.addDummy(dummyVO);
        
        if (dummyVO.getUserName().equals("ERROR"))
            throw new ServiceException("ERR_USER_NAME");

        return dummyVO.getUserId();
    }    

    public void downloadLog() throws ServiceException {
        //1) Log
        File file = new File(LOG_DIR);
        File[] files = file.listFiles(); //if file not exist, file.listFiles() will return null, if file.exist(), will return [] 
        if (files == null || files.length == 0) {
            throw new ServiceException("No log files");
        }

        StringBuffer sb = new StringBuffer("logs\n");
        for (int i = 0; i < files.length; i++) {
            sb.append(i + ": " + files[i].getName() + "\n");
        }
        log.info(sb.toString());

        //2) Download
        File zipFile = new File(LOG_ZIP_FULL_FILE_NAME);
        try {
            ZipUtils.compressFiles(files, zipFile);
            IoUtil.writeFile2Response(zipFile);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
            FileUtil.deleteFile(zipFile);
        }
    }

    @Override
    public void addAccessRecord(AccessVO vo) throws ServiceException {
        //dummyDao.addAccessRecord(vo);
    }

    public PageResultVO findAccessPage(PageVO pageVO) throws ServiceException {
        return doPagedQuery(dao, "findAccessPage", null, pageVO);
    }

    public String uploadFile(MultipartFile multiFile) throws ServiceException {
        String extension = FilenameUtils.getExtension(multiFile.getOriginalFilename());
        String fileId = Long.toString(System.currentTimeMillis());
        String newFileName = FileUtil.buildFilePrefixName(getCurrentUserId(), fileId) + DIR_CURR + extension;
        String fullNewFileName = getTmpDir(configVO.getConfigPath()) + newFileName; // + multiFile.getOriginalFilename() 
        log.info("Upload doc to app server: {} ", fullNewFileName);

        try (InputStream in = multiFile.getInputStream()) {
            writeFile(in, fullNewFileName, supportedDocTypes);
            //Save to T_Attachment(newFileName, multiFile.getOriginalFilename())
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ServiceException(ERR_FAIL);
        }

        return fileId;
    }
    
    public void uploadDoc(@RequestParam MultipartFile multiFile) throws ServiceException {
        try (InputStream in = multiFile.getInputStream()) {
            FileUtils.copyInputStreamToFile(in, new File("c:/" + multiFile.getOriginalFilename()));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ServiceException(ERR_UPLOAD);
        }
    }
    
    public String testParams(int userId, String userName) throws ServiceException {
        log.info("userId: {}, userName: {}", userId, userName);
        return "OK";
    }
    
    public JSONArray getCities() throws ServiceException {
        try {
            StringBuffer sb = new StringBuffer();
            Scanner scanner = new Scanner( new File(appConf.getConfigPath() + "city.js"));
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            scanner.close();
            JSONArray array = JSONArray.parseArray(sb.toString());
            
            log.info("{}", array.getJSONObject(0).getString("label"));
            log.info("{}", array.getJSONObject(0).getJSONArray("children").getJSONObject(0).getJSONArray("children").getJSONObject(0).get("label"));
            return array;
        } catch (Exception e) {
            log.error("{}", e);
            throw new ServiceException(e.getMessage());
        }
    }
}
