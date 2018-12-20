package com.tickychat.server.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angus
 * @date 2018/12/15
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "HELLO TICKY CHAT!";
    }
}
