package com.example.controller;

import com.common.model.ResultModel;
import com.example.aop.SignatureValidation;
import com.example.model.HelloModel;
import com.example.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Environment env;

    @Value("${testData.data1}")
    private String data1;

    @Autowired
    private Person person;

//    @Autowired
//    private ResultModel resultModel;

    @GetMapping("/hello")
    public ResultModel hello() {

        String port = env.getProperty("server.port");
        HelloModel helloModel = new HelloModel();
        helloModel.setData1(data1);
        helloModel.setData2(port);

        ResultModel resultModel = new ResultModel();
        resultModel.setSuccessResult(helloModel);

        resultModel.setSuccessResult(person);

        Logger logger = LoggerFactory.getLogger(getClass());

        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

        return resultModel;
    }

    @SignatureValidation
    @PostMapping("/hello")
    public ResultModel hello2() {

        ResultModel resultModel = new ResultModel();

        resultModel.setSuccessResult(person);

        return resultModel;
    }
}
