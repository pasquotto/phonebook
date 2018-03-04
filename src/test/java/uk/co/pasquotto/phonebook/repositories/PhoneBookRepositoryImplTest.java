package uk.co.pasquotto.phonebook.repositories;

import org.junit.Before;
import org.junit.Test;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.model.PhoneBook;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static uk.co.pasquotto.phonebook.ContactUtils.createContact;

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
        pb.getContacts().add(createContact(UUID.fromString("32d5099f-fa66-45c7-a6fe-2248c612430f"), "name4", "phone4", "address4"));
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
    @Test
    public void getContact() {
        UUID uuid = UUID.fromString("32d5099f-fa66-45c7-a6fe-2248c612430f");
        Contact contactById = underTest.getContactById(uuid);
        assertNotNull(contactById);
    }

    @Test
    public void getContactThatDoesNotExists() {
        UUID uuid = UUID.randomUUID();
        Contact contactById = underTest.getContactById(uuid);
        assertNull(contactById);
    }

    @Test
    public void updateContact() {
        UUID uuid = UUID.fromString("32d5099f-fa66-45c7-a6fe-2248c612430f");
        Contact contactToBeChanged = createContact(uuid, "changedName", "changedName", "changedAddress");
        underTest.updateContact(contactToBeChanged);

        Contact retrievedContact = underTest.getContactById(uuid);

        assertEquals(contactToBeChanged.getId(), retrievedContact.getId());
        assertEquals(contactToBeChanged.getName(), retrievedContact.getName());
        assertEquals(contactToBeChanged.getPhoneNumber(), retrievedContact.getPhoneNumber());
        assertEquals(contactToBeChanged.getAddress(), retrievedContact.getAddress());
    }
}