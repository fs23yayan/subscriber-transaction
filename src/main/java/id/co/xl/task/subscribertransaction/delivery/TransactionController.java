package id.co.xl.task.subscribertransaction.delivery;

import id.co.xl.task.subscribertransaction.model.request.GetMonthlyTransactionRq;
import id.co.xl.task.subscribertransaction.usecase.TransactionUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//public class TransactionController {
//    @Autowired
//    private TransactionUsecase transactionUsecase;
//
//    @PostMapping("/api/transaction/summary")
//    public ResponseEntity<?> getTransactionSummary(@RequestBody()GetMonthlyTransactionRq bodyRq) {
//        return transactionUsecase.getTransactionSummary(bodyRq.getMsisdn());
//    }
//}

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/summary")
    public ResponseEntity<Object> getSummary(
            @RequestParam String msisdn,
            @RequestParam String pin
    ) {
        return transactionService.getMonthlySummary(msisdn, pin);
    }
}
