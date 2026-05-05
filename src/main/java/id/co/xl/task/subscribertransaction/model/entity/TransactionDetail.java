package id.co.xl.task.subscribertransaction.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransactionDetail {
    private String msisdn;
}
