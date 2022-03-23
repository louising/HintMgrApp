package com.zero.hintmgr.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zero.core.domain.PageVO;
import com.zero.core.domain.ResponseVO;
import com.zero.hintmgr.service.impl.DummyServiceImpl;
import com.zero.hintmgr.util.SecurityUtil;
import com.zero.hintmgr.vo.ParamObj;
import com.zero.hintmgr.vo.UserInfoBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Dummy Controller", value = "It provides basic function", produces = "The Produces")
/**
 * DummyController
 *    
 *   POST http://localhost:8080/hintmgr/dummy/add body { userId: 101, userName: "Alice"}
 * DELETE http://localhost:8080/hintmgr/dummy/del?dummyName=Alice001 BODY { "userId": 1, "userName": "Alice" }
 *    PUT http://localhost:8080/hintmgr/dummy/upd?userId=102&userName=Alice02 BODY { "userId": 103, "userName": "Alice03" }
 *    GET http://localhost:8080/hintmgr/dummy/list
 *   POST http://localhost:8080/hintmgr/dummy/page/3/2 BODY { "userId": "1", "userName": "Alice" }
 *   
 *   POST http://localhost:8080/hintmgr/dummy/uploadDoc BODY form-data
 *    GET http://localhost:8080/hintmgr/dummy/downloadLog
 *    GET http://localhost:8080/hintmgr/dummy/sysInfo
 *    GET http://localhost:8080/hintmgr/dummy/list
 *    http://localhost:8080/hintmgr/swagger-ui.html
 *    
 *    
 *    GET http://47.100.122.195:8080/hintmgr/dummy/sysInfo
 * 
 * @author Administrator
 * @version 2021-11-04
 */
@RestController
@RequestMapping("/dummy")
public class DummyController extends BaseController {
    private Logger log = LoggerFactory.getLogger(DummyController.class);

    //By default, read property from application.yml or application.properties
    //If read from app.yaml/app.properties, add @PropertySource("classpath:/com/acme/app.properties")
    @Value("${appConf.version}")
    private Long version;

    @Autowired
    private DummyServiceImpl service;
           
    //param: {code, userName,tel,inviteCode}
    //After register, return a jsession,roleCode
    //Or inviteCode is invalid or has been used.
    @PostMapping("/register")
    public ResponseVO register(@RequestBody UserInfoBean u) {
        return service.register(u);
    }    
       
    /**
     * @return {sessioncode,roleCode,roleName}
     * user_status(1-enable 0-discarded)
     */
    @GetMapping("/login")
    public ResponseVO login(@RequestParam String code) {
        return service.login(code);
    }
    
    @PostMapping("/loginAdmin")
    public ResponseVO loginAdmin(@RequestBody Map<String, String> map) {
        String loginUser = map.get("loginName");
        String loginPwd = map.get("loginPwd");
        return service.loginAdmin(loginUser, loginPwd);
    }
        

    @ApiOperation(value = "get system information", notes = "")
    /**
    * Get system information
    */
    //@RequestMapping(path = "/sysInfo", method = RequestMethod.GET, produces = RESPONSE_TYPE)
    @GetMapping("/sysInfo")
    public ResponseVO sysInfo(ParamObj paramObj, PageVO pageVO) {
/*        log.info("ParamObj {} PageVO  {}", paramObj, pageVO);
        log.info("User {}", BaseUtil.getUserInfoFromSessionId());
        String userId = BaseUtil.getUserIdFromSessionId();
        List<Map<String, Object> > list = dao.getProjectAdminMemberCount(userId); 
        System.out.println("Size: " + list.size());
        for (Map<String, Object> map1: list) {
            for (String key: map1.keySet() ) {
                System.out.println(key + "=" + map1.get(key).getClass());
                
            }
        }*/
        
        ResponseVO result = new ResponseVO();
        Map<String, String> map = service.getSysInfo();
        result.setData(map);
        return result;
    }
    
    //cipher?mode=1&str=xx //mode: 1=encode 2=decode
    @GetMapping("/cipher")
    public ResponseVO encode(@RequestParam("str") String source, @RequestParam("mode") String mode) {
        ResponseVO result = new ResponseVO();
        String target = "1".equals(mode) ? SecurityUtil.encode(source) : SecurityUtil.decode(source);
        result.setData(target);
        log.info("Source {} Target {} Mode {}", source, target, mode);
        return result;
    }
}
