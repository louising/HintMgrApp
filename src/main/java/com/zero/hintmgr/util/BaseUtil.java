package com.zero.hintmgr.util;

import static com.zero.hintmgr.constants.Constants.DIR_PARENET;
import static com.zero.hintmgr.constants.Constants.ERR_INVALID_SESSION;
import static com.zero.hintmgr.constants.Constants.SESSION_ID;
import static com.zero.hintmgr.constants.I18NConstants.ERR_FAIL;
import static com.zero.hintmgr.util.SecurityUtil.decode;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zero.hintmgr.ServiceException;
import com.zero.hintmgr.vo.UserSession;

public class BaseUtil {
    private static Logger log = LoggerFactory.getLogger(BaseUtil.class);
    static NumberFormat numberFormat = NumberFormat.getNumberInstance();
    static {
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
    }
    static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static String getPkCode() {
        String token = UUID.randomUUID().toString();
        return token.replace("-", ""); //32-bits
    }
    
    public static String getSessionId(String userId, String rolePosition) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        //map.put("loginTime", String.format("%1$tF %1$tF %1$tT", System.currentTimeMillis())); //%1$tF %1$tT
        map.put("rolePosition", rolePosition);
        String str = JSONObject.toJSONString(map);
        
        String encoded = SecurityUtil.encode(str);
        //String base64Str = new String(Base64.getEncoder().encode(encoded.getBytes()));
        
        String sessionId = encoded.replaceAll("[\\s*\t\n\r]", "");
        log.info("UserId: {} rolePosition: {} Session: {}", userId, rolePosition, sessionId);
        return sessionId;
    }
    
    public static UserSession getUserInfoFromSessionId(String sessionId) throws ServiceException {
        try {
            String str = SecurityUtil.decode(sessionId); //new String(Base64.getDecoder().decode(sessionId.getBytes()))
            JSONObject obj = JSONObject.parseObject(str);
            
            return new UserSession(obj.getString("userId"), obj.getString("rolePosition"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ERR_INVALID_SESSION);
        } 
    }
    
    public static UserSession getUserInfoFromSessionId() throws ServiceException {
        String sessionId = HttpUtil.getHeader(SESSION_ID);  
        return getUserInfoFromSessionId(sessionId);
    }
    public static String getUserIdFromSessionId() throws ServiceException {        
        UserSession u = getUserInfoFromSessionId();
        return u.getUserId();
    }
    
    /**
    * Return date time string with fomat: 2017-10-11 13:21:30
    * @param date
    * @return
    */
    public static String formatDateTime(Date date) {
        if (date == null)
            return "";
        
        return dateTimeFormat.format(date);
    }

    /**
    * Return time string with fomat: 13:21:30
    * @param date
    * @return
    */
    public static String formatTime(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(date);
    }

    /**
    * ????????????????????? yyyy-MM-dd
    * @param dateString
    * @return
    * @throws ServiceException Date
    */
    public static Date parseDate(String dateString) throws ServiceException {
        if (BaseUtil.isEmpty(dateString)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            throw new ServiceException("ERR_DATE_FORMAT");
        }
        return date;
    }
    
    public static Date parseDateFromYMDHM(String dateString) throws ServiceException {
        if (BaseUtil.isEmpty(dateString)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            throw new ServiceException("ERR_DATE_FORMAT");
        }
        return date;
    }

    public static void close(Closeable... resources) {
        if (resources != null && resources.length > 0) {
            for (Closeable r : resources) {
                try {
                    r.close();
                } catch (IOException e) {
                    log.error("{}", e);
                }
            }
        }
    }

    /**
    * Close single resource
    * @param stream
    */
    public static void close(java.io.Closeable stream) {
        if (stream != null)
            try {
                stream.close();
            } catch (IOException e) {
                log.error("{}", e.toString());
            }
    }

    /**
    * Compare two string
    * 
    * @param str1
    * @param str2
    * @return
    */
    public int compareTo(String str1, String str2) {
        if (str1 == null) {
            if (str2 == null)
                return 0;
            else {
                return 1;
            }
        } else {
            if (str2 == null)
                return 1;
            else {
                return str1.compareTo(str2);
            }
        }
    }

    /**
    * Compare two objects that implement Comparable interface
    * @param obj1
    * @param obj2
    * @return
    */
    //Put the null to the last
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int compareTo(Comparable obj1, Comparable obj2) {
        if (obj1 == null) {
            if (obj2 == null)
                return 0;
            else {
                return 1;
            }
        } else {
            if (obj2 == null)
                return -1;
            else {
                return obj1.compareTo(obj2);
            }
        }
    }

    public static boolean isEmpty(Object str) {
        return str == null || str.toString().trim().equals("");
    }

    public static void checkParameterTrue(boolean parameter, String errCode, Object... args) throws ServiceException {
        if (!parameter)
            throw new ServiceException(errCode, args);
    }

    public static void checkFileExtension(String fileName, List<String> supportedDocTypes, String errCode, Object... args) throws ServiceException {
        if (supportedDocTypes != null) {
            checkParameterTrue(supportedDocTypes.contains(FileUtil.getExtension(fileName)), errCode, args);
        }
    }

    @SuppressWarnings("rawtypes")
    public static void checkNotNull(Object obj, String errCode) throws ServiceException {
        if (obj == null || (obj instanceof String && isEmpty((String) obj)) || (obj instanceof List && ((List) obj).isEmpty()))
            throw new ServiceException(errCode);
    }
    
    public static void checkNotNull(String errCode, String... strs) throws ServiceException {
        for (String s: strs) {
            if (isEmpty(s)) {
                throw new ServiceException(errCode);
            }
        }        
    }    

    public static void checkIsTrue(boolean parameter, String errCode) throws ServiceException {
        if (!parameter)
            throw new ServiceException(errCode);
    }

    public static Map<String, String> getSysInfo() {
        Map<String, String> map = new HashMap<>();
        
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        map.put("System Time", BaseUtil.formatDateTime(new Date()));
        map.put("OS", System.getProperty("os.name") + ", " + System.getProperty("os.arch") + ", " + System.getProperty("os.version"));
        map.put("Java", System.getProperty("java.version") + ", " + System.getProperty("java.vendor") + ", " + System.getProperty("java.home"));
        map.put("JVM", System.getProperty("java.vm.name") + ", " + System.getProperty("java.vm.version") + ", " + System.getProperty("java.vm.vendor"));
        map.put("HeapMemory", getMemoryUsageStr(mbean.getHeapMemoryUsage()));
        map.put("NonHeapMemory", getMemoryUsageStr(mbean.getNonHeapMemoryUsage()));
        
        /*
        String sysInfo = "Version: 20180621-1" + "\n";
        sysInfo += "DateTime: " + BaseUtil.formatDateTime(new Date()) + "\n\n";
        sysInfo += " HeapMemory: " + getMemoryUsageStr(mbean.getHeapMemoryUsage()) + "\n";
        sysInfo += "NonHeapMemory: " + getMemoryUsageStr(mbean.getNonHeapMemoryUsage()) + "\n\n";
        sysInfo += "Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor") + ", " + System.getProperty("java.home")
                + "\n";
        sysInfo += " JVM: " + System.getProperty("java.vm.name") + ", " + System.getProperty("java.vm.version") + ", "
                + System.getProperty("java.vm.vendor") + "\n";
        sysInfo += " OS: " + System.getProperty("os.name") + ", " + System.getProperty("os.arch") + ", " + System.getProperty("os.version") + "\n";
        */
        return map;
    }

    private static String getMemoryUsageStr(MemoryUsage musage) {
        String memoryStr = "Init: " + getSize(musage.getInit());
        memoryStr += " Max: " + getSize(musage.getMax());
        memoryStr += " Used: " + getSize(musage.getUsed());
        memoryStr += " Available: " + getSize(musage.getMax() - musage.getUsed());
        return memoryStr;
    }

    private static String getSize(long size) {
        String result = "";
        if (size < 1024)
            result = size + " byte";
        else if (size < 1024 * 1024)
            result = numberFormat.format((double) size / 1024) + " K";
        else if (size < 1024 * 1024 * 1024)
            result = numberFormat.format((double) size / (1024 * 1024)) + " M";
        else
            result = numberFormat.format((double) size / (1024 * 1024 * 1024)) + " G";

        return String.format("%8s", result);
    }
    


    public static String getConfigPath(String configPath) {
        //Not using: String str = Class.class.getClass().getResource("/").getPath();
        //Will return /C:/Users/louis.CHINA/git/ZeroApp_API/zero.service/target/classes/
        //Can not find dir when run in a jar.
        String dir = configPath;
        if (BaseUtil.isEmpty(dir)) {
            dir = "/conf/configPath/";
        } else {
            dir = dir.trim();
        }
        if (dir.contains("\\"))
            dir = dir.replaceAll("\\\\", "/");

        if (!dir.endsWith("/"))
            dir += "/";

        File file = new File(dir);
        if (!file.exists()) {
            boolean b = file.mkdirs();
            if (!b) {
                log.error("Make folder fails {}", b);
            }
        }
        return dir;
    }

    /** @return e.g. /var/appconf/apptmpfiles */
    public static String getTmpDir(String configPath) {
        String fullTmpDir = getConfigPath(configPath) + "tmp/";
        File tmpFile = new File(fullTmpDir);
        if (!tmpFile.exists()) {
            boolean b = tmpFile.mkdirs();

            if (!b) {
                log.error("Make folder fails {}", b);
            }
        }

        return fullTmpDir;
    }

    public static String encodeFileName(String fileName) throws ServiceException {
        HttpServletRequest request = HttpUtil.getRequest();
        String userAgent = request.getHeader("User-Agent");
        try {
            if (isIE(userAgent)) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(ERR_FAIL);
        }

        return fileName;
    }

    public static boolean isIE(String userAgent) {
        return userAgent != null && (userAgent.contains("MSIE") || userAgent.contains("Trident"));
    }

    public static void writeFile(InputStream in, String fullFileName, List<String> supportedDocTypes) throws ServiceException {
        checkIsTrue(!BaseUtil.isEmpty(fullFileName), "ERR_FILE_NAME_INVALID");
        checkIsTrue(!fullFileName.contains(DIR_PARENET), "ERR_FILE_NAME_INVALID");
        checkIsTrue(supportedDocTypes.contains(FileUtil.getExtension(fullFileName)), "ERR_UPLOAD_DOC_TYPE");

        try {
            File file = org.apache.commons.io.FileUtils.getFile(fullFileName);
            checkNotNull(file, "ERR_WRITE_DOC");
            org.apache.commons.io.FileUtils.copyInputStreamToFile(in, file);
        } catch (Exception e) {
            throw new ServiceException("ERR_WRITE_DOC");
        }
    }    
}
