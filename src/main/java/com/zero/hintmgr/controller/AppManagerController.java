package com.zero.hintmgr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zero.core.domain.PageVO;
import com.zero.core.domain.ResponseVO;
import com.zero.hintmgr.service.impl.AdminService;
import com.zero.hintmgr.util.BaseUtil;
import com.zero.hintmgr.vo.ParamObj;

import io.swagger.annotations.Api;

@Api(tags = "APP Admin Controller", value = "APP Admin Controller.")
/**
 * AdminController
 *    
 * @author Administrator
 * @version 2021-11-04
 */
@RestController
@RequestMapping("/app/mgr")
public class AppManagerController extends BaseController {
    Logger log = LoggerFactory.getLogger(AppManagerController.class);

    @Autowired
    private AdminService service;
    
    @GetMapping("/projects")
    public ResponseVO projects(@RequestParam(value="project_name", required=false) String projectName, @RequestParam(value="userId", required=false) String userId) {
        return process(() -> service.findProjectsByAppMgr(projectName, userId));
    }
    
    @GetMapping("/runningProjects")
    public ResponseVO runningProjects() {
        return process(() -> service.findRunningProjects());
    }
    
    @GetMapping("/hints")
    public ResponseVO hints(
            @RequestParam(value="projectId", required=false) String projectId, 
            @RequestParam(value="projectName", required=false) String projectName, 
            @RequestParam(value="hintStatus", required=false) String hintStatus, 
            PageVO pageVO) {
        return process(() -> service.findAppMgrHintPage(projectId, projectName, hintStatus,pageVO));
    }
    
    //认领列表
    @GetMapping("/hintsByAskedUser")
    public ResponseVO hintsByAskedUser(@RequestParam(value="userId", required=false) String userId, @RequestParam(value="userName", required=false) String userName, PageVO pageVO) {
        return process(() -> service.findHintsByAskedUserPage(userId, userName, pageVO));
    }
    
    //新增列表
    @GetMapping("/hintsByAddedUser")
    public ResponseVO hintsByAddedUser(@RequestParam(value="userId", required=false) String userId, @RequestParam(value="userName", required=false) String userName, PageVO pageVO) {
        return process(() -> service.findHintsByAddedUserPage(userId, userName, pageVO));
    }
    
    //线索详情
    @GetMapping("/hintDetails")
    public ResponseVO getHintDetail(@RequestParam(value="hintId") String hintId) {
        return process(() -> service.getHintDetail(hintId));
    }
    
    @GetMapping("/assignHint")
    public ResponseVO assignHint(@RequestParam(value="hint_id") String hintId, @RequestParam(value="user_id") String user_id) {
        return process(() -> service.assignHint(hintId, user_id));
    }
    
    //分配线索的时候，查询 待分配线索所在项目里面的 用户(销售员 拓客员)
    @GetMapping("/users")
    public ResponseVO findUsers(@RequestParam(value="projectId") String projectId) {
        return process(() -> service.findUsers(projectId));
    }
    
    /*
        成员列表-销售员 /members?projectId=xx&rolePosition=3&orderColumn=ADD&orderType=U
        成员列表-拓客员 /members?projectId=xx&rolePosition=4&orderColumn=ADD&orderType=U
        成员列表-管理员 /members?projectId=xx&rolePosition=2
         
        参数说明：
       orderField: 排序字段 ADD=新增线索 TRACE=跟进线索
       orderType:  排序顺序 U=升序 D=降序
       projectId:  项目ID
    */
    @GetMapping("/members")
    public ResponseVO members(ParamObj paramObj, PageVO pageVO) {
        log.info("Param: {}", paramObj);
        
        return process(() -> service.findMembersPage(paramObj, pageVO));
    }
    //For AreaAdmin to query
    @GetMapping("/projectAdminCountAndProjectCount")
    public ResponseVO findProjectAdminCountAndProjectCount() {
        String userId = BaseUtil.getUserIdFromSessionId();
        return process(() -> service.findProjectAdminCountAndProjectCount(userId));
    }    
    
    @GetMapping("/managerDetail")
    public ResponseVO projectAdminDetail(@RequestParam(value="userId") String userId) {
        return process(() -> service.projectAdminDetail(userId));
    }
    
    @GetMapping("/userHints")
    public ResponseVO userHints(@RequestParam(value="userId") String userId) {
        return process(() -> service.userHints(userId));
    }
    
    @GetMapping("/myInfo")
    public ResponseVO myInfo() {
        return process(() -> service.myInfo());
    }
    
    @GetMapping("/rank")
    public ResponseVO rank(@RequestParam(value="type") String type, PageVO pageVO) {
        return process(() -> service.rank(pageVO, type));
    }
}
