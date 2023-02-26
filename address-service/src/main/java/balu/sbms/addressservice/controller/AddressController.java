package balu.sbms.addressservice.controller;

import balu.sbms.addressservice.request.CreateAddressRequest;
import balu.sbms.addressservice.response.AddressResponse;
import balu.sbms.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest addressRequest){
        return addressService.createAddress(addressRequest);
    }

    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable long id){
        return addressService.getById(id);
    }
}
