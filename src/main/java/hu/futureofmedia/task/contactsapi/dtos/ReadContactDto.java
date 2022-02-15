package hu.futureofmedia.task.contactsapi.dtos;

import hu.futureofmedia.task.contactsapi.entities.Contact;

import java.time.LocalDateTime;

public class ReadContactDto {

    private String lastName;
    private String firstName;
    private String CompanyName;
    private String email;
    private String phoneNumber;
    private String comment;
    private LocalDateTime regTime;
    private LocalDateTime lastModification;

    public ReadContactDto(String lastName,
                          String firstName,
                          String companyName,
                          String email,
                          String phoneNumber,
                          String comment,
                          LocalDateTime regTime,
                          LocalDateTime lastModification) {
        this.lastName = lastName;
        this.firstName = firstName;
        CompanyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.regTime = regTime;
        this.lastModification = lastModification;
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


    public ReadContactDto(Contact contact){
        this(contact.getLastName(),
                contact.getFirstName(),
                contact.getCompany().getName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getComment(),
                contact.getRegTime(),
                contact.getLastModification());
    }
}
