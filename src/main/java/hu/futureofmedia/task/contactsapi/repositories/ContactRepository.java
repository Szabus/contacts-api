package hu.futureofmedia.task.contactsapi.repositories;


import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    Page<Contact> findAllByState(State state, Pageable pageable);
    Contact findAllByLastNameEquals(String lastName);
    Contact deleteByLastNameEquals(String lastName);
}
