package com.zero.hintmgr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
public class HintMgrApplication {
    Logger log = LoggerFactory.getLogger(HintMgrApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HintMgrApplication.class, args);
    }
}
