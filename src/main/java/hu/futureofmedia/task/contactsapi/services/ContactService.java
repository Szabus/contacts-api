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
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> ActiveContacts(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable sortedByLastNameAsc = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Contact> pagedResult = contactRepository.findAllByState(State.ACTIVE, sortedByLastNameAsc);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Contact>();
        }
    }

    @Transactional
    public Contact getOneContact(String lastName){
        try{
            return contactRepository.findAllByLastNameEquals(lastName);
        } catch (Exception e){
            return null;
        }
    }
    @Transactional
    public Contact deleteOneContact(String lastName){
        try{
            return deleteOneContact(lastName);
        } catch (Exception e){
            return null;
        }
    }

}
