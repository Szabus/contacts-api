package hu.futureofmedia.task.contactsapi.repositories;

import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    Page<Contact> findAllByState(State state, Pageable pageable);

    Contact findAllByLastNameEquals(String lastName);

}
