package com.zero.hintmgr.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DummyDao {
    // Invite Code
    Integer findInviteCodeCount(@Param("inviteCode") String inviteCode);
    boolean updateInviteCode(Map<String, String> map);
    Map<String, Object> getInviteCode(@Param("inviteCode") String inviteCode);    
    void discardInviteCode(@Param("invite_code") String invite_code);   

    List<Map<String, Object>> findInviteCodePage(Map<String, String> param);
    int findInviteCodePageCount(HashMap<String, String> param);
    
    List<String> getAllInviteCodes();
    int addInviteCode(@Param("invite_code") String invite_code,@Param("project_id") String project_id,@Param("role_id") String role_id);  
    
    // User
    int addUser(Map<String, String> user);    
    Map<String, Object> getUserByOpenId(String openId);
    Map<String, Object> getUserByUserId(String userId);
    int resetUser(String userId);
    int getUserCountByRoleId(String roleId);
    int updateUserJoinProjectTime(String userId);
    
    // Role
    Map<String, Object> getRole(String roleId);
    int addRole(Map<String, String> map);
    int updRole(Map<String, String> map);
    int delRole(@Param("role_id") String role_id);
    
    List<Map<String, Object>> findRolesPage(Map<String, String> param);
    int findRolesPageCount(HashMap<String, String> param);
    
    //Project
    int addProject(Map<String, String> map);
    int updProject(Map<String, String> map);
    Map<String, Object> getProject(String projectId);
    String getProjectId(@Param("project_name") String project_name);
    int delProject(@Param("project_id") String projectId);
    int archiveProject(@Param("project_id") String projectId);
    int clearUserProject(String projectId);
    List<Map<String, Object>> findProjectPage(Map<String, String> param);
    int findProjectPageCount(HashMap<String, String> param);
    
    int updProjectAreaAdminJoinTime(@Param("project_id") String projectId);
    int updProjectAdminJoinTime(@Param("project_id") String projectId);
    
    int setProjectAdmin(Map<String, String> param);
    int setProjectAreaAdminId(@Param("project_id") String projectId, @Param("project_area_admin_id") String project_area_admin_id);
    int setProjectAdminId(@Param("project_id") String projectId, @Param("project_admin_id") String project_admin_id);
    
    List<Map<String, Object>> adminUsers();
    List<Map<String, Object>> projectMembers(@Param("project_id") String projectId);
    List<Map<String, Object>> projectUsers(@Param("project_id") String projectId);
    List<Map<String, Object>> nonProjectUsers();
    
    int quitProject(Map<String, String> map);
    int joinProject(Map<String, String> map);
    int clearProjectAdmin(@Param("project_admin_id") String project_admin_id, @Param("project_id") String project_id);
    
    List<String> getProjectIdsByProjectAdmin(@Param("user_id") String user_id);
    List<String> getProjectIdsByAreaAdmin(@Param("user_id") String user_id);
    
    //Dict
    int addDict(Map<String, Object> map);
    int updDict(Map<String, Object> map);
    int delDict(@Param("dict_id") String dictId);
    Map<String, Object> getDict(@Param("dict_id") String dictId);
    List<Map<String, Object>> findDictPage(Map<String, String> param);
    int findDictPageCount(HashMap<String, String> param);
    List<Map<String,String>> findDicts();
    
    int getDictCustomerTypeRefCount(@Param("dict_id") String dict_id);
    int getItemTypeRefCount(String dictId);
    int getHintTypeRefCount(String dictId);
    int getHintLevelRefCount(String dictId);
    int getHintLevelRoleRefCount(String dictId);
    String getHintValidHour();
    
    //SysRole
    int addSysRole(Map<String, String> map);
    int updSysRole(Map<String, String> map);
    int delSysRole(@Param("role_id") String role_id);
    Map<String, Object> getSysRole(@Param("role_id") String role_id);
    List<Map<String, Object>> findSysRolePage(Map<String, String> param);
    int findSysRolePageCount(HashMap<String, String> param);
    int getSysUserCountByRoleId(String roleId);
    
    //SysUser
    int addSysUser(Map<String, Object> map);
    int updSysUser(Map<String, Object> map);
    int delSysUser(@Param("user_id") String user_id);
    List<Map<String, Object>> findSysUserPage(Map<String, String> param);
    int findSysUserPageCount(HashMap<String, String> param);
    int findSysUserCount(@Param("login_name") String login_name, @Param("login_pwd") String login_pwd);
    String findSysUserAuthories(@Param("login_name") String login_name);
    void setSysUserStatus(@Param("user_id")String user_id, @Param("user_status")String user_status);
    Map<String, Object> getSysUser(@Param("user_id") String user_id);
    String getSysUserIdByLoginName(@Param("login_name")String login_name);
    
    //Hint
    int addHint(Map<String, Object> map);
    Map<String, Object> getHint(@Param("hint_id") String hint_id);
    int delHint(@Param("hint_id") String hint_id);
    int assignHint(@Param("hint_id") String hint_id, @Param("user_id") String user_id);
    int auditHint(Map<String, Object> map);
    int updHint(Map<String, Object> map);
    int addHintImg(@Param("hint_image_id") String hint_image_id, @Param("hint_id") String hint_id, @Param("img_objname") String image_url);
    int delHintImg(@Param("hint_id") String hint_id);
    List<String> getHintImages(@Param("hint_id") String hint_id);
    
    List<Map<String, Object>> findHintPage(Map<String, Object> param);
    int findHintPageCount(HashMap<String, Object> param);
    
    List<Map<String, Object>> findHintRecordPage(Map<String, Object> param);
    int findHintRecordPageCount(HashMap<String, Object> param);  
    
    //User Mangement
    List<Map<String, Object>> findUserPage(Map<String, String> param);
    int findUserPageCount(HashMap<String, String> param);    
    
    int setUserStatus(@Param("user_id") String user_id, @Param("user_status")String user_status);
    
    //App Manager
    List<Map<String, Object>> findProjectsByAppMgr(Map<String, String> param);
    List<Map<String, Object>> findRunningProjects();
    
    List<Map<String, Object>> findAppMgrHintPage(Map<String, String> param);
    int findAppMgrHintPageCount(HashMap<String, String> param);  
    
    List<Map<String, Object>> findHintsByAskedUserPage(Map<String, String> param);
    int findHintsByAskedUserPageCount(HashMap<String, String> param);   
    
    List<Map<String, Object>> findHintsByAddedUserPage(Map<String, String> param);
    int findHintsByAddedUserPageCount(HashMap<String, String> param);  
    
    //HintDetail
    Map<String, Object> getHintDetail(@Param("hint_id") String hint_id);
    
    List<Map<String, Object>> getCustomerTypes();
    
    List<Map<String, Object>> theHintRecords(@Param("hint_id") String hint_id);
    
    List<Map<String, Object>> findUsers(@Param("project_id") String projectId);
    
    // ---- 管理员-成员列表  Begin    
    List<Map<String, Object>> findNormalUsersByProjectAdminPage(Map<String, String> param);    //Position (3,4) @Param("project_id") String projectId, @Param("order_column") String order_column, @Param("role_position") String rolePosition
    int findNormalUsersByProjectAdminPageCount(HashMap<String, String> param);
    
    List<Map<String, Object>> findProjectAdminUsersByAreaAdminPage(Map<String, String> param); //Position 2 @Param("project_id") String projectId
    int findProjectAdminUsersByAreaAdminPageCount(HashMap<String, String> param);
    
    //Map(projectCount, projectAdminCount)
    Map<String, Object> findProjectAdminCountAndProjectCount(@Param("project_id") String projectId);
    // --- 管理员-成员列表 End
    
    Map<String, Object> projectAdminDetail(@Param("project_admin_id") String project_admin_id); //Project Admin Detail
    
    List<Map<String, Object>> userAskedHints(@Param("user_id") String user_id);
    List<Map<String, Object>> userAddedHints(@Param("user_id") String user_id);
    
    //Personal Center Begin
    int getProjectAdminProjectCount(@Param("user_id") String user_id);
    List<Map<String, Object>> getProjectAdminMemberCount(@Param("user_id") String user_id);
    
    int getAreaAdminProjectCount(@Param("user_id") String user_id);
    List<Map<String, Object>> getAreaAdminMemberCount(@Param("user_id") String user_id);
    int getProjectAdminCountOfAreaAdmin(@Param("user_id") String user_id);
    
    //Person Center End
    //@Param("user_id") String user_id, @Param("item_type_name"
    List<Map<String, Object>> findPublicHintsPage(Map<String, String> param);
    int findPublicHintsPageCount(HashMap<String, String> param);
    
    void askHint(@Param("hint_id") String hint_id, @Param("user_id") String user_id, @Param("valid_starttime") Date valid_starttime, @Param("valid_endtime") Date valid_endtime); 
    void freeHint(@Param("hint_id") String hint_id);
    void autoFreeHint();
    
    //item_type_name, my_hint_type=ADD,ASK
    List<Map<String, Object>> myHintsPage(Map<String, String> param);
    int myHintsPageCount(HashMap<String, String> param);    
    
    void updHintRemark(@Param("hint_id")String hintId, @Param("remark")String remark);    
    void addHintRecord(Map<String, String> param);
    
    int getAddedHintsCount(String userId);
    int getTracedHintsCount(String userId);
    
    //Added Hint Ranking
    List<Map<String, Object>> getAddedHintsCountRankPage(Map<String, String> param);
    int getAddedHintsCountRankPageCount(HashMap<String, String> param);    
    //Traced Hint Ranking    
    List<Map<String, Object>> getTracedHintsCountRankPage(Map<String, String> param);
    int getTracedHintsCountRankPageCount(HashMap<String, String> param);    
    
    
    //Statics
    Map<String, Object> staticsTotalCount();
    List<Map<String, Object>> staticsTotalSaleBuildCount();
    
    List<Map<String, Object>> staticsAskHintCount(@Param("date1") String date1, @Param("date2") String date2);
    List<Map<String, Object>> staticsTraceHintCount(@Param("date1") String date1, @Param("date2") String date2);
    
    List<Map<String, Object>> staticsUserCount(@Param("date1") String date1, @Param("date2") String date2);
    List<Map<String, Object>> staticsSalesBuildCount(@Param("date1") String date1, @Param("date2") String date2);
    
    void updateProjectTime(Map<String, Object> param);
}
