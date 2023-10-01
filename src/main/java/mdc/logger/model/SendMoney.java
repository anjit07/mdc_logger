package mdc.logger.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SendMoney {

    private String fromAccountHolderName;
    private String toAccountHolderName;

    private int amount;
}
