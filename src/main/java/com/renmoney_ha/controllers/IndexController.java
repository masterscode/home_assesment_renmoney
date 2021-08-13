package com.renmoney_ha.controllers;

import com.renmoney_ha.payloads.response.ProjectInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public @ResponseBody ProjectInfo indexPath() {
        return new ProjectInfo(
                "Renmoney backend take home project",
                "Develop a simple REST API for a small library.  " +
                        "The API will be used to add, update, delete, " +
                        "lend books to users and search for books in the library",
                "Emmanuel Ogbinaka",
                LocalDate.of(2021,8,13),
                "http://localhost/swagger-ui/"
        );
    }
}
