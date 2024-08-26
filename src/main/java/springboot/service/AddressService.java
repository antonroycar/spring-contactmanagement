package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import springboot.entity.Address;
import springboot.entity.Contact;
import springboot.entity.User;
import springboot.model.AddressResponse;
import springboot.model.CreateAddressRequest;
import springboot.model.UpdateAddressRequest;
import springboot.repository.AddressRepository;
import springboot.repository.ContactRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AddressService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    public AddressResponse create(User user, CreateAddressRequest request){
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContact(contact);
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());

        addressRepository.save(address);

        return toAddressResponse(address);
    }

    private AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .city(address.getCity())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

    public AddressResponse get (User user, String contactId, String addressId){
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return toAddressResponse(address);
    }

    public AddressResponse update(User user, UpdateAddressRequest request){
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Address address = addressRepository.findFirstByContactAndId(contact, request.getAddressId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());
        addressRepository.save(address);

        return toAddressResponse(address);
    }

    public void remove(User user, String contactId, String addressId){

        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        addressRepository.delete(address);
    }

    public List<AddressResponse> list (User user, String contactId){
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Address> addresses = addressRepository.findAllByContact(contact);
        return addresses.stream().map(address -> toAddressResponse(address)).toList();
    }
}
