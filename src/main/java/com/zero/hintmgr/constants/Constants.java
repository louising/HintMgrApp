package com.zero.hintmgr.constants;

public interface Constants {
    boolean LOGIN_ENABLE = false;
    boolean XSRF_ENABLE = false;
    
    String STATUS_OK        = "200"; //Success
    String STATUS_ERR       = "500"; //Server error
    
    String DICTTYPE_CUSTOMER_TYPE = "01";
    String DICTTYPE_ITEM_TYPE = "02";
    String DICTTYPE_HINT_TYPE = "03";
    String DICTTYPE_HINT_LEVEL = "04";
    
    //Register and Login
    String ERR_INVALID_ADMIN_PWD = "400_ERR_INVALID_ADMIN_PWD";
    String ERR_REGISTER_FIELD_NULL = "401_USERNAME_TEL_CODE_INVITE_CODE_IS_NULL";
    String ERR_INVALID_INVITE_CODE = "402_ERR_INVALID_INVITE_CODE";
    String ERR_USER_NOT_REGISTERED = "403_ERR_USER_NOT_REGISTERED";
    String ERR_NOT_LOGIN = "404_NOT_LOGIN"; //Not Login
    String ERR_USER_INACTIVE = "405_ERR_USER_INACTIVE"; //InActive
    String ERR_INVALID_SESSION = "406_ERR_INVALID_SESSION";
    
    String ERR_DICT_CUSTOMERTYPE_USED = "5101";
    String ERR_DICT_ITEMTYPE_USED = "5102";
    String ERR_DICT_HINTTYPE_USED = "5103";
    String ERR_DICT_LEVLEID_USED = "5104";
    String ERR_DICT_LEVLEID_ROLE_USED = "5105";
    
    String ERR_SYS_ROLE_USED_BY_USER = "5201";
    String ERR_ROLE_USED_BY_USER = "5202";
    
        
    String KEY_USER_STATUS = "user_status";
    String ERR_USER_STATUS_5_NO_USER = "5";
    String ERR_USER_STATUS_6_USER_CLOSED = "6";
    String ERR_USER_STATUS_7_INVALID_CODE = "7";
    String ERR_USER_STATUS_8_INVITECODE_USED = "8";
    String ERR_USER_STATUS_9_INVITECODE_DISCARDED = "9";
    String ERR_USER_STATUS_10_OPENID_REGISTED = "10";
    String ERR_USER_NO_PROJECT= "5301";
    
    

    String SESSION_ID     = "sessionId";   //Defense XSRF
    String AUTHRORIES = "AUTHRORIES";
    String ROOT_AUTHORIES = "[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\",\"/customerType\",\"/projectManage\",\"/projectType\",\"/roleManage\",\"/codeList\",\"/userList\",\"/roleList\",\"/total\",\"/publicSet\"]";
    String RESPONSE_TYPE    = "application/json;charset=UTF-8"; //Response MIME type
    String SESSION_USER_KEY = "SESSION_USER_KEY"; 
    
    String EXTENSION_ZIP         = ".zip";
    String EXTENSION_EXCEL_MACRO = ".xlsm";
    String EXTENSION_EXCEL       = ".xlsx";
    
    String DIR_CURR    = ".";
    String DIR_PARENET = "..";
    
    // Dict(01=CustomerType 02=ItemType 03=HintType 04=HintLevel)
    String DICT_CUSTOMER_TYPE = "01";
    String DICT_ITEM_TYPE = "02";
    String DICT_HINT_TYPE = "03";
    String DICT_HINT_LEVEL = "04";
    
    //static ConcurrentMap<String, String> sessionMap = new ConcurrentHashMap<>();
}
