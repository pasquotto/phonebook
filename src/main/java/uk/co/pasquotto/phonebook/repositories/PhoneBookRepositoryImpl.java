package uk.co.pasquotto.phonebook.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.model.PhoneBook;
import uk.co.pasquotto.phonebook.services.impl.ContactNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class PhoneBookRepositoryImpl implements PhoneBookRepository {

    @Autowired
    private RestTemplate restTemplate;

    private PhoneBook phoneBook;
    private Map<UUID, Contact> contactsById;

    @Value("${phonebook.repository.url}")
    private String url;

    @Override
    public List<Contact> findAll() {
        return phoneBook.getContacts();
    }

    @Override
    public void setUpDatabase() {
        phoneBook = restTemplate.getForObject(url, PhoneBook.class);
        contactsById = new HashMap<>(phoneBook.getContacts().size());
        //generating unique id's for each contact
        phoneBook.getContacts().forEach(contact -> {
            contact.setId(generateId());
            contactsById.put(contact.getId(), contact);
        });

    }

    @Override
    public Contact addContact(Contact contact) {
        contact.setId(generateId());
        phoneBook.getContacts().add(contact);
        contactsById.put(contact.getId(), contact);
        return contact;
    }

    @Override
    public Contact getContactById(UUID contactId) {
        return contactsById.get(contactId);
    }

    @Override
    public void updateContact(Contact contact) {
        Contact oldContact = contactsById.get(contact.getId());
        if (oldContact != null) {
            phoneBook.getContacts().remove(oldContact);
            phoneBook.getContacts().add(contact);
            contactsById.put(contact.getId(), contact);
        } else {
            throw new ContactNotFoundException(contact.getId().toString());
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        phoneBook.getContacts().remove(contact);
        contactsById.remove(contact.getId());
    }

    private UUID generateId() {
        return UUID.randomUUID();
    }

    protected void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        contactsById = new HashMap<>(phoneBook.getContacts().size());
        phoneBook.getContacts().forEach(contact -> contactsById.put(contact.getId(), contact));
    }
}
