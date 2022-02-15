package hu.futureofmedia.task.contactsapi.dtos;

import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.entities.Contact;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RegContactDto {

    @NotNull(message = "Last name cannot be null")
    private String lastName;
    @NotNull(message = "Last name cannot be null")
    private String firstName;
    private Company companyName;
    @Email(message = "Not valid", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    private String phoneNumber;
    private String comment;


    public RegContactDto(String lastName,
                         String firstName,
                         Company companyName,
                         String email,
                         String phoneNumber,
                         String comment) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
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

    public Company getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Company companyName) {
        this.companyName = companyName;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public RegContactDto(Contact contact) {
        this(contact.getLastName(),
                contact.getFirstName(),
                contact.getCompany(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getComment());
    }
}
