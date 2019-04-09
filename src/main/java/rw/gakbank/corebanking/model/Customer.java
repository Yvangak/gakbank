package rw.gakbank.corebanking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Customer {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private Address address;
    private Contact contact;
    private LocalDate registeredOn;
    private List<Account> accountList;

    public void addAccount(Account account) {
        if (accountList == null) {
            accountList = new ArrayList<>();
        }
        accountList.add(account);
    }

    public void removeAccount(Account account) {
        if (accountList != null) {
            accountList.remove(account);
        }
    }

    public Customer() {
    }

    ;

    public Customer(String firstName, String lastName, Address address, Contact contact, LocalDate registeredOn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contact = contact;
        this.registeredOn = registeredOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(contact, customer.contact) &&
                Objects.equals(registeredOn, customer.registeredOn) &&
                Objects.equals(accountList, customer.accountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, contact, registeredOn, accountList);
    }

}
