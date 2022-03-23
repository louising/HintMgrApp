package com.zero.hintmgr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zero.core.domain.PageVO;
import com.zero.core.domain.ResponseVO;
import com.zero.hintmgr.service.impl.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Admin Controller", value = "Admin Controller", produces = "The Produces")
/**
 * //TODO 后台管理 员工管理 总认领数 改成 总跟进数
 * //TODO Check 分配线索 用户必须和 线索 在同一个项目
 * //TODO 查询项目成员的时候，查询加入项目时间
 * //TODO 用户加入项目时，保存 项目加入时间
 * AdminController
 * http://localhost:8080/hintmgr/admin/downloadHintTemplate?sessionId=xx
 * http://119.91.195.217:8080/hintmgr/admin/downloadHintTemplate
 * http://119.91.195.217:8080/hintmgr/admin/findUsers?sessionId=1234
 * 
 * @author Administrator
 * @version 2021-11-04
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    Logger log = LoggerFactory.getLogger(AdminController.class);
    
    @Autowired
    private AdminService service;
    
    @GetMapping("/cities")
    public ResponseVO cites() {
        return process(() -> service.getCities());
    }
    
    @ApiOperation(value = "保存项目", notes="project_id 非空则更新，为空则新增")    
    //Project
    @PostMapping("/saveProject")
    public ResponseVO saveProject(@RequestBody Map<String, String> map) {
        return process(() -> service.saveProject(map));
    }
    @GetMapping("/delProject")
    public ResponseVO delProject(@RequestParam("projectId") String projectId) {
        return process(() -> service.delProject(projectId));
    }
    @GetMapping("/archiveProject")
    public ResponseVO archiveProject(@RequestParam("projectId") String projectId) {
        return process(() -> service.archiveProject(projectId));
    }
    @GetMapping("/projects")
    public ResponseVO projects(@RequestParam(value="projectName", required=false) String projectName, PageVO pageVO) {
        log.info("Project: {} PageVO {}", projectName, pageVO);
        
        Map<String, String> map = new HashMap<String,String>();
        map.put("project_name", projectName);
        return process(() -> service.findProjectPage(map, pageVO));
    }    
    
    @GetMapping("/adminUsers")
    public ResponseVO adminUsers() {
        return process(() -> service.adminUsers());
    }
    
    //项目成员(用户+管理员)
    @GetMapping("/projectMembers")
    public ResponseVO projectMembers(@RequestParam("project_id") String projectId) {
        return process(() -> service.projectMembers(projectId));
    }
    
    //拓客员+销售员
    @GetMapping("/projectUsers")
    public ResponseVO projectUsers(@RequestParam("project_id") String projectId) {
        return process(() -> service.projectUsers(projectId));
    }
    
    @GetMapping("/nonProjectUsers")
    public ResponseVO nonProjectUsers() {
        return process(() -> service.nonProjectUsers());
    }
    
    @PostMapping("/setAdmin")
    public ResponseVO setProjectAdmin(@RequestBody Map<String, String> param) {
        return process(() -> service.setProjectAdmin(param));
    } 
    
    @PostMapping("/setProjectMembers")
    public ResponseVO setProjectMembers(@RequestParam("project_id") String projectId, @RequestParam("operType") String operType, @RequestBody List<Map<String, String>> param) {
        return process(() -> service.setProjectMembers(projectId, operType, param));
    }
    
    @PostMapping("/moveMembers")
    public ResponseVO moveMembers(@RequestParam("projectid") String projectId, @RequestBody List<Map<String, String>> params) {
        log.info("moveMembers {} \n {}", projectId, params);
        return process(() -> service.setProjectMembers(projectId, "1", params));
    }
    
    //SysRole
    @PostMapping("/saveSysRole")
    public ResponseVO saveSysRole(@RequestBody Map<String, String> map) {
        return process(() -> service.saveSysRole(map));
    }
    
    @GetMapping("/delSysRole")
    public ResponseVO delSysRole(@RequestParam("roleId") String roleId) {
        return process(() -> service.delSysRole(roleId));
    }
    
    @GetMapping("/sysRoles")
    public ResponseVO sysRoles(PageVO pageVO) {
        return process(() -> service.findSysRolePage(new HashMap<String,String>(), pageVO));
    }
    
    //SysUser
    @PostMapping("/saveSysUser")
    public ResponseVO saveSysUser(@RequestBody Map<String, Object> map) {
        return process(() -> service.saveSysUser(map));
    }
    
    @GetMapping("/delSysUser")
    public ResponseVO del(@RequestParam("userId") String userId) {
        return process(() -> service.delSysUser(userId));
    }
    
    @GetMapping("/sysUsers")
    public ResponseVO sysUsers(PageVO pageVO) {
        return process(() -> service.findSysUserPage(new HashMap<String,String>(), pageVO));
    }
    
    //Map<String, String> param
    @GetMapping("/setSysUserStatus")
    public ResponseVO setSysUserStatus(@RequestParam("user_id") String user_id, @RequestParam("user_status") String user_status) {
        return process(() -> service.setSysUserStatus(user_id, user_status));
    } 
    
    //Dict
    @PostMapping("/saveDict")
    public ResponseVO saveDict(@RequestBody Map<String, Object> map) {
        return process(() -> service.saveDict(map));
    }
    
    @GetMapping("/delDict")
    public ResponseVO delDict(@RequestParam("dict_id") String dict_id) {
        return process(() -> service.delDict(dict_id));
    }
    
    @GetMapping("/dicts")
    public ResponseVO dicts(@RequestParam("dict_type") String dict_type, PageVO pageVO) {
        Map<String, String> param = new HashMap<>();
        param.put("dict_type", dict_type);
        return process(() -> service.findDictPage(param, pageVO));
    }  
    
    @GetMapping("/getDict")
    public ResponseVO getDict(@RequestParam("dict_id") String dict_id) {
        return process(() -> service.getDict(dict_id));
    }
    
    //Hint
    @PostMapping("/saveHint")
    public ResponseVO saveHint(@RequestBody Map<String, Object> map) {
        return process(() -> service.saveHintFromAdmin(map));
    }
    
    @GetMapping("/delHint")
    public ResponseVO delHint(@RequestParam("hint_id") String hint_id) {
        return process(() -> service.delHint(hint_id));
    }
    
    @PostMapping("/hints")
    public ResponseVO hints(@RequestBody Map<String, Object> param, PageVO pageVO) {
        return process(() -> service.findHintPage(param, pageVO));
    }
    
    @GetMapping("/hintDetails")
    public ResponseVO hintDetails(@RequestParam("hintId") String hintId) {
        return process(() -> service.getHintDetailFromAdmin(hintId));
    }
    
    @GetMapping("/assignHint")
    public ResponseVO assignHint(@RequestParam("hint_id") String hint_id, @RequestParam("user_id") String user_id) {
        return process(() -> service.assignHint(hint_id, user_id));
    }
    
    @PostMapping("/auditHint")
    public ResponseVO auditHint(@RequestBody Map<String, Object> param) {
        return process(() -> service.auditHint(param));
    }
    
    //hintRecords?pageSize=20&pageIndex=1
    @PostMapping("/hintRecords")
    public ResponseVO hintRecords(@RequestBody Map<String, Object> param, PageVO pageVO) {
        return process(() -> service.findHintRecordPage(param, pageVO));
    }
    
    @GetMapping("/downloadHintTemplate")
    public ResponseVO downloadHintTemplate() {
        service.downloadHintTemplate();
        return new ResponseVO();
    }
    
    @PostMapping(path = "/importHints", consumes = "multipart/form-data")
    public ResponseVO importHints(@RequestParam MultipartFile multiFile) {
        return process(()-> service.importHints(multiFile));        
    }
    
    @GetMapping("/findUsers")
    public ResponseVO findUsers(@RequestParam(value="userName", required=false) String userName, 
            @RequestParam(value="roleName",required=false) String roleName, 
            @RequestParam(value="tel", required=false) String tel, 
            @RequestParam(value="invite_code", required=false) String invite_code, 
            @RequestParam(value="role_name", required=false) String role_name, 
            PageVO pageVO) {
        return process(()-> service.findUserPage(userName, roleName, tel, invite_code, role_name, pageVO));        
    }

    @GetMapping("/setUserStatus")
    public ResponseVO setUserStatus(@RequestParam("user_id") String user_id, @RequestParam("user_status") String user_status) {
        return process(() -> service.setUserStatus(user_id, user_status));
    }

    @GetMapping("/resetUser")
    public ResponseVO resetUser(@RequestParam("userId") String userId) {
        return process(() -> service.resetUser(userId));
    }
    
    // InviteCode
    @GetMapping("/inviteCodes")
    public ResponseVO findInviteCodePage(@RequestParam(value = "roleId", required=false) String roleId, 
            @RequestParam(value="status", required=false) String code_status, 
            @RequestParam(value="project_name", required=false) String project_name,
            @RequestParam(value="invite_code", required=false) String invite_code,
            PageVO pageVO) {
        return process(()-> service.findInviteCodePage(roleId, code_status, project_name,invite_code, pageVO));        
    }
    
    @GetMapping("/discardInviteCode")
    public ResponseVO discardInviteCode(@RequestParam("inviteCode") String inviteCode) {
        return process(()-> service.discardInviteCode(inviteCode));        
    }    
    
    @GetMapping("/generateInviteCodes")
    public ResponseVO generateInviteCodes(@RequestParam("project_id") String project_id,@RequestParam("role_id") String role_id,@RequestParam("count") int count) {
        return process(()-> service.generateInviteCodes(project_id,role_id,count));        
    } 
    
    // Role
    @GetMapping("/roles")
    public ResponseVO roles(PageVO pageVO) {
        return process(()-> service.findRolesPage(new HashMap<String, String>(), pageVO));        
    }
    
    @PostMapping("/saveRole")
    public ResponseVO saveRole(@RequestBody Map<String, String> map) {
        return process(() -> service.saveRole(map));
    }
    
    @GetMapping("/delRole")
    public ResponseVO delRole(@RequestParam("roleId") String roleId) {
        return process(() -> service.delRole(roleId));
    }
    
    //Statistic
    @GetMapping("/statistics")
    public ResponseVO statistics() {
        return process(() -> service.statisTotal());
    }
    
    @GetMapping("/statisticHints")
    public ResponseVO statisticHints(@RequestParam("date1") String date1, @RequestParam("date2") String date2) {
        return process(() -> service.statisticHints(date1, date2));
    }
    
    @GetMapping("/statisticUsers")
    public ResponseVO statisticUsers(@RequestParam("date1") String date1, @RequestParam("date2") String date2) {
        return process(() -> service.statisticUsers(date1, date2));
    }
}
