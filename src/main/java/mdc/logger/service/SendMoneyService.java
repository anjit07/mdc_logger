package mdc.logger.service;


import mdc.logger.model.SendMoney;
import mdc.logger.repositories.SendMoneyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class SendMoneyService {

    @Autowired
    private SendMoneyRepo sendMoneyRepo;


    private Logger logger = LoggerFactory.getLogger(SendMoneyService.class);

    public Map<String, String> send(SendMoney sendMoney) {
        Map<String, String> response = new HashMap<>();
        try {
            logger.info("Before send money to {} ", sendMoney.getToAccountHolderName());
            String status = sendMoneyRepo.send(sendMoney);
            response.put("status", status);
            logger.info("after send money to {} and amount {} ", sendMoney.getToAccountHolderName(), sendMoney.getAmount());
        } catch (Exception e) {
            logger.error("Error occurred during sending money.");
        }
        return response;
    }
}
