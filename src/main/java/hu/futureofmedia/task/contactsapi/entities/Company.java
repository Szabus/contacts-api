package hu.futureofmedia.task.contactsapi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Company {
    @Id
    private Long id;
    private String name;

    @OneToOne(mappedBy = "contact")
    private Contact contact;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
