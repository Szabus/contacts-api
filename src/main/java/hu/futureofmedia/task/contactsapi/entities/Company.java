package hu.futureofmedia.task.contactsapi.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "company")
    @Transient
    private List<Contact> contacts = new ArrayList<>();

    public Company() {
    }

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
