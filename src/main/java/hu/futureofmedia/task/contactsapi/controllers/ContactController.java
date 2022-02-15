package hu.futureofmedia.task.contactsapi.controllers;

import hu.futureofmedia.task.contactsapi.dtos.ListContactDto;
import hu.futureofmedia.task.contactsapi.dtos.ReadContactDto;
import hu.futureofmedia.task.contactsapi.dtos.RegContactDto;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;
import hu.futureofmedia.task.contactsapi.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class ContactController {

    ContactRepository contactRepository;
    ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService, ContactRepository contactRepository) {
        this.contactService = contactService;
        this.contactRepository = contactRepository;
    }

    @GetMapping(path = "/contacts")
    public ResponseEntity<List<ListContactDto>> getActiveContacts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "lastName") String sortBy) {

        List<ListContactDto> allActiveContactsList;
        allActiveContactsList = contactService.ActiveContacts(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(allActiveContactsList, HttpStatus.OK);
    }

    @GetMapping(path = "/contacts/{lastname}")
    public ResponseEntity<ReadContactDto> getOneContact(@PathVariable(value = "lastname") String lastName) {
        return new ResponseEntity<>(contactService.getOneContact(lastName), HttpStatus.OK);
    }

    @DeleteMapping(path = "/contacts/{lastname}")
    public ResponseEntity<String> deleteOneContact(@PathVariable(value = "lastname") String lastName) {
        contactService.deleteOneContact(lastName);
        return new ResponseEntity<>("Contact has been deleted", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> contactRegistration(@Valid @RequestBody RegContactDto contact) {
        try {
            contactService.registerContact(contact);
            return new ResponseEntity<>("Contact added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("not valid", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/contacts/{lastname}")
    public ResponseEntity<String> updateContact(
            @PathVariable(value = "lastname") String lastName,
            @Valid @RequestBody RegContactDto contactDetails) {

        try {
            contactService.updateContact(contactDetails, lastName);
            return new ResponseEntity<>("Contact modified", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("not valid", HttpStatus.BAD_REQUEST);
        }
    }
}
