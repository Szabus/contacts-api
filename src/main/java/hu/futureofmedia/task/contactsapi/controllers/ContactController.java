package hu.futureofmedia.task.contactsapi.controllers;

import hu.futureofmedia.task.contactsapi.dtos.ListContactDto;
import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;
import hu.futureofmedia.task.contactsapi.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {

    ContactRepository contactRepository;
    ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService, ContactRepository contactRepository) {
        this.contactService = contactService;
        this.contactRepository = contactRepository;
    }

    @GetMapping(path = "/contacts")
    public ResponseEntity<List<Contact>> getActiveContacts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "lastName") String sortBy) {

        List<Contact> allActiveContactsList = contactService.ActiveContacts(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(allActiveContactsList, HttpStatus.OK);
    }

    @GetMapping(path = "/contacts/{lastname}")
    public ResponseEntity<Contact> getOneContact(@PathVariable(value = "lastname") String lastName) {
        return new ResponseEntity<>(contactService.getOneContact(lastName), HttpStatus.OK);
    }

    @DeleteMapping(path = "/contacts/{lastname}")
    public ResponseEntity<Contact> deleteOneContact(@PathVariable(value = "lastname") String lastName) {
        return new ResponseEntity<>(contactService.deleteOneContact(lastName), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> contactRegistration(@Valid @RequestBody Contact contact) {
        try {
            contactService.registerContact(contact);
            return new ResponseEntity<>("Contact added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("not valid", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/contacts/{lastname}")
    public ResponseEntity<String> updateContact(
            @PathVariable(value = "lastname") String lastName,
            @Valid @RequestBody Contact contactDetails) {
        Contact contact = contactService.getOneContact(lastName);

        try {
            contact.setLastName(contactDetails.getLastName());
            contact.setFirstName(contactDetails.getFirstName());
            contact.setCompany(contactDetails.getCompany());
            contact.setEmail(contactDetails.getEmail());
            contact.setPhoneNumber(contactDetails.getPhoneNumber());
            contact.setComment(contactDetails.getComment());

            contactRepository.save(contact);
            return new ResponseEntity<>("Contact modified", HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>("not valid", HttpStatus.BAD_REQUEST);
        }
    }
}
