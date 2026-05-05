package id.co.xl.task.subscribertransaction.service;

import id.co.xl.task.subscribertransaction.model.response.GetPinRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Service
//public class PinService {
//    @Autowired
//    private WebClient genericWebClient;
//
//    public GetPinRs getPin(String msisdn) {
//        GetPinRs getPinRs = new GetPinRs().setStatus("error");
//        String uri = "/subscriber/628191234561/pin";
//        try {
//            ResponseEntity<GetPinRs> getPinRsResponseEntity = this.genericWebClient.get()
//                    .uri(uri).retrieve()
//                    .toEntity(GetPinRs.class)
//                    .block();
//
//            log.info("[GET HTTP RESPONSE - SUCCESS][{}][{}][{}]", uri, getPinRsResponseEntity.getStatusCode(), getPinRsResponseEntity.getBody());
//            getPinRs = getPinRsResponseEntity.getBody();
//        } catch (WebClientResponseException ex) {
//            log.info("[GET HTTP RESPONSE - FAILED][{}][{}][{}]", uri, ex.getStatusCode(), ex.getResponseBodyAsString());
//        }
//
//        return getPinRs;
//    }
//}
public class PinService {

    private final WebClient webClient;

    public String getPin(String msisdn) {
        try {
            GetPinRs response = webClient.get()
                    .uri("/subscriber/{msisdn}/pin", msisdn)
                    .retrieve()
                    .bodyToMono(GetPinRs.class)
                    .block();

            if (response != null && "ok".equals(response.getStatus())) {
                return response.getData();
            }

            return null;

        } catch (WebClientResponseException.NotFound e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("PIN service error");
        }
    }
}
