package mdc.logger.repositories;

import mdc.logger.model.SendMoney;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SendMoneyRepo {

    private Logger logger = LoggerFactory.getLogger(SendMoneyRepo.class);

    public String send(SendMoney sendMoney) {

        logger.info("Money transfer to {} ,Successfully ", sendMoney.getToAccountHolderName());
        return "Send Successfully";
    }
}
