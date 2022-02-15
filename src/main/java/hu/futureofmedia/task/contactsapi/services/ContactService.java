package hu.futureofmedia.task.contactsapi.services;

import hu.futureofmedia.task.contactsapi.dtos.ListContactDto;
import hu.futureofmedia.task.contactsapi.dtos.ReadContactDto;
import hu.futureofmedia.task.contactsapi.dtos.RegContactDto;
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
@Transactional
public class ContactService {

    private final ContactRepository CONTACT_REPOSITORY;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.CONTACT_REPOSITORY = contactRepository;
    }

    public List<ListContactDto> ActiveContacts(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable sortedByLastNameAsc = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<ListContactDto> resultList = new ArrayList<>();

        Page<Contact> pagedResult = CONTACT_REPOSITORY.findAllByState(State.ACTIVE, sortedByLastNameAsc);

        if (pagedResult.hasContent()) {
            for (Contact contact : pagedResult.getContent()) {
                resultList.add(new ListContactDto(contact));
            }
            return resultList;
        } else {
            return new ArrayList<>();
        }
    }

    public ReadContactDto getOneContact(String lastName) {
        try {
            return new ReadContactDto(CONTACT_REPOSITORY.findAllByLastNameEquals(lastName));
        } catch (Exception e) {
            return null;
        }
    }

    public ReadContactDto deleteOneContact(String lastName) {
        try {
            Contact contact = CONTACT_REPOSITORY.findAllByLastNameEquals(lastName);
            contact.setState(State.DELETED);
            return new ReadContactDto(contact);
        } catch (Exception e) {
            return null;
        }
    }

    public void registerContact(RegContactDto contactDto) {

        Contact contact = new Contact(contactDto);
        contact.setState(State.ACTIVE);
        CONTACT_REPOSITORY.save(contact);
    }

    public void updateContact(RegContactDto contactDto, String lastName) {

        Contact contact = CONTACT_REPOSITORY.findAllByLastNameEquals(lastName);
        contact.setLastName(contactDto.getLastName());
        contact.setFirstName(contactDto.getFirstName());
        contact.setCompany(contactDto.getCompanyName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setComment(contactDto.getComment());

        CONTACT_REPOSITORY.save(contact);
    }
}
