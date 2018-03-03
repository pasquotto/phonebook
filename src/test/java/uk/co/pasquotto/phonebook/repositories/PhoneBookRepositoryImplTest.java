package uk.co.pasquotto.phonebook.repositories;

import org.junit.Before;
import org.junit.Test;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.model.PhoneBook;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class PhoneBookRepositoryImplTest {

    private PhoneBookRepositoryImpl underTest;

    @Before
    public void setUp() {
        underTest = new PhoneBookRepositoryImpl();
        PhoneBook pb = new PhoneBook();
        pb.setContacts(new ArrayList<>(4));
        pb.getContacts().add(createContact(UUID.randomUUID(),"name1", "phone1", "address1"));
        pb.getContacts().add(createContact(UUID.randomUUID(),"name2", "phone2", "address2"));
        pb.getContacts().add(createContact(UUID.randomUUID(), "name3", "phone3", "address3"));
        pb.getContacts().add(createContact(UUID.randomUUID(), "name4", "phone4", "address4"));
        underTest.setPhoneBook(pb);
    }

    @Test
    public void testFindAll() {
        List<Contact> allContacts = underTest.findAll();
        assertEquals(4, allContacts.size());
    }

    @Test
    public void testAddContact() {
        Contact addedContact = underTest.addContact(createContact(null,"newName", "newPhone", "newAddress"));

        assertNotNull(addedContact);
        assertNotNull(addedContact.getId());
    }

    private Contact createContact(UUID id, String name, String phone, String address) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setPhoneNumber(phone);
        contact.setAddress(address);
        return contact;
    }
}