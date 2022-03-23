package com.zero.springboot.demo;

import static com.zero.hintmgr.util.BaseUtil.parseDate;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zero.hintmgr.util.BaseUtil;
import com.zero.hintmgr.util.SecurityUtil;
import com.zero.hintmgr.util.SimpleDataSource;

public class JdbcDemo {
    static DataSource dataSource;
    static Logger log = LoggerFactory.getLogger(JdbcDemo.class);
    
    static {
        String driverClass = "org.h2.Driver";
        String url = "jdbc:h2:tcp://localhost/~/H2DB-SpringRestDemo";
        String user = "sa";
        String pwd = "sa";
    
        //dataSource = new SimpleDataSource(driverClass, url, user, pwd);
    }
    
    public static void main(String[] args) throws Exception {
       String s = "2022-01-09 22:45";
       System.out.println(BaseUtil.parseDateFromYMDHM(s));
       
       Date endDate = parseDate("2022-01-09 23:45:00");
       Calendar d = Calendar.getInstance();
       d.setTime(endDate);
       d.add(Calendar.DAY_OF_MONTH, 1);
        
       
       
       System.out.println(BaseUtil.getSessionId("306b069058d64973b4b29e17b1b7aab2", "3"));
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf.format(new Date()));
        System.out.println(sdf.parse("2021-12-13 10:23"));
        */
        //addData();
        //System.out.println(BaseUtil.getSessionId("proj04", "2"));
        //System.out.println(SecurityUtil.decode("1TpnGUc773iQyY9uHZoPYA=="));

        /*
        String url = "https://thirdwx.qlogo.cn/mmopen/vi_32/49nDFxibAia2aCo8zOXUGTnb6KOhMKVXlcGxFffAGJD6wWUnIl3uGP2A7cOE8VdqOupF3pk2vG4RXIS7zmQxgIicw/132";
        String objectName = saveFile2Oss(url);
        System.out.println("ObjectName: " + objectName);
        
        // URL: https://sw-clue.oss-cn-guangzhou.aliyuncs.com/202112/bdba4b834fec466e97f39073522cf845?Expires=1638719469&OSSAccessKeyId=LTAI5t8M6cuo3dFJHoN1ZrqM&Signature=CddJTJCRqo%2FV%2FdD3gim4Bl8nCfQ%3D
        
        */
        
        /*
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println(new Date());
            }
            
        }, 1000, 2000); //300000
        */
    }
    
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        
        byte[] bytes = bos.toByteArray();
        /*
        byte[] getData = readInputStream(inputStream);
        FileOutputStream fos = new FileOutputStream(new File("d:/tmp/a.png"));
        fos.write(getData);
        fos.close();
        inputStream.close();
         */
        return bytes;
    }
    
    static List<String> getTime(String date1, String date2) {
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
    
    //"https://sw-clue.oss-cn-guangzhou.aliyuncs.com/20211113/5c3706fee02e4029817cb05c2a23fd75.jpeg?Expires=1636849097&OSSAccessKeyId=LTAI5t8M6cuo3dFJHoN1ZrqM&Signature=W4YtWJGpxETt9x4bq9AlmbwEwlg%3D";
    static String getObjectName(String url) {
        int startIndex = "https://sw-clue.oss-cn-guangzhou.aliyuncs.com/".length();
        int endIndex = url.indexOf("?");
        return url.substring(startIndex, endIndex);
    }
    
    static void testSession() {
        for (int i = 0;i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            String time = String.format("%1$tF %1$tF %1$tT", System.currentTimeMillis()); //%1$tF %1$tT
            map.put("userId", "2b7b8610233b4137813c2bf26f4a0f4b");
            //map.put("loginTime", time);
            map.put("rolePosition", "ADMIN");
            String str = JSONObject.toJSONString(map);
            System.out.println("S0: " + str);
            
            //String str = "2b7b8610233b4137813c2bf26f4a0f4b" + "=" + time;
            String str1 = SecurityUtil.encode(str);
            str1 = new String(Base64.getEncoder().encode(str1.getBytes()));
            
            String str2 = SecurityUtil.decode(new String(Base64.getDecoder().decode(str1.getBytes())));
            System.out.println("S1: " + str1);
            System.out.println("S2: " + str2);
            JSONObject obj = JSONObject.parseObject(str2);
            String userId = obj.getString("userId");
            time = obj.getString("loginTime");
            System.out.println("UserID: " + userId + " Time: " + time + " Position: " + obj.getString("rolePosition"));
            System.out.println();
        }
        
        /*
        Map<String, String> map = new HashMap<>();
        map.put("userId", "2b7b8610233b4137813c2bf26f4a0f4b");
        //map.put("loginTime", time);
        map.put("rolePosition", "3");
        String str = JSONObject.toJSONString(map);
        
        System.out.println(new String(Base64.getEncoder().encode(str.getBytes())));
        System.out.println(new String(Base64.getDecoder().decode("eyJyb2xlUG9zaXRpb24iOiIzIiwidXNlcklkIjoiMmI3Yjg2MTAyMzNiNDEzNzgxM2MyYmYyNmY0YTBmNGIifQ==".getBytes())));         
         */
    }
    
    static void generateCodes4() {
        int i = 0, n = 13;
        while (i < n) {
            String str = BaseUtil.getPkCode();
            System.out.println("Source: " + str);
            int length = 8, k = 0, max = 32 - length;
            while (i < n & k <= max) {
                System.out.println(i + ": " + str.substring(k, k + 8));
                i++;
                k++;
            }          
        }
    }
    
    static void generateCodes3() {
        int i = 0, n = 28;
        while (i < n) {
            String str = BaseUtil.getPkCode();
            System.out.println("Source: " + str);
            int length = 8, k = 0, max = 32 - length;
            while (i < n & k <= max) {
                System.out.println(i + ": " + str.substring(k, k + 8));
                i++;
                k++;
            }          
        }
    }
    
    static void testGenerateCodes() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2000000; i++) {
            String str = BaseUtil.getPkCode();
            add(list, str.substring(0, 8));
            add(list, str.substring(8, 16));
            add(list, str.substring(16, 24));
            add(list, str.substring(24, 32));
        }
    }
    
    static void add(List<String> list, String code) {
        if (list.contains(code)) {
            System.err.println("ERR");
        } else {            
            list.add(code);
        }
    }

    static void testPoiExcel()  throws Exception {
        InputStream in = new FileInputStream("D:/workspace/workspace_ee/HintMgrApp/docs/HintTemplate.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(in);
        XSSFSheet sheet = wb.getSheetAt(0);
        
        int lastRow = sheet.getLastRowNum(); //Row [first, last] 0,1,2,3 Cell[first, last) 0,1,2,3
        for (int i = 1; i <= lastRow; i++) {
            XSSFRow row = sheet.getRow(i);
            
            String levelName = row.getCell(0).getStringCellValue();
            String hintName = row.getCell(1).getStringCellValue();
            String corpName = row.getCell(2).getStringCellValue();
            String typeName = row.getCell(3).getStringCellValue();
            String itemName = row.getCell(4).getStringCellValue();
            String projectName = row.getCell(5).getStringCellValue();
            String province = row.getCell(6).getStringCellValue();
            String city = row.getCell(7).getStringCellValue();
            String district = row.getCell(8).getStringCellValue();
            String add = row.getCell(9).getStringCellValue();
            String custName = row.getCell(10).getStringCellValue();
            String custType = row.getCell(11).getStringCellValue();
            String contact = (row.getCell(12).getCellType() == Cell.CELL_TYPE_NUMERIC) ? (long) row.getCell(12).getNumericCellValue() + "": row.getCell(12).getStringCellValue();
            String remark = row.getCell(13).getStringCellValue();
        }
        wb.close();
    }
    
    static OSS getOssClient() {
        OSS ossClient = new OSSClientBuilder().build("https://oss-cn-guangzhou.aliyuncs.com", "LTAI5t8M6cuo3dFJHoN1ZrqM", "SCEcwVfnY1yYmrVP16tty3bcxCDrsm");
        return ossClient;
    } 
    
    static void testOss() throws Exception {
        String originalFileName = "d:/images/rosestarcloud.png";
        String bucket = "sw-clue";
        
        String folderName = new SimpleDateFormat("yyyyMM").format(new Date());
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
        String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String objectName = folderName + "/" + fileMainName + extensionName;
        
        OSS oss = getOssClient();
        oss.putObject(bucket, objectName, new FileInputStream(originalFileName));
        oss.shutdown();
        //return objectName;
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date date1Day = c.getTime();
        String url = oss.generatePresignedUrl(bucket, objectName, date1Day).toString();
        System.out.println("URL: " + url);
        
        System.out.println(String.format("%1$tF %1$tT", System.currentTimeMillis())); // .%1$tL
    }
    
    /** Save file to OSS, return objectName */
    static String saveUrlToOss(String url) throws Exception {
        String bucket = "sw-clue";
        
        HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
        //conn.setConnectTimeout(3 * 1000);
        //conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        InputStream inputStream = conn.getInputStream();
        
        String folderName = new SimpleDateFormat("yyyyMM").format(new Date());
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
        String objectName = folderName + "/" + fileMainName;
        
        OSS oss = getOssClient();
        oss.putObject(bucket, objectName, inputStream);
        oss.shutdown();
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date date1Day = c.getTime();
        String fileUrl = oss.generatePresignedUrl(bucket, objectName, date1Day).toString();
        System.out.println("URL: " + fileUrl);
        //System.out.println(String.format("%1$tF %1$tT", System.currentTimeMillis())); // .%1$tL
        return objectName;
    }
    
    //Save file to OSS, return objectName
    static String saveFile2Oss(String url) throws Exception {
        String bucket = "sw-clue";
        
        HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
        //conn.setConnectTimeout(3 * 1000);
        //conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        InputStream inputStream = conn.getInputStream();
        
        String folderName = new SimpleDateFormat("yyyyMM").format(new Date());
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
        String objectName = folderName + "/" + fileMainName;
        
        OSS oss = getOssClient();
        oss.putObject(bucket, objectName, inputStream);
        oss.shutdown();

        /*
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date date1Day = c.getTime();
        String fileUrl = oss.generatePresignedUrl(bucket, objectName, date1Day).toString();
        System.out.println("URL: " + fileUrl);
        */
        return objectName;
    }

    protected static void addData() throws SQLException {
        Connection con = dataSource.getConnection();
        con.setAutoCommit(false);

        //1) clear users
        Statement stmt = con.createStatement();
        stmt.execute("delete t_user");
        con.commit();
        
        //2) Add users
        PreparedStatement pstmt = con.prepareStatement("insert into t_user(user_name,login_name, create_time) values (?, ?, CURRENT_TIMESTAMP)");
        for (int i = 1; i <= 10; i++) {
            pstmt.setString(1, "Alice" + i);
            pstmt.setString(2, "alice" + i);
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        con.commit();
        
        /*
        PreparedStatement pstmt = con.prepareStatement("insert into t_user(user_name,login_name, create_time) values (?, ?, CURRENT_TIMESTAMP)");
        for (int i = 1; i <= 10; i++) {
            pstmt.setString(1, "AAA" + i);
            pstmt.setString(2, "aaa" + i);
            pstmt.execute();
        }
        con.commit();
        */
        
        /*
        String sql = "insert into t_user(user_name,login_name, create_time) values ('%s', '%s', CURRENT_TIMESTAMP)";
        Statement stmt = con.createStatement();
        for (int i = 1; i <= 10; i++) {
            stmt.addBatch(String.format(sql, "CCC" + i, "ccc" + i));
        }
        stmt.executeBatch();
        stmt.close();
        con.commit();
        */
        
        con.setAutoCommit(true);        
        con.close();
    }

    static {
        String driverClass = "org.h2.Driver";
        String url = "jdbc:h2:tcp://localhost/~/H2DB-SpringRestDemo";
        String user = "sa";
        String pwd = "sa";
    
        //dataSource = new SimpleDataSource(driverClass, url, user, pwd);
    }

}
