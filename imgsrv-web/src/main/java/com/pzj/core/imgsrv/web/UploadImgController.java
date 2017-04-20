package com.pzj.core.imgsrv.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class UploadImgController {
	
    @RequestMapping("/upload")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

}
