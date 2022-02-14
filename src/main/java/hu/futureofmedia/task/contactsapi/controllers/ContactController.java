package hu.futureofmedia.task.contactsapi.controllers;

import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.State;
import hu.futureofmedia.task.contactsapi.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(path = "/contacts")
    public ResponseEntity<List<Contact>> getActiveContacts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "lastName") String sortBy)
    {

        List<Contact> allActiveContactsList = contactService.ActiveContacts(pageNo,pageSize,sortBy);
        return new ResponseEntity<>(allActiveContactsList, HttpStatus.OK);
    }

    @GetMapping(path = "/contacts/{lastname}")
    public ResponseEntity<Contact> getOneContact(@PathVariable(value = "lastname") String lastName)
            {
                return new ResponseEntity<>(contactService.getOneContact(lastName), HttpStatus.OK);
    }

    @DeleteMapping(path = "/contacts/{lastname}")
    public ResponseEntity<Contact> deleteOneContact(@PathVariable(value = "lastname") String lastName)
    {
        return new ResponseEntity<>(contactService.deleteOneContact(lastName),HttpStatus.OK);
    }
}
