package com.financetool.finance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/version")
    public String getApiVersion() {
        return "v1";
    }
}
