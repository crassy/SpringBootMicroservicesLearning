package balu.sbms.studentservice.service;

import balu.sbms.studentservice.feignclients.AddressFeignClient;
import balu.sbms.studentservice.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    AddressFeignClient addressFeignClient;

    long count = 1;

    Logger logger = LoggerFactory.getLogger(CommonService.class);
    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId){
        logger.info("Count = " + count);
        count ++;
        logger.info("Student Service - - Inside getAddressById " + addressId);

        /**
         * The below code was commented after adding resilience4j.
         * START:
         */
//        Mono<AddressResponse> addressResponseMono = webClient.get().uri("/getById/" + addressId)
//                .retrieve().bodyToMono(AddressResponse.class);
//
//        return addressResponseMono.block();
        /**
         * END:
         */
        AddressResponse addressResponse = addressFeignClient.getById(addressId);
        return  addressResponse;
    }
    public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable){
        logger.error("Student Service - - Inside fallbackGetAddressById " + addressId);
        logger.error("Error = " + throwable);
        return new AddressResponse();
    }
}
