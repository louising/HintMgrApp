package com.zero.hintmgr.service.impl;

import static com.zero.hintmgr.constants.Constants.DICTTYPE_CUSTOMER_TYPE;
import static com.zero.hintmgr.constants.Constants.DICTTYPE_HINT_LEVEL;
import static com.zero.hintmgr.constants.Constants.DICTTYPE_HINT_TYPE;
import static com.zero.hintmgr.constants.Constants.DICTTYPE_ITEM_TYPE;
import static com.zero.hintmgr.constants.Constants.ERR_DICT_CUSTOMERTYPE_USED;
import static com.zero.hintmgr.constants.Constants.ERR_DICT_HINTTYPE_USED;
import static com.zero.hintmgr.constants.Constants.ERR_DICT_ITEMTYPE_USED;
import static com.zero.hintmgr.constants.Constants.ERR_DICT_LEVLEID_ROLE_USED;
import static com.zero.hintmgr.constants.Constants.ERR_DICT_LEVLEID_USED;
import static com.zero.hintmgr.constants.Constants.ERR_ROLE_USED_BY_USER;
import static com.zero.hintmgr.constants.Constants.ERR_SYS_ROLE_USED_BY_USER;
import static com.zero.hintmgr.util.BaseUtil.isEmpty;
import static com.zero.hintmgr.util.BaseUtil.*;

import java.io.Closeable;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.zero.core.domain.PageResultVO;
import com.zero.core.domain.PageVO;
import com.zero.hintmgr.ServiceException;
import com.zero.hintmgr.conf.AppConf;
import com.zero.hintmgr.dao.DummyDao;
import com.zero.hintmgr.util.BaseUtil;
import com.zero.hintmgr.util.IoUtil;
import com.zero.hintmgr.util.SecurityUtil;
import com.zero.hintmgr.vo.ParamObj;
import com.zero.hintmgr.vo.StatisHintVO;
import com.zero.hintmgr.vo.StatisUserVO;
import com.zero.hintmgr.vo.UserSession;

//TODO Verify Del(Role): Can not del when has users
//TODO Verify can not add the same loginName, and update the same loginName
@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdminService extends BaseServiceImpl {
    Logger log = LoggerFactory.getLogger(DummyServiceImpl.class);

    final static String LOG_DIR = "/hintmgr/logs/"; //refer to logback.xml
    final static String LOG_ZIP_FULL_FILE_NAME = LOG_DIR + "logs.zip";

    @Autowired
    protected DummyDao dao;

    @Autowired
    private AppConf appConf;
    
    @Autowired
    private AppUserService userService;
    
    public JSONArray getCities() throws ServiceException {
        try {
            StringBuffer sb = new StringBuffer();
            Scanner scanner = new Scanner(new File(appConf.getConfigPath() + "city.js"));
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

    //Project
    public void saveProject(Map<String, String> map) {
        //1
        String projectId = map.get("project_id");
        if (isEmpty(projectId)) {
            projectId = BaseUtil.getPkCode();
            map.put("project_id", projectId);
            dao.addProject(map);
        } else {
            Map<String, Object> projMap = dao.getProject(projectId);
            
            //If update area admin, update join time
            String project_area_admin_id = map.get("project_area_admin_id");
            if (!isEmpty(project_area_admin_id)) {
                Object oldProjectAreaAdminId = projMap.get("project_area_admin_id"); 
                if ( oldProjectAreaAdminId == null || !oldProjectAreaAdminId.equals(project_area_admin_id)) {
                    dao.updProjectAreaAdminJoinTime(projectId);
                }
            }
            
            //if update project admin, update join time
            String project_admin_id = map.get("project_admin_id");
            if (!isEmpty(project_admin_id)) {
                Object oldProjectAdminId = projMap.get("project_admin_id"); 
                if ( oldProjectAdminId == null || !oldProjectAdminId.equals(project_admin_id)) {
                    dao.updProjectAdminJoinTime(projectId);
                }
            }
            
            dao.updProject(map);
        }
        
        //2
        String project_admin_id = map.get("project_admin_id") + "";
        if (project_admin_id != null && !project_admin_id.equals("")) {
            dao.clearProjectAdmin(project_admin_id, projectId);
        }        
    }

    public void delProject(String projectId) {
        dao.delProject(projectId);
    }

    public void archiveProject(String projectId) {
        dao.archiveProject(projectId);
        dao.clearUserProject(projectId);
    }

    public PageResultVO findProjectPage(Map<String, String> param, PageVO pageVO) throws ServiceException {
        PageResultVO vo = doPagedQuery(dao, "findProjectPage", param, pageVO);
        formateDateTime((List) vo.getRecords(), "create_time");
        return vo;
    }

    public List<Map<String, Object>> adminUsers() {
        return dao.adminUsers();
    }

    public List<Map<String, Object>> projectMembers(String project_id) {
        List<Map<String, Object>> list = dao.projectMembers(project_id);
        
        formateDateTime(list, "join_time");
        return list;
    }

    public List<Map<String, Object>> projectUsers(String project_id) {
        List<Map<String, Object>> list = dao.projectUsers(project_id);
        
        for (Map<String, Object> map: list) {
            if (map.containsKey("avatarObjectName")) {
                String avatarObjectName = map.get("avatarObjectName") + "";
                String url = userService.getFileUrl(avatarObjectName + "");
                map.put("avatarUrl", url);
            }
        }
        
        return list;
    }

    public List<Map<String, Object>> nonProjectUsers() {
        return dao.nonProjectUsers();
    }

    public void setProjectAdmin(Map<String, String> param) {
        dao.setProjectAdmin(param);
    }

    //@param str aa,bb
    //@Return 'aa','bb'
    String concateStr(String str) {
        String s = "";
        if (str.contains(",")) {
            String[] strs = str.split("[,]");
            s = "'" + strs[0] + "'";
            for (int i = 1; i < strs.length; i++) {
                s += ",'" + strs[i] + "'";
            }
        } else {
            s = "'" + str + "'";
        }
        return s;
    }

    //@Param params(user_id=xx, role_position=xx )
    public void setProjectMembers(String projectId, String operType, List<Map<String, String>> params) {
        String projectAreaAdminId = "", projectAdminId = "";
        String userIds = "";

        for (Map<String, String> map : params) {
            String userId = map.get("user_id");
            String rolePositon = map.get("role_position");
            if ("1".equals(rolePositon)) {
                projectAreaAdminId = userId;
            } else if ("2".equals(rolePositon)) {
                projectAdminId = userId;
            } else {
                if (userIds.equals("")) {
                    userIds = "'" + userId + "'";
                } else {
                    userIds += ",'" + userId + "'";
                }
            }
        }

        Map<String, String> map = new HashMap<>();
        if (!"".equals(userIds)) {
            map.put("project_id", projectId);
            map.put("userIds", userIds);
        }

        if ("1".equals(operType)) {
            if (!projectAreaAdminId.equals("")) {
                dao.setProjectAreaAdminId(projectId, projectAreaAdminId);
                
                dao.updProjectAreaAdminJoinTime(projectId);
            }
            if (!projectAdminId.equals("")) {
                dao.setProjectAdminId(projectId, projectAdminId);
                
                dao.updProjectAdminJoinTime(projectId);
                dao.clearProjectAdmin(projectAdminId, projectId);
            }
            if (map.size() > 0) {
                dao.joinProject(map);
                
                for (Map<String, String> mMap : params) {
                    String userId = mMap.get("user_id");
                    dao.updateUserJoinProjectTime(userId);
                }
            }
        } else if ("2".equals(operType)) {
            if (!projectAreaAdminId.equals("")) {
                dao.setProjectAreaAdminId(projectId, "");
            }
            if (!projectAdminId.equals("")) {
                dao.setProjectAdminId(projectId, "");
            }
            if (map.size() > 0) {
                dao.quitProject(map);
            }
        }
    }

    //SysRole
    public void saveSysRole(Map<String, String> map) { //Add or Update
        Object id = map.get("role_id");
        if (isEmpty(id)) {
            map.put("role_id", BaseUtil.getPkCode());
            dao.addSysRole(map);
        } else {
            dao.updSysRole(map);
        }
    }

    public void delSysRole(String id) {
        int count = dao.getSysUserCountByRoleId(id);
        if (count > 0) {
            throw new ServiceException(ERR_SYS_ROLE_USED_BY_USER);
        }
        dao.delSysRole(id);
    }

    public Map<String, Object> getSysRole(String id) {
        return dao.getSysRole(id);
    }

    public PageResultVO findSysRolePage(Map<String, String> param, PageVO pageVO) throws ServiceException {
        return doPagedQuery(dao, "findSysRolePage", param, pageVO);
    }

    //SysUser
    public void saveSysUser(Map<String, Object> map) { //Add or Update
        Object id = map.get("user_id");
        String pwd = map.get("login_pwd") + "";
        log.info("UserId {} Original Pwd {}", id, pwd);

        String encryptedPwd = SecurityUtil.encode(pwd);
        map.put("login_pwd", encryptedPwd);

        if (isEmpty(id)) {
            map.put("user_id", BaseUtil.getPkCode());
            dao.addSysUser(map);
        } else {
            dao.updSysUser(map);
        }
    }

    public void delSysUser(String user_id) {
        dao.delSysUser(user_id);
    }

    public Map<String, Object> getSysUser(String user_id) {
        return dao.getSysUser(user_id);
    }

    public PageResultVO findSysUserPage(Map<String, String> param, PageVO pageVO) throws ServiceException {
        PageResultVO vo = doPagedQuery(dao, "findSysUserPage", param, pageVO);
        
        List<Map> list = (List<Map>) vo.getRecords();
        for (Map<String, Object> map: list) {
            String pwd = map.get("login_pwd") + "";
            map.put("login_pwd", SecurityUtil.decode(pwd));
        }
        return vo;
    }

    public void setSysUserStatus(String user_id, String user_status) {
        dao.setSysUserStatus(user_id, user_status);
    }

    //Dict
    public void saveDict(Map<String, Object> map) { //Add or Update
        Object id = map.get("dict_id");
        if (isEmpty(id)) {
            map.put("dict_id", BaseUtil.getPkCode());
            dao.addDict(map);
        } else {
            dao.updDict(map);
        }
    }

    public void delDict(String dictId) {
        //Check is used
        Map<String, Object> map = dao.getDict(dictId);
        if (map == null) {
            throw new ServiceException("Dictionary has been deleted");
        }
        String dictType = map.get("dict_type") + "";
        if (DICTTYPE_CUSTOMER_TYPE.equals(dictType)) {
            if (dao.getDictCustomerTypeRefCount(dictId) > 0) {
                throw new ServiceException(ERR_DICT_CUSTOMERTYPE_USED);
            }
        } else if (DICTTYPE_ITEM_TYPE.equals(dictType)) {
            if (dao.getItemTypeRefCount(dictId) > 0) {
                throw new ServiceException(ERR_DICT_ITEMTYPE_USED);
            }
        } else if (DICTTYPE_HINT_TYPE.equals(dictType)) {
            if (dao.getHintTypeRefCount(dictId) > 0) {
                throw new ServiceException(ERR_DICT_HINTTYPE_USED);
            }
        } else if (DICTTYPE_HINT_LEVEL.equals(dictType)) {
            if (dao.getHintLevelRefCount(dictId) > 0) {
                throw new ServiceException(ERR_DICT_LEVLEID_USED);
            }
            if (dao.getHintLevelRoleRefCount(dictId) > 0) {
                throw new ServiceException(ERR_DICT_LEVLEID_ROLE_USED);
            }
        }
            
        dao.delDict(dictId);
    }

    public Map<String, Object> getDict(String dictId) {
        return dao.getDict(dictId);
    }

    public PageResultVO findDictPage(Map<String, String> param, PageVO pageVO) throws ServiceException {
        PageResultVO result = doPagedQuery(dao, "findDictPage", param, pageVO);
        formateDateTime((List) result.getRecords(), "create_time");
        return result;
    }

    private void formateDateTime(List<Map<String, Object>> list, String... fieldNames) {
        for (Map map : list) {
            for (String fieldName : fieldNames) {
                map.put(fieldName, BaseUtil.formatDateTime((java.sql.Timestamp) map.get(fieldName)));
            }
        }
    }

    
    /*
        新增线索(APP 端)  //saveHintFromAppUser
            图片短路径: imageObjNames: "xx,xx"  //逗号分隔

        线索详情 (APP 端 + 管理后台) //getHintImages()
            图片短路径: imageObjNames: "xx,xx,xx"
            图片长路径: images: "xx,xx,xx"
     */
    //valid_starttime: the time of User from MiniApp asked the Hint
    public void saveHintFromAdmin(Map<String, Object> map) { //Add or Update
        /* 
        Date date1 = new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1); //Default valid date 1 month
        Date date2 = c.getTime();
        */
        log.info("{}", map);
        if (!map.containsKey("valid_starttime") || isEmpty(map.get("valid_starttime"))) {
            map.put("valid_starttime", null);
            map.put("valid_endtime", null);
        } else {
            map.put("valid_starttime", parseDateFromYMDHM(map.get("valid_starttime").toString()));
            map.put("valid_endtime", parseDateFromYMDHM(map.get("valid_endtime").toString()));
            
            /*
            Date endDate = parseDate(map.get("valid_endtime").toString());
            Calendar d = Calendar.getInstance();
            d.setTime(endDate);
            d.add(Calendar.DAY_OF_MONTH, 1);
            map.put("valid_endtime", d.getTime());
            */
        }

        Object id = map.get("hint_id");
        if (isEmpty(id)) {
            map.put("hint_id", BaseUtil.getPkCode());
            map.put("hint_from", "1"); //1-create by admin 2-creaate by app user 3-import by admin
            map.put("hint_status", "0");
            map.put("audit_status", "1");

            map.put("create_by", BaseUtil.getUserIdFromSessionId());
            dao.addHint(map);
        } else {
            dao.updHint(map);
        }
    }

    public void delHint(String hintId) {
        dao.delHint(hintId);
    }

    //Admin: Get (BasicInfo + Images) not contains Hint-Trace-Record
    public Map<String, Object> getHintDetailFromAdmin(String hintId) {
        Map<String, Object> hintMap = dao.getHint(hintId); 
        hintMap.putAll(getHintImages(hintId));
        
        return hintMap;
    }
    
    //App(Mgr + User): BaseicInfo + Images + TraceRecord + CustomerTypes
    public Map<String, Object> getHintDetail(String hintId) {
        List<Map<String, Object>> customerTypes = dao.getCustomerTypes();

        //1) Basic Info
        Map<String, Object> hintMap = dao.getHintDetail(hintId);

        hintMap.put("valid_starttime", BaseUtil.formatDateTime((java.sql.Timestamp) hintMap.get("valid_starttime")));
        hintMap.put("valid_endtime", BaseUtil.formatDateTime((java.sql.Timestamp) hintMap.get("valid_endtime")));
        
        //2) customer_types
        String customerTypeIds = hintMap.get("customer_type_ids") + "";
        if (customerTypeIds != null && !customerTypeIds.equals("")) {
            String[] strs = customerTypeIds.split("[,]");
            String names = "";
            for (String str : strs) {
                String customerTypeName = "";
                for (Map<String, Object> customerMap : customerTypes) {
                    String customerTypeId = customerMap.get("dict_id") + "";
                    if (customerTypeId.equals(str)) {
                        customerTypeName = customerMap.get("dict_name") + "";
                        break;
                    }
                }
                if (names.equals("")) {
                    names = customerTypeName;
                } else {
                    names += ", " + customerTypeName;
                }
            }
            hintMap.put("customer_type_names", names);
        }
        
        String time = BaseUtil.formatDateTime((java.sql.Timestamp) hintMap.get("update_time"));
        hintMap.put("update_time", time);

        // 3) Get ImageURL
        hintMap.putAll(getHintImages(hintId));
        
        // 4) Get HintRecord
        List<Map<String, Object>> theHintRecords = dao.theHintRecords(hintId);
        formateDateTime(theHintRecords, "create_time");

        hintMap.put("hintRecords", theHintRecords);
        return hintMap;
    }
    
    private Map<String, String> getHintImages(String hintId) {
        Map<String, String> map = new HashMap<>();
        
        String imageUrls = "", imageObjNames = "";            
        List<String> images = dao.getHintImages(hintId);
        if (images != null && images.size() > 0) {
            //hintMap.put("images", images.toString().replaceAll("[\\[\\]]", ""));
            for (String objName: images) {
                if (imageUrls.equals("")) {
                    imageUrls = userService.getFileUrl(objName);
                    imageObjNames = objName;
                } else {
                    imageUrls += "," + userService.getFileUrl(objName);
                    imageObjNames += "," + objName;
                }
            }
        }
        map.put("images", imageUrls);
        map.put("imageObjNames", imageObjNames);
        return map;
    }

    public PageResultVO findHintPage(Map<String, Object> param, PageVO pageVO) throws ServiceException {
        if ("ALL".equals(param.get("ask_status"))) {
            param.put("ask_status", "");
        }
        
        Map<String, Object> map = new HashMap<>();
        map.putAll(param);
        PageResultVO vo = doPagedQuery(dao, "findHintPage", map, pageVO);
        
        formateDateTime((List) vo.getRecords(), "create_time","ask_assign_time","valid_starttime","valid_endtime");
        
        List<Map> list = (List<Map>) vo.getRecords();
        for (Map recMap: list) {
            if (BaseUtil.isEmpty(recMap.get("user_id"))) {
                recMap.put("hint_status", "0");
            } else {
                recMap.put("hint_status", "1");
            }
        }
        return vo;
    }

    public void assignHint(String hintId, String userId) {
        int hintValidHour = 720;
        String hintValidHourStr = dao.getHintValidHour();
        if (hintValidHourStr == null) {
            log.info("Not set hint valid time, use default value: 30 days");
            hintValidHourStr = "720";
        } else {
            try {                
                hintValidHour = Integer.parseInt(hintValidHourStr);
            } catch (Exception e) {
                log.error("Failed to parse Hint Valid time: {}", e.getMessage());
                hintValidHour = 720;
            }
        }
        log.info("Hint valid hour {}", hintValidHour);
        
        Date date1 = new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, hintValidHour); //Default valid date 1 month
        Date date2 = c.getTime();
        
        dao.askHint(hintId, userId, date1, date2);
        //dao.assignHint(hintId, userId);
    }

    public void auditHint(Map<String, Object> param) {
        dao.auditHint(param);
    }

    public PageResultVO findHintRecordPage(Map<String, Object> param, PageVO pageVO) throws ServiceException {
        Map<String, Object> map = new HashMap<>();
        map.putAll(param);
        PageResultVO vo = doPagedQuery(dao, "findHintRecordPage", map, pageVO);
        
        formateDateTime((List) vo.getRecords(), "create_time");
        return vo;
    }

    //Download Hint Template
    public void downloadHintTemplate() {
        String hintTemplatePath = appConf.getConfigPath() + "HintTemplate.xlsx";
        File file = new File(hintTemplatePath);
        try {
            IoUtil.writeFile2Response(file);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    String findDictId(String dictType, String dictName, List<Map<String, String>> dicts) {
        String dictId = "";
        for (Map<String, String> map : dicts) {
            String dict_type = map.get("dict_type");
            String dict_name = map.get("dict_name");
            String dict_id = map.get("dict_id");
            if (dict_type.equals(dictType) && dict_name.equals(dictName)) {
                dictId = dict_id;
                break;
            }
        }
        return dictId;
    }

    //@dictNames: AA,BB  @Return dictIds: 01,02
    String findCustomerIds(String dictNames, List<Map<String, String>> dicts) {
        String[] names = dictNames.split("[,，]");
        String dictIds = "";
        for (Map<String, String> map : dicts) {
            String dict_type = map.get("dict_type");
            String dict_name = map.get("dict_name");
            String dict_id = map.get("dict_id");

            for (String dictName : names) {
                if (dict_type.equals("01") && dict_name.equals(dictName)) {
                    dictIds += "".equals(dictIds) ? dict_id : "," + dict_id;

                }
            }
        }
        return dictIds;
    }

    public void importHints(MultipartFile in) {
        List<Map<String, String>> dicts = dao.findDicts();

        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(in.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(0);

            int lastRow = sheet.getLastRowNum(); //Row [first, last] 0,1,2,3 Cell[first, last) 0,1,2,3
            for (int i = 1; i <= lastRow; i++) {
                XSSFRow row = sheet.getRow(i);
                String levelName = row.getCell(0).getStringCellValue();
                String typeName = row.getCell(3).getStringCellValue();
                String itemName = row.getCell(4).getStringCellValue();
                String projectName = row.getCell(5).getStringCellValue();
                String custTypes = row.getCell(11).getStringCellValue();

                String hintName = row.getCell(1).getStringCellValue();
                String corpName = row.getCell(2).getStringCellValue();
                String province = row.getCell(6).getStringCellValue();
                String city = row.getCell(7).getStringCellValue();
                String district = row.getCell(8).getStringCellValue();
                String addr = row.getCell(9).getStringCellValue();
                String custName = row.getCell(10).getStringCellValue();
                String contact = (row.getCell(12).getCellType() == Cell.CELL_TYPE_NUMERIC) ? (long) row.getCell(12).getNumericCellValue() + ""
                        : row.getCell(12).getStringCellValue();
                String remark = row.getCell(13).getStringCellValue();

                Map<String, Object> map = new HashMap<>();
                map.put("hint_id", BaseUtil.getPkCode());
                map.put("hint_from", "3"); //1-create by admin 2-creaate by app user 3-import by admin
                map.put("hint_status", "0"); //0=unAssign 1=assigned
                map.put("audit_status", "1"); //0=reject 1=pass

                map.put("hint_name", hintName);
                map.put("remark", remark);
                map.put("corp_name", corpName);
                map.put("corp_address", addr);
                map.put("province", province);
                map.put("city", city);
                map.put("district", district);
                map.put("customer_name", custName);
                map.put("customer_contact", contact);

                String projectId = dao.getProjectId(projectName);
                String itemId = findDictId("02", itemName, dicts);
                String typeId = findDictId("03", typeName, dicts);
                String levelId = findDictId("04", levelName, dicts);
                String customerTypeIds = findCustomerIds(custTypes, dicts);
                map.put("project_id", projectId);
                map.put("item_type_id", itemId);
                map.put("hint_type_id", typeId);
                map.put("hint_level_id", levelId);
                map.put("customer_type_ids", customerTypeIds);

                map.put("create_by", BaseUtil.getUserIdFromSessionId());

                dao.addHint(map);
            }
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            close(wb);
        }
    }

    void close(Closeable wb) {
        if (wb != null) {
            try {
                wb.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public PageResultVO findUserPage(String userName, String roleName, String tel, String invite_code, String role_name, PageVO pageVO) throws ServiceException {
        Map<String, String> param = new HashMap<>();
        param.put("user_name", userName);
        param.put("role_name", roleName);
        param.put("tel", tel);
        param.put("invite_code", invite_code);
        param.put("role_name", role_name);

        PageResultVO result = doPagedQuery(dao, "findUserPage", param, pageVO);
        
        List<Map<String, Object>> list = (List<Map<String, Object>>) result.getRecords();
        for (Map<String, Object> map: list) {
            //1 AvatarUrl
            Object avatarObjectName = map.get("avatarObjectName");
            if (!BaseUtil.isEmpty(avatarObjectName)) {
                String url = userService.getFileUrl(avatarObjectName + "");
                map.put("avatarUrl", url);
            }
            //2
            map.put("create_time", BaseUtil.formatDateTime((java.sql.Timestamp) map.get("create_time")));
            //3
            Object rolePosition = map.get("role_position");
            String superior_user_name = "";
            if ("3".equals(rolePosition) || "4".equals(rolePosition)) {
                superior_user_name = map.containsKey("project_admin_username") ? map.get("project_admin_username") + "" : "";
            } else if ("2".equals(rolePosition)) {
                superior_user_name = map.containsKey("area_admin_username") ? map.get("area_admin_username") + "" : "";
            }
            map.put("superior_user_name", superior_user_name);
        }

        return result;
    }

    public void setUserStatus(String user_id, String user_status) throws ServiceException {
        dao.setUserStatus(user_id, user_status);
    }
    
    public void resetUser(String userId) {
        dao.resetUser(userId);
    }

    /* Invite Code  */
    public PageResultVO findInviteCodePage(String role_id, String code_status, String project_name, String invite_code, PageVO pageVO) throws ServiceException {
        Map<String, String> param = new HashMap<>();
        param.put("role_id", role_id);
        param.put("code_status", code_status);
        param.put("project_name", project_name);
        param.put("invite_code", invite_code);

        PageResultVO vo = doPagedQuery(dao, "findInviteCodePage", param, pageVO);
        formateDateTime((List) vo.getRecords(), "create_time", "used_time");
        return vo;
    }

    public void discardInviteCode(String invite_code) {
        dao.discardInviteCode(invite_code);
    }

    public void generateInviteCodes(String project_id, String role_id, int count) {
        List<String> codes = dao.getAllInviteCodes();

        int i = 0;
        while (i < count) {
            String str = BaseUtil.getPkCode();
            int length = 8, k = 0, max = 32 - length;
            while (i < count & k <= max) {
                String code = str.substring(k, k + 8);
                if (!codes.contains(code)) {
                    dao.addInviteCode(code, project_id, role_id);
                    codes.add(code);
                    i++;
                }
                k++;
            }
        }
    }

    //Role
    public PageResultVO findRolesPage(Map<String, String> param, PageVO pageVO) throws ServiceException {
        PageResultVO vo = doPagedQuery(dao, "findRolesPage", param, pageVO);
        formateDateTime((List) vo.getRecords(), "create_time");
        return vo;
    }

    public void saveRole(Map<String, String> map) { //Add or Update
        Object id = map.get("role_id");
        if (isEmpty(id)) {
            map.put("role_id", BaseUtil.getPkCode());
            dao.addRole(map);
        } else {
            dao.updRole(map);
        }
    }

    public void delRole(String id) {
        int count = dao.getUserCountByRoleId(id);
        if (count > 0) {
            throw new ServiceException(ERR_ROLE_USED_BY_USER);
        }
        dao.delRole(id);
    }

    //App Manager
    public List<Map<String, Object>> findProjectsByAppMgr(String projectName, String userId) {
        Map<String, String> map = new HashMap<>();
        
        UserSession u;
        if (userId == null || "".equals(userId)) {            
            u = BaseUtil.getUserInfoFromSessionId();
        } else {
            Map<String, Object> user = dao.getUserByUserId(userId);
            String user_id = user.get("user_id") + "";
            String positon = user.get("role_position") + "";
            u = new UserSession(user_id, positon);
        }
        
        if ("1".equals(u.getRolePosition())) {
            map.put("project_area_admin_id", u.getUserId());
        } else if ("2".equals(u.getRolePosition())) {
            map.put("project_admin_id", u.getUserId());
        }
        
        log.info("ProjectName {} {}", (projectName == null), projectName);
        map.put("project_name", projectName);

        List<Map<String, Object>> list = dao.findProjectsByAppMgr(map);
        formateDateTime(list, "createTime");

        return list;
    }
    
    public List<Map<String, Object>> findRunningProjects() {
        List<Map<String, Object>> list = dao.findRunningProjects();
        formateDateTime(list, "createTime");

        return list;
    }

    //Find Hints that: 1) Is my project; 2) Audited
    public PageResultVO findAppMgrHintPage(String projectId, String projectName, String hintStatus, PageVO pageVO) throws ServiceException {
        Map<String, String> param = new HashMap<>();
        UserSession us = BaseUtil.getUserInfoFromSessionId();
        if ("1".equals(us.getRolePosition())) {
            param.put("project_area_admin_id", us.getUserId());
        } else if ("2".equals(us.getRolePosition())) {            
            param.put("project_admin_id", us.getUserId());
        }
        if (!BaseUtil.isEmpty(projectId)) {
            param.put("project_id", projectId);
        }
        if (!BaseUtil.isEmpty(projectName)) {
            param.put("project_name", projectName);
        }        
        if (!BaseUtil.isEmpty(hintStatus)) {
            param.put("hintStatus", hintStatus);
        }        

        PageResultVO result = doPagedQuery(dao, "findAppMgrHintPage", param, pageVO);
        formateDateTime((List) result.getRecords(), "update_time");

        return result;
    }

    public PageResultVO findHintsByAskedUserPage(String userId, String userName, PageVO pageVO) throws ServiceException {
        Map<String, String> param = new HashMap<>();
        param.put("user_id", userId);
        param.put("user_name", userName);

        return doPagedQuery(dao, "findHintsByAskedUserPage", param, pageVO);
    }

    public PageResultVO findHintsByAddedUserPage(String userId, String userName, PageVO pageVO) throws ServiceException {
        Map<String, String> param = new HashMap<>();
        param.put("user_id", userId);
        param.put("user_name", userName);

        return doPagedQuery(dao, "findHintsByAddedUserPage", param, pageVO);
    }

    public List<Map<String, Object>> findUsers(String projectId) {
        return dao.findUsers(projectId);
    }

    /*
    成员列表(这个接口必须是 项目管理员 或者 区域管理调用)
    
    成员列表-销售员 /members?projectId=xx&rolePosition=3&orderColumn=ADD&orderType=U
    成员列表-拓客员 /members?projectId=xx&rolePosition=4&orderColumn=ADD&orderType=U
    成员列表-管理员 /members?projectId=xx&rolePosition=2
     
    参数说明：
        orderField: 排序字段 ADD=新增线索 TRACE=认领线索
        orderType:  排序顺序 U=升序 D=降序
        projectId:  项目ID
     */
    public PageResultVO findMembersPage(ParamObj paramObj, PageVO pageVO) {
        log.info("members param: {}", paramObj);
        String projectIds = "";
        if (paramObj.getProjectId() == null || paramObj.getProjectId().equals("")) {
            projectIds = getCurrentAdminProjectIds();
        } else {
            projectIds = "'" + paramObj.getProjectId() + "'";
        }
        log.info("Current User ProjectIds {}", projectIds);
        
        Map<String, String> param = new HashMap<>();
        param.put("project_ids", projectIds);

        if ("2".equals(paramObj.getRolePosition())) {
            PageResultVO result = doPagedQuery(dao, "findProjectAdminUsersByAreaAdminPage", param, pageVO);
            setAvatarUrl(result.getRecords());
            
            int projectCount = dao.getAreaAdminProjectCount(BaseUtil.getUserIdFromSessionId());
            result.getPageVO().setFlag(projectCount + "");
            return result;
        } else {
            String rolePosition = "3".equals(paramObj.getRolePosition()) ? "3" : "4";
            String orderColumn = "TRACE".equals(paramObj.getOrderColumn()) ? " order by B.RecordedCount " : " order by A.AddedCount ";
            orderColumn += "D".equals(paramObj.getOrderType()) ? " DESC " : " ASC ";
            
            param.put("order_column", orderColumn);
            param.put("role_position", rolePosition);

            PageResultVO result = doPagedQuery(dao, "findNormalUsersByProjectAdminPage", param, pageVO);
            setAvatarUrl(result.getRecords());
            return result;
        }
    }
    
    //Get projects of ProjectAdmin/AreaAdmin, return '001','002'
    private String getCurrentAdminProjectIds() throws ServiceException {
        String projectIds = "";
        
        List<String> list = null;
        UserSession u = BaseUtil.getUserInfoFromSessionId();
        if ("1".equals(u.getRolePosition())) {
            list = dao.getProjectIdsByAreaAdmin(u.getUserId());
        } else if ("2".equals(u.getRolePosition())) {
            list = dao.getProjectIdsByProjectAdmin(u.getUserId()); 
        } else {
            log.error("Current User is not ProjectAdmin or AreaAdmin");
            throw new ServiceException("USER_IS_NOT_PROJECT_ADMIN_AREA_ADMIN");
        }
        if (list == null || list.size() == 0) {
            throw new ServiceException("USER_HAS_NO_PROJECT");
        }
        
        for (String str: list) {
            if (projectIds.equals("")) {
                projectIds = "'" + str + "'";
            } else {
                projectIds += ",'" + str + "'";
            }
        }
        return projectIds;
    }

    //Map(projectCount, projectAdminCount)
    public Map<String, Object> findProjectAdminCountAndProjectCount(String projectId) {
        return dao.findProjectAdminCountAndProjectCount(projectId);
    }

    //Project Amin Detail
    public Map<String, Object> projectAdminDetail(String project_admin_id) {
        return dao.projectAdminDetail(project_admin_id);
    }

    //User Hints
    public Map<String, Object> userHints(String userId) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> added = dao.userAddedHints(userId);
        List<Map<String, Object>> asked = dao.userAskedHints(userId);
        formateDateTime(added, "create_time");
        formateDateTime(asked, "asked_time");

        map.put("addedHints", added);
        map.put("askedHints", asked);

        return map;
    }

    //Personal Center
    public Map<String, Object> myInfo() {
        Map<String, Object> map = new HashMap<>();

        UserSession u = BaseUtil.getUserInfoFromSessionId();
        String userId = u.getUserId();
        String position = u.getRolePosition();

        Map<String, Object> userMap = dao.getUserByUserId(u.getUserId());
        map.put("userName", userMap.get("user_name") + "");
        map.put("roleName", userMap.get("role_name") + "");
        map.put("role_position", userMap.get("role_position") + "");
        map.put("project_name", userMap.get("project_name") + "");
        
        Object avatarObjectName = userMap.get("avatarObjectName");
        if (!BaseUtil.isEmpty(avatarObjectName)) {
            String url = userService.getFileUrl(avatarObjectName + "");
            map.put("avatarUrl", url);
        } else {
            map.put("avatarUrl", "");            
        }
        
        //For ProjectAdmin and AreaAdmin
        List<Map<String, Object>> memberCountMapList = null;
        if ("2".equals(position)) {
            map.put("projectCount", dao.getProjectAdminProjectCount(userId));

            memberCountMapList = dao.getProjectAdminMemberCount(userId);
        } else if ("1".equals(position)) {
            map.put("projectCount", dao.getAreaAdminProjectCount(userId));
            map.put("projectAdminCount", dao.getProjectAdminCountOfAreaAdmin(userId));
            memberCountMapList = dao.getAreaAdminMemberCount(userId);
        }

        if (memberCountMapList != null) {
            for (Map<String, Object> map1 : memberCountMapList) {
                if ("3".equals(map1.get("role_position"))) {
                    map.put("saleCount", map1.get("userCount"));
                } else if ("4".equals(map1.get("role_position"))) {
                    map.put("spreaderCount", map1.get("userCount"));
                }
            }
        }
        if ("1".equals(position) || "2".equals(position)) {            
            if (!map.containsKey("saleCount")) {
                map.put("saleCount", 0);
            }
            if (!map.containsKey("spreaderCount")) {
                map.put("spreaderCount", 0);
            }
        }
        
        //For Sale and Extension
        //if ("3".equals(position) || "4".equals(position)) {
        map.put("addedHintsCount", dao.getAddedHintsCount(userId));
        map.put("tracedHintsCount", dao.getTracedHintsCount(userId));

        return map;
    }
    
    /* App/User */
    public PageResultVO findPublicHintsPage(String item_type_name, PageVO pageVO) throws ServiceException {
        String userId = BaseUtil.getUserIdFromSessionId();
        Map<String, String> param = new HashMap<>();
        param.put("user_id", userId);
        param.put("item_type_name", item_type_name);
        
        PageResultVO vo = doPagedQuery(dao, "findPublicHintsPage", param, pageVO);
        formateDateTime((List) vo.getRecords(), "update_time");
        
        return vo;
    }     

    public void askHint(String hintId) {
        int hintValidHour = 720;
        String hintValidHourStr = dao.getHintValidHour();
        if (hintValidHourStr == null) {
            log.info("Not set hint valid time, use default value: 30 days");
            hintValidHourStr = "720";
        } else {
            try {                
                hintValidHour = Integer.parseInt(hintValidHourStr);
            } catch (Exception e) {
                log.error("Failed to parse Hint Valid time: {}", e.getMessage());
                hintValidHour = 720;
            }
        }
        log.info("Hint valid hour {}", hintValidHour);
        
        Date date1 = new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, hintValidHour); //Default valid date 1 month
        Date date2 = c.getTime();
        
        String userId = BaseUtil.getUserIdFromSessionId();
        dao.askHint(hintId, userId, date1, date2);
    }
    
    public void freeHint(String hintId) {
        dao.freeHint(hintId);
    }
    
    public PageResultVO myHintsPage(String userId, String itemTypeName, String myHintType, String auditStatus, PageVO pageVO) throws ServiceException {
        if (userId == null || "".equals(userId)) {
            userId = BaseUtil.getUserIdFromSessionId();
        }
        Map<String, String> param = new HashMap<>();
        param.put("user_id", userId);
        param.put("my_hint_type", myHintType);
        param.put("item_type_name", itemTypeName);
        param.put("audit_status", auditStatus);

        PageResultVO vo = doPagedQuery(dao, "myHintsPage", param, pageVO);
        
        formateDateTime((List) vo.getRecords(), "create_time", "update_time", "valid_starttime","valid_endtime");
        
        for (Map map : (List<Map>) vo.getRecords()) {
            if (map.containsKey("valid_starttime") && map.containsKey("valid_endtime")) {                
                map.put("valid_timeRage", map.get("valid_starttime") + "-" + map.get("valid_endtime"));
            }
        }
        
        return vo;
    }
    
    //Add Hint by App/User
    public void saveHintFromAppUser(Map<String, Object> map) { //Add or Update
        log.info("{}", map);
        //1) Save Hint
        if (!map.containsKey("valid_starttime") || isEmpty(map.get("valid_starttime"))) {
            map.put("valid_starttime", null);
            map.put("valid_endtime", null);
        } else {
            if (map.containsKey("valid_starttime") && !isEmpty(map.get("valid_starttime")) && map.containsKey("valid_endtime") && !isEmpty(map.get("valid_endtime"))  ) {
                map.put("valid_starttime", parseDate(map.get("valid_starttime").toString()));
                map.put("valid_endtime", parseDate(map.get("valid_endtime").toString()));
            }
        }
        
        String hintId = "";
        if (map.containsKey("hint_id")) {
            hintId = map.get("hint_id") + "";            
        }
        if (isEmpty(hintId)) {
            String userId = BaseUtil.getUserIdFromSessionId();
            
            Object projectId = map.get("project_id");
            if (projectId == null || "".equals(projectId.toString().trim())) {
                //Get current user project_id
                Map<String, Object> userMap = dao.getUserByUserId(userId);
                Object project_id = userMap.get("project_id");
                map.put("project_id", project_id);
            }
            
            hintId = BaseUtil.getPkCode();
            map.put("hint_id", hintId);
            map.put("hint_from", "2"); //1-create by admin 2-creaate by app user 3-import by admin
            map.put("hint_status", "0");  // 0-no-assigned 1-assigned
            map.put("audit_status", "0"); // 0-auditing 1-Pass 2-Reject

            map.put("create_by", userId);
            dao.addHint(map);
        } else {
            dao.updHint(map);
        }
        
        //2) Save Images
        dao.delHintImg(hintId);
        if (map.containsKey("imageObjNames")) {
            String imageObjNames = map.get("imageObjNames") + "";
            if (imageObjNames != null && !imageObjNames.equals("")) {
                String[] imageArr = imageObjNames.split(",");
                for (String imgObjName: imageArr) {
                    dao.addHintImg(BaseUtil.getPkCode(), hintId, imgObjName);        
                }
            }
        }
    }    
    
    public void updHintRemark(Map<String, String> map) {
        String hintId = map.get("hint_id");
        String remark = map.get("remark");
        dao.updHintRemark(hintId, remark);
    }
    
    public void addHintRecord(Map<String, String> param) {
        String userId = BaseUtil.getUserIdFromSessionId();
        Map<String, Object> user = dao.getUserByUserId(userId);
        String projectId = user.get("project_id") + "";
        
        param.put("hint_record_id", BaseUtil.getPkCode());
        param.put("user_id", userId);
        param.put("project_id", projectId);
        dao.addHintRecord(param);
    }
    
    public PageResultVO findAddedHintsPage(PageVO pageVO) throws ServiceException {
        return doPagedQuery(dao, "findPublicHintsPage", new HashMap<String, String>(), pageVO);
    }    
    
    //Ranking
    public Map<String, Object> rank(PageVO pageVO, String type) {
        return "1".equals(type) ? getAddedHintsCountRankPage(pageVO) : getTracedHintsCountRankPage(pageVO);
    }
    
    public Map<String, Object> getAddedHintsCountRankPage(PageVO pageVO) throws ServiceException {
        Map<String, Object> userMap = dao.getUserByUserId(BaseUtil.getUserIdFromSessionId());
        String projectId = userMap.get("project_id") + "";

        Map<String, String> param = new HashMap<>();
        param.put("project_id", projectId);
        
        //Map<String, String>
        PageResultVO vo = doPagedQuery(dao, "getAddedHintsCountRankPage", param, pageVO);
        //Add Ranking No.
        String myRank = setAndGetRanking(vo);
        
        if ("".equals(myRank)) {
            int pageCount = 0;
            if (pageVO.getRecordCount() % pageVO.getPageSize() > 0) {
                pageCount = pageVO.getRecordCount() / pageVO.getPageSize() + 1;
            } else {
                pageCount = pageVO.getRecordCount() / pageVO.getPageSize();
            }
            for (int i = 1; i <= pageCount; i++) {
                PageVO page = new PageVO(pageVO.getPageSize(), pageVO.getPageIndex());
                page.setPageIndex(i);
                PageResultVO tmp = doPagedQuery(dao, "getAddedHintsCountRankPage", param, page);
                myRank = setAndGetRanking(tmp);
                if (!"".equals(myRank)) {
                    break;
                }
            }
        }
        
        setAvatarUrl(vo.getRecords());
        
        Map<String, Object> map = new HashMap<>();
        map.put("myRank", myRank);
        map.put("pageResult", vo);
        return map;
    } 
    
    private void setAvatarUrl(Object records) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) records;
        for (Map<String, Object> map: list) {
            //1 AvatarUrl
            Object avatarObjectName = map.get("avatarObjectName");
            if (!BaseUtil.isEmpty(avatarObjectName)) {
                String url = userService.getFileUrl(avatarObjectName + "");
                map.put("avatarUrl", url);
            } else {
                map.put("avatarUrl", "");
            }
        }
    }
    
    public Map<String, Object> getTracedHintsCountRankPage(PageVO pageVO) throws ServiceException {
        Map<String, Object> userMap = dao.getUserByUserId(BaseUtil.getUserIdFromSessionId());
        String projectId = userMap.get("project_id") + "";
        
        Map<String, String> param = new HashMap<>();
        param.put("project_id", projectId);
        
        PageResultVO vo = doPagedQuery(dao, "getTracedHintsCountRankPage", param, pageVO);
        //Add Ranking No.
        String myRank = setAndGetRanking(vo);
        
        if ("".equals(myRank)) {
            int pageCount = 0;
            if (pageVO.getRecordCount() % pageVO.getPageSize() > 0) {
                pageCount = pageVO.getRecordCount() / pageVO.getPageSize() + 1;
            } else {
                pageCount = pageVO.getRecordCount() / pageVO.getPageSize();
            }
            for (int i = 1; i <= pageCount; i++) {
                PageVO page = new PageVO(pageVO.getPageSize(), pageVO.getPageIndex());
                page.setPageIndex(i);
                PageResultVO tmp = doPagedQuery(dao, "getTracedHintsCountRankPage", param, page);
                myRank = setAndGetRanking(tmp);
                if (!"".equals(myRank)) {
                    break;
                }
            }
        }
        
        setAvatarUrl(vo.getRecords());
        
        Map<String, Object> map = new HashMap<>();
        map.put("myRank", myRank);
        map.put("pageResult", vo);
        
        log.info("getTracedHintsCountRankPage202202160703 {}", vo.getRecords());
        return map;
    }
    
    //Set ranking and current user ranking
    private String setAndGetRanking(PageResultVO vo) {
        String currRank = "";
        
        String currUserId = BaseUtil.getUserIdFromSessionId();
        PageVO pageVO = vo.getPageVO();
        List<Map<String, Object>> list = (List<Map<String, Object>> ) vo.getRecords();
        for (int i = 0; i < list.size(); i++) {
            int rank = pageVO.getPageSize() * (pageVO.getPageIndex() - 1) + i + 1;
            Map<String, Object> map = list.get(i);
            map.put("rank", rank + "");
            
            if (currUserId.equals(map.get("user_id"))) {
                //pageVO.setFlag(rank + "");
                currRank = rank + "";
            }
        }
        
        return currRank;
    }
    
    // Statics
    public Map<String, Object> statisTotal() {
        Map<String, Object> resultMap = dao.staticsTotalCount();
        
        List<Map<String, Object>> salesBuildList = dao.staticsTotalSaleBuildCount();
        for (Map<String, Object> map: salesBuildList) {
            if (map.get("role_position").toString().equals("3")) {
                resultMap.put("totalSalers", map.get("userCount"));
            } else if (map.get("role_position").toString().equals("4")) {
                resultMap.put("totalSpreaders", map.get("userCount"));
            } 
        }
        
        if (!resultMap.containsKey("totalSalers")) {
            resultMap.put("totalSalers", 0L);
        } 
        if (!resultMap.containsKey("totalSpreaders")) {
            resultMap.put("totalSpreaders", 0L);
        }
        
        return resultMap;
    }
    
    public List<StatisHintVO> statisticHints(String date1, String date2) {
        List<StatisHintVO> list = new ArrayList<>();

        List<Map<String, Object>> askHintList = dao.staticsAskHintCount(date1, date2);
        List<Map<String, Object>> traceHintMap = dao.staticsTraceHintCount(date1, date2);
        
        List<String> dateList = getTime(date1, date2);
        for (int i = 0; i < dateList.size(); i++) {
            String date = dateList.get(i);
            
            long askHintCount = 0;
            for (int j = 0; j < askHintList.size(); j++) {
                Map<String, Object> map = askHintList.get(j);
                String key = map.get("YearMonth") + "";
                if (key.equals(date)) {
                    askHintCount = (Long) map.get("askHintCount");
                }
            }
            
            long traceHintCount = 0;
            for (int j = 0; j < traceHintMap.size(); j++) {
                Map<String, Object> map = traceHintMap.get(j);
                String key = map.get("YearMonth") + "";
                if (key.equals(date)) {
                    traceHintCount = (Long) map.get("traceHintCount");
                }
            }
            list.add(new StatisHintVO(date, askHintCount, traceHintCount));
        }
        
        return list;
    }
    
    //date1: 2021-10-03
    private List<String> getTime(String date1, String date2) {
        //String date1 = "2021-10-18", date2 = "2021-11-13";
        List<String> dateList = new ArrayList<>(); // 10.09
        try {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("MM.dd");
            Calendar c1 = Calendar.getInstance(), c2 = Calendar.getInstance();
            c1.setTime(dateTimeFormat.parse(date1));
            c2.setTime(dateTimeFormat.parse(date2));
            while (c1.before(c2)) {
                dateList.add(format2.format(c1.getTime()));
                c1.add(Calendar.DAY_OF_MONTH, 1);
            }
            dateList.add(format2.format(c2.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return dateList;
    }
    
    public List<StatisUserVO> statisticUsers(String date1, String date2) {
        List<StatisUserVO> list = new ArrayList<>();
        
        List<Map<String, Object>> totalList = dao.staticsUserCount(date1, date2);
        List<Map<String, Object>> othersList = dao.staticsSalesBuildCount(date1, date2);

        List<String> dateList = getTime(date1, date2);
        for (int i = 0; i < dateList.size(); i++) {
            String date = dateList.get(i);
            
            long totalUserCount = 0;
            for (int j = 0; j < totalList.size(); j++) {
                Map<String, Object> map = totalList.get(j);
                String key = map.get("YearMonth") + "";
                if (key.equals(date)) {
                    totalUserCount = (Long) map.get("count");
                }
            }
            
            long salesCount = 0, spreadCount = 0;
            for (int j = 0; j < othersList.size(); j++) {
                Map<String, Object> map = othersList.get(j);
                String key = map.get("YearMonth") + "";
                if (key.equals(date)) {
                    if ("3".equals(map.get("role_position"))) {
                        salesCount = (Long) map.get("count");
                    }
                    if ("4".equals(map.get("role_position"))) {
                        spreadCount = (Long) map.get("count");
                    }
                }
            }
            list.add(new StatisUserVO(date, totalUserCount, salesCount, spreadCount));
        }
        
        return list;
    }
    
    
}
