package hu.futureofmedia.task.contactsapi.dtos;

import hu.futureofmedia.task.contactsapi.entities.Contact;

public class RegContactDto {

    private String lastName;
    private String firstName;
    private String CompanyName;
    private String email;
    private String phoneNumber;
    private String comment;


    public RegContactDto(String lastName,
                          String firstName,
                          String companyName,
                          String email,
                          String phoneNumber,
                          String comment) {
        this.lastName = lastName;
        this.firstName = firstName;
        CompanyName = companyName;
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

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
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
                contact.getCompany().getName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getComment());
    }
}
