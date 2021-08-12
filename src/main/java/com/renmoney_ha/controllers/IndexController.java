package com.renmoney_ha.controllers;

import com.renmoney_ha.payloads.response.ProjectInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public @ResponseBody ProjectInfo indexPath() {
        return new ProjectInfo();
    }
}

//http://localhost:8080/swagger-ui/