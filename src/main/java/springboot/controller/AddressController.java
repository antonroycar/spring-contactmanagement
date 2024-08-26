package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springboot.entity.User;
import springboot.model.AddressResponse;
import springboot.model.CreateAddressRequest;
import springboot.model.UpdateAddressRequest;
import springboot.model.WebResponse;
import springboot.service.AddressService;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PostMapping(
            path = "/api/contacts/{contactId}/addresses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create(User user,
                                               @RequestBody CreateAddressRequest request,
                                               @PathVariable("contactId") String contactId){
        request.setContactId(contactId);
        AddressResponse addressResponse = addressService.create(user, request);
        return WebResponse.<AddressResponse>builder()
                .data(addressResponse)
                .build();
    }

    @GetMapping(
            path = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> get(User user,
                                            @PathVariable("contactId") String contactId,
                                            @PathVariable("addressId") String addressId){
        AddressResponse addressResponse = addressService.get(user, contactId, addressId);
        return WebResponse.<AddressResponse>builder()
                .data(addressResponse).build();

    }

    @PutMapping(
            path = "/api/contacts/{contactsId}/addresses/{addressId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> update(User user,
                                               @RequestBody UpdateAddressRequest request,
                                               @PathVariable("contactsId") String contactsId,
                                               @PathVariable("addressId") String addressId){
        request.setContactId(contactsId);
        request.setAddressId(addressId);

        AddressResponse update = addressService.update(user, request);
        return WebResponse.<AddressResponse>builder()
                .data(update)
                .build();
    }

    @DeleteMapping(
            path = "/api/contacts/{contactsId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> remove(User user,
                                      @PathVariable("contactsId") String contactsId,
                                      @PathVariable("addressId") String addressId){
        addressService.remove(user, contactsId, addressId);

        return WebResponse.<String>builder()
                .data("OK").build();
    }

    @GetMapping(
            path = "/api/contacts/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AddressResponse>> list(User user,
                                  @PathVariable("contactId") String contactId){
        List<AddressResponse> list = addressService.list(user, contactId);
        return WebResponse.<List<AddressResponse>>builder().data(list).build();
    }
}
