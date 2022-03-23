package com.zero.springboot.demo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.zero.hintmgr.HintMgrApplication;

/**
 * RestTemplate lies in spring-web.jar
 * By default the RestTemplate relies on standard JDK facilities to establish HTTP connections.
 * If switch to other HTTP library, such as httpclient(Netty, OkHttp), it should be 4.5.5
 * There maybe indirect dependency, such as minio which depends on httpclient 4.1 that will missing HTTPClients.
 * 
 * @author Louisling
 * @version 2018-07-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HintMgrApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {
    //@Value("${local.server.port}")
    @LocalServerPort
    private int port;

    @Test
    public void testGet() {
        System.out.println("\nPort1: " + port);
        
        //http://47.100.122.195:8080/hintmgr/dummy/list
        Map<String, Object> headers = new HashMap<>();
        headers.put("sessionId", "1lBscuxBx7bYPFFmPuPq9hYws+KR7bhkNlKsbeIjp6o=");
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.headForHeaders("/dummy/loginAdmin", headers);
        String url = "http://localhost:" + port + "/hintmgr/admin/setUserStatus?user_id=86130c4ac600406097d2b12d68463d6a&user_status=1";
        String result = restTemplate.getForObject(url, String.class);        
        System.out.println("GET: " + result);
    }
}
