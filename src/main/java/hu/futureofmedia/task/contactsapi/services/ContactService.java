package hu.futureofmedia.task.contactsapi.services;

import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.State;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository CONTACT_REPOSITORY;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.CONTACT_REPOSITORY = contactRepository;
    }

    public List<Contact> ActiveContacts(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable sortedByLastNameAsc = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Contact> pagedResult = CONTACT_REPOSITORY.findAllByState(State.ACTIVE, sortedByLastNameAsc);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Contact>();
        }
    }

    @Transactional
    public Contact getOneContact(String lastName){
        try{
            return CONTACT_REPOSITORY.findAllByLastNameEquals(lastName);
        } catch (Exception e){
            return null;
        }
    }
    @Transactional
    public Contact deleteOneContact(String lastName){
        try{
            Contact contact = CONTACT_REPOSITORY.findAllByLastNameEquals(lastName);
            contact.setState(State.DELETED);
            return contact;
        } catch (Exception e){
            return null;
        }
    }

    public Contact registerContact(Contact contact) {

        contact.setState(State.ACTIVE);
        CONTACT_REPOSITORY.save(contact);

        return contact;
    }
}
