package balu.sbms.studentservice.feignclients;

import balu.sbms.studentservice.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${address.service.url}", value = "address-feign-client", path = "/api/address")
public interface AddressFeignClient {
    @GetMapping("/getById/{id}")
    AddressResponse getById(@PathVariable long id);
}
