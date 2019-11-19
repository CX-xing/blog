package com.cx.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :  C__xing
 * @Date :  2019/11/19
 */
@RestController
public class InitController {

    @RequestMapping("/")
    String index() {
        return "hello spring boot";
    }

}
