package id.co.xl.task.subscribertransaction.usecase;

import id.co.xl.task.subscribertransaction.model.entity.TransactionDetail;
import id.co.xl.task.subscribertransaction.model.response.GenericResponse;
import id.co.xl.task.subscribertransaction.model.response.GetPinRs;
import id.co.xl.task.subscribertransaction.repository.TransactionRepository;
import id.co.xl.task.subscribertransaction.service.PinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TransactionUsecase {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PinService pinService;

    public ResponseEntity<Object> getTransactionSummary(String msisdn) {
        GenericResponse<List<TransactionDetail>> detailGenericResponse = new GenericResponse<List<TransactionDetail>>()
                .setCode("00")
                .setMessage("success")
                .setStatus("ok");

        List<TransactionDetail> transactionDetailList = transactionRepository.fetchByMSISDN(msisdn);

        GetPinRs getPinRs = pinService.getPin(msisdn);

        detailGenericResponse.setData(transactionDetailList);
        return new ResponseEntity<>(detailGenericResponse, HttpStatus.OK);
    }
}
