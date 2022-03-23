package com.zero.hintmgr.controller;

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
import com.zero.hintmgr.service.impl.AppUserService;

import io.swagger.annotations.Api;

//Different Controller use the same tags, will display in the same UI
@Api(tags = "App User Controller")
/**
 * AdminController
 * //Download templaate file, file is broken
 * 
 * @author Administrator
 * @version 2021-11-04
 */
@RestController
@RequestMapping("/app/user")
public class AppUserController extends BaseController {
    Logger log = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    private AppUserService userService;

    @Autowired
    private AdminService service;
    
    @PostMapping(path = "/uploadFile", consumes = "multipart/form-data")
    public ResponseVO upload(@RequestParam MultipartFile multiFile) {
        return process(()-> userService.uploadFile(multiFile.getInputStream(), multiFile.getOriginalFilename()));        
    }
    
    @GetMapping("/publicHints")
    public ResponseVO findPublicHintsPage(@RequestParam(value="itemType", required=false) String itemType, PageVO pageVO) {
        return process(()-> service.findPublicHintsPage(itemType, pageVO));        
    }
    
    @GetMapping("/askHint")
    public ResponseVO askHint(@RequestParam(value="hintId") String hintId) {
        return process(()-> service.askHint(hintId));        
    }
    
    @GetMapping("/freeHint")
    public ResponseVO freeHint(@RequestParam(value="hintId") String hintId) {
        return process(()-> service.freeHint(hintId));        
    }
    
    @GetMapping("/myHints")
    public ResponseVO myHintsPage(@RequestParam(value="auditStatus", required=false) String auditStatus, @RequestParam(value="itemTypeName", required=false) String itemTypeName, @RequestParam(value="myHintType") String myHintType,@RequestParam(value="userId", required=false) String userId, PageVO pageVO) {
        return process(()-> service.myHintsPage(userId, itemTypeName,myHintType, auditStatus, pageVO));   
    }
    
    @PostMapping("/saveHint")
    public ResponseVO saveHint(@RequestBody Map<String, Object> map) {
        return process(() -> service.saveHintFromAppUser(map));
    }
    
    @GetMapping("/getHintDetail")
    public ResponseVO getHintDetail(@RequestParam(value="hintId") String hintId) {
        return process(() -> service.getHintDetail(hintId));
    }    
    
    @GetMapping("/delHint")
    public ResponseVO delHint(@RequestParam("hintId") String hint_id) {
        return process(() -> service.delHint(hint_id));
    }    
    
    @PostMapping("/updHintRemark")
    public ResponseVO updHintRemark(@RequestBody Map<String, String> map) {
        return process(() -> service.updHintRemark(map));
    } 
    
    @PostMapping("/addHintRecord")
    public ResponseVO addHintRecord(@RequestBody Map<String, String> map) {
        return process(() -> service.addHintRecord(map));
    }
}
