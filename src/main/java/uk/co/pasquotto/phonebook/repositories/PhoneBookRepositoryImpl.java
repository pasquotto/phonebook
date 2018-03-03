package uk.co.pasquotto.phonebook.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.model.PhoneBook;

import java.util.List;
import java.util.UUID;

@Repository
public class PhoneBookRepositoryImpl implements PhoneBookRepository {

    @Autowired
    private RestTemplate restTemplate;

    private PhoneBook phoneBook;

    @Value("${phonebook.repository.url}")
    private String url;

    @Override
    public List<Contact> findAll() {
        return phoneBook.getContacts();
    }

    @Override
    public void setUpDatabase() {
        phoneBook = restTemplate.getForObject(url, PhoneBook.class);
        //generating unique id's for each contact
        phoneBook.getContacts().forEach(contact -> contact.setId(generateId()));
    }

    @Override
    public Contact addContact(Contact contact) {
        contact.setId(generateId());
        phoneBook.getContacts().add(contact);
        return contact;
    }

    private UUID generateId() {
        return UUID.randomUUID();
    }

    protected void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }
}
