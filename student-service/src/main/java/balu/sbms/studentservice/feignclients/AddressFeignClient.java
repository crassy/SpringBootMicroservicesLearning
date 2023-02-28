package balu.sbms.studentservice.feignclients;

import balu.sbms.studentservice.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The below code works with Feign Client.
 * As we have hand coded the address.service.url in the application.properties file,
 * this wont be useful for a microservices environment.
 * Thus we are going with Eureka server and client.
 */
//@FeignClient(url = "${address.service.url}", value = "address-feign-client", path = "/api/address")
//public interface AddressFeignClient {
//    @GetMapping("/getById/{id}")
//    AddressResponse getById(@PathVariable long id);
//}

@FeignClient(value = "api-gateway")
public interface AddressFeignClient {
    @GetMapping("/address-service/api/address/getById/{id}")
    AddressResponse getById(@PathVariable long id);
}

