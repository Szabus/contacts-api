package hu.futureofmedia.task.contactsapi.entities;

import hu.futureofmedia.task.contactsapi.dtos.RegContactDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;


@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
            "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-" +
            "\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|" +
            "\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)"
            + "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(nullable = false)
    private String email;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
    private String comment;
    @Enumerated(EnumType.STRING)
    private State state;
    @CreationTimestamp
    private LocalDateTime regTime;
    @CreationTimestamp
    private LocalDateTime lastModification;

    public Contact() {
    }

    public Contact(Long id,
                   String lastName,
                   String firstName,
                   String email,
                   String phoneNumber,
                   Company company,
                   String comment,
                   State state,
                   LocalDateTime regTime,
                   LocalDateTime lastModification) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.comment = comment;
        this.state = state;
        this.regTime = regTime;
        this.lastModification = lastModification;
    }

    public Contact(String lastName, String firstName, String email, String phoneNumber, Company company, String comment) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.comment = comment;
    }

    public Contact(RegContactDto regContactDto) {
        this(regContactDto.getLastName(),
                regContactDto.getFirstName(),
                regContactDto.getEmail(),
                regContactDto.getPhoneNumber(),
                regContactDto.getCompanyName(),
                regContactDto.getComment());
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public void setLastModification(LocalDateTime lastModification) {
        this.lastModification = lastModification;
    }
}
