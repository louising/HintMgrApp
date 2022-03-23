package com.zero.springboot.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zero.core.domain.PageResultVO;
import com.zero.core.domain.PageVO;
import com.zero.hintmgr.HintMgrApplication;
import com.zero.hintmgr.constants.Constants;
import com.zero.hintmgr.dao.DummyDao;
import com.zero.hintmgr.service.impl.AdminService;
import com.zero.hintmgr.service.impl.DummyServiceImpl;
import com.zero.hintmgr.util.BaseUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HintMgrApplication.class)
public class AdminServiceTest {
    Logger log = LoggerFactory.getLogger(AdminServiceTest.class);

    @Autowired
    private AdminService service;
    
    @Autowired
    private DummyServiceImpl dummyService;
    
    @Autowired
    private DummyDao dao;
    
    //@Test
    public void testAddDict() {
        ///ItemType
        Map<String, Object> map = new HashMap<>();
        map.put("dict_name", "银行");
        map.put("dict_desc", "银行 desc");
        map.put("dict_type", Constants.DICT_ITEM_TYPE);
        service.saveDict(map);

        map.clear();
        map.put("dict_name", "餐饮");
        map.put("dict_desc", "餐饮 desc");
        map.put("dict_type", Constants.DICT_ITEM_TYPE);
        service.saveDict(map);

        map.clear();
        map.put("dict_name", "超市");
        map.put("dict_desc", "超市 desc");
        map.put("dict_type", Constants.DICT_ITEM_TYPE);
        service.saveDict(map);

        /// Type
        map.clear();
        map.put("dict_name", "科技企业");
        map.put("dict_desc", "科技企业  desc");
        map.put("dict_type", Constants.DICT_HINT_TYPE);
        service.saveDict(map);

        map.clear();
        map.put("dict_name", "传统企业");
        map.put("dict_desc", "传统企业  desc");
        map.put("dict_type", Constants.DICT_HINT_TYPE);
        service.saveDict(map);

        map.clear();
        map.put("dict_name", "机器人企业");
        map.put("dict_desc", "机器人企业 desc");
        map.put("dict_type", Constants.DICT_HINT_TYPE);
        service.saveDict(map);

        /// Level
        map = new HashMap<>();
        map.put("dict_name", "三级");
        map.put("dict_desc", "Important");
        map.put("dict_type", Constants.DICT_HINT_LEVEL);
        service.saveDict(map);
    }

    //@Test
    public void testFindDict() {
        Map<String, String> param = new HashMap<>();
        param.put("dict_type", Constants.DICT_CUSTOMER_TYPE);
        PageVO pageVO = new PageVO(2, 1);
        PageResultVO result = service.findDictPage(param, pageVO);
        Object records = result.getRecords();

        log.info("List: " + records);
        Assert.assertNotNull(records);
    }

    //@Test
    public void testGetDelDict() {
        service.delDict("23d83a044bf34f6896f1738e739860b0"); //23d83a044bf34f6896f1738e739860b0运营部1

        Map<String, Object> r = service.getDict("090e4d483b8a496cb4980e3cb6e7b79d"); //090e4d483b8a496cb4980e3cb6e7b79d 销售部1
        System.out.println("Before: " + r);

        r.put("dict_name", "开发部");
        r.put("dict_desc", "Dev Dept");
        service.saveDict(r);

        r = service.getDict("090e4d483b8a496cb4980e3cb6e7b79d");
        System.out.println("After: " + r);

        Assert.assertNotNull(1);
    }
    
    //@Test
    public void testSysRole() {
        /*
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("role_name", "Role" + i);
            map.put("role_desc", "Role Desc" + i);
            map.put("role_authories", "[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\",\"/customerType\"]");
            service.saveSysRole(map);
        }
        */

        /*
        Map<String, Object> map = new HashMap<>();
        map.put("role_id", "a8a730c68167406a891b14e153390e01");
        map.put("role_name", "Role New");
        map.put("role_desc", "Role Desc New");
        map.put("role_authories", "[\"/adminManage\",\"/clueLevel\"]");
        service.saveSysRole(map);
        */
        
        /*
        String id = "a8a730c68167406a891b14e153390e01";
        Map<String, Object> map = service.getSysRole(id);
        System.out.println("SysRole: " + map);
        service.delSysRole(id);
        */
        Map<String, String> map = new HashMap<>();
        PageVO pageVO = new PageVO(3, 1);
        PageResultVO result = service.findSysRolePage(map, pageVO);
        log.info("List page1: " + result.getRecords());
        
        pageVO = new PageVO(3, 2);
        result = service.findSysRolePage(map, pageVO);
        log.info("List page2: " + result.getRecords());
    }
    
    //@Test
    public void testSysUser() {
        /*
        Map<String, String> map = new HashMap<>();
        PageVO pageVO = new PageVO(3, 1);
        PageResultVO result = service.findSysUserPage(map, pageVO);
        log.info("List page1: " + result.getRecords());
        
        pageVO = new PageVO(3, 2);
        result = service.findSysUserPage(map, pageVO);
        log.info("List page2: " + result.getRecords());
        */
        
        /*
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", "2bb023928e7e4fa8935a775f8d7e7d47");
        map.put("login_name", "AccountNew");
        map.put("login_pwd", "Secret New" );
        map.put("user_name", "user_name New");
        map.put("role_id", "roleid New");
        map.put("remark", "Remark New");
        service.saveSysUser(map);
        */
        
        /*
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("login_name", "Account" + i);
            map.put("login_pwd", "Secret" + i);
            map.put("user_name", "user_name" + i);
            map.put("role_id", "roleid" +i);
            map.put("remark", "Remark" + i);
            service.saveSysUser(map);
        }
        
        service.delSysUser("2bb023928e7e4fa8935a775f8d7e7d47");
        */
        
        //findSysUserCount
        System.out.println("Account1: " + dummyService.findSysUserAuthories("Account1"));
        System.out.println("Account3: " + dummyService.findSysUserAuthories("Account3"));
        
        Map<String, String> map = new HashMap<>();
        map.put("user_id", "86130c4ac600406097d2b12d68463d6a");
        map.put("user_status", "0");
        service.setSysUserStatus("86130c4ac600406097d2b12d68463d6a", "0");
        
        map.put("user_id", "2b7b8610233b4137813c2bf26f4a0f4b");
        map.put("user_status", "1");
        service.setSysUserStatus("2b7b8610233b4137813c2bf26f4a0f4b", "1");
    }
    
    //@Test
    public void testHintAdd() {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("hint_name", "线索" + i);
            map.put("remark", "线索 Remark" + i);
            map.put("project_id", "144f807280c34ad290493591a5cae4cb");
            map.put("hint_type_id", "241a8357372547c5a125a14e2bb22691");
            map.put("hint_level_id", "7ccdc29c2474407ca4bcb1b51d65cdfe");
            map.put("item_type_id", "5c3595e09acc447ea8d30c3729990308");
            map.put("customer_type_ids", "090e4d483b8a496cb4980e3cb6e7b79d");
            map.put("corp_name", "苹果公司");
            map.put("corp_address", "海滨路321号");
            map.put("province", "广东省");
            map.put("city", "深圳市");
            map.put("district", "罗湖区");

            map.put("customer_name", "张三");
            map.put("customer_contact", "13022223333");
            map.put("hint_from", "1");
            map.put("hint_status", "0");
            map.put("audit_status", "1");

            service.saveHintFromAdmin(map);
        }
    }

    //@Test
    public void testHintUpd() {
        /*
        Map<String, Object> map = service.getHint("e776626491004ee7883f7ec03aa726ce");
        System.out.println("Before" + map);
        map.put("hint_name", "广东科学新线索1");
        map.put("project_id", "283b26c29ac148b1bdea47f019151713");
        
        service.saveHintForAdmin(map);
        System.out.println("After: " + map);
        */
        
        Map<String, Object> param = new HashMap<>();
        param.put("project_name", "会展");
        param.put("item_type_name", "银行");
        param.put("corp_name", "苹果");
        param.put("corp_name", "苹果");
        param.put("customer_name", "李");
        //param.put("hint_status", "1");
        param.put("audit_status", "1");
        PageVO pageVO = new PageVO(25, 1);
        PageResultVO result = service.findHintPage(param, pageVO);
        List records = (List) result.getRecords();
        System.out.println("Size: " + records.size());
        log.info("List: " + records);
    }
    
    @Test
    public void testDao() {
        //dao.clearProjectAdmin("proj02", "70af32d21a67406b9499299d544ecf57");
        //dao.clearProjectAdmin("proj02", "70af32d21a67406b9499299d544ecf57");
        Map<String, Object> map = new HashMap<>();
        Date date = BaseUtil.parseDateFromYMDHM("2022-01-09 22:45:02");
        //map.put("update_time", date);
        //dao.updateProjectTime(map);
        
        Date endDate = BaseUtil.parseDate("2022-01-09 23:45:02");
        Calendar d = Calendar.getInstance();
        d.setTime(endDate);
        d.add(Calendar.DAY_OF_MONTH, 1);
        map.put("update_time", d.getTime());
        
        System.out.println(d.getTime());
        dao.updateProjectTime(map);             
    }
}
