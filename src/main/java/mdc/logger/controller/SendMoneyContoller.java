package mdc.logger.controller;

import mdc.logger.model.SendMoney;
import mdc.logger.service.SendMoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "send")
public class SendMoneyContoller {

    private Logger logger = LoggerFactory.getLogger(SendMoneyContoller.class);
    @Autowired
    private SendMoneyService sendMoneyService;


    @PostMapping
    public Map<String, String> save(@RequestBody SendMoney sendMoney) {

        logger.info("Before Service call");
        Map<String, String> response = sendMoneyService.send(sendMoney);
        logger.info("Before send response to user ");
        return response;
    }


}
