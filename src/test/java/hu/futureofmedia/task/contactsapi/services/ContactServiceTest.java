package hu.futureofmedia.task.contactsapi.services;

import hu.futureofmedia.task.contactsapi.entities.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactServiceTest {

    @Autowired
    ContactService contactService;

    Contact contact = new Contact();

    @Test
    void activeContacts() {



    }

    @Test
    void getOneContact() {
    }

    @Test
    void deleteOneContact() {
    }

    @Test
    void registerContact() {
    }
}