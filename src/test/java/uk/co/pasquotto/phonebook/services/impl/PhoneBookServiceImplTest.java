package uk.co.pasquotto.phonebook.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.repositories.PhoneBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.co.pasquotto.phonebook.ContactUtils.createContact;

@RunWith(MockitoJUnitRunner.class)
public class PhoneBookServiceImplTest {

    @Mock
    private PhoneBookRepository phoneBookRepository;

    @InjectMocks
    private PhoneBookServiceImpl underTest;

    @Test
    public void listAllContacts() {
        List<Contact> contactsFromRepository = new ArrayList<>();
        contactsFromRepository.add(new Contact());
        contactsFromRepository.add(new Contact());
        contactsFromRepository.add(new Contact());
        when(phoneBookRepository.findAll()).thenReturn(contactsFromRepository);

        List<Contact> contacts = underTest.listAllContacts();
        assertEquals(3, contacts.size());
    }

    @Test
    public void testCreateContact() {
        Contact newContact = new Contact();
        newContact.setName("ContactName");
        newContact.setPhoneNumber("1234");
        newContact.setAddress("Address");

        underTest.addContact(newContact);

        verify(phoneBookRepository).addContact(newContact);
    }

    @Test
    public void testGetContact() {
        Contact contactFromRepository = createContact(UUID.randomUUID(), "cName", "1234", "address");
        when(phoneBookRepository.getContactById(contactFromRepository.getId())).thenReturn(contactFromRepository);
        Contact retrievedContact = underTest.getContactById(contactFromRepository.getId());

        assertEquals(contactFromRepository.getId(), retrievedContact.getId());
    }

    @Test(expected = ContactNotFoundException.class)
    public void testGetContactNotfound() {
        UUID id = UUID.randomUUID();
        when(phoneBookRepository.getContactById(id)).thenReturn(null);
        underTest.getContactById(id);
    }

    @Test
    public void testUpdateContact() {
        Contact contactFromRepository = createContact(UUID.randomUUID(), "cName", "1234", "address");
        when(phoneBookRepository.getContactById(contactFromRepository.getId())).thenReturn(contactFromRepository);
        Contact contactToUpdate = createContact(contactFromRepository.getId(), "cName changed", "1234 changed", "address changed");
        underTest.updateContact(contactFromRepository.getId(), contactToUpdate);
        verify(phoneBookRepository).updateContact(contactToUpdate);
    }

    @Test(expected = ContactNotFoundException.class)
    public void testUpdateContactThatDoesNotExists() {
        UUID uuid = UUID.randomUUID();
        when(phoneBookRepository.getContactById(uuid)).thenReturn(null);
        Contact contactToUpdate = createContact(uuid, "cName changed", "1234 changed", "address changed");
        underTest.updateContact(uuid, contactToUpdate);
    }

    @Test
    public void testDeleteContact() {
        Contact contactFromRepository = createContact(UUID.randomUUID(), "cName", "1234", "address");
        when(phoneBookRepository.getContactById(contactFromRepository.getId())).thenReturn(contactFromRepository);
        underTest.deleteContact(contactFromRepository.getId());
        verify(phoneBookRepository).deleteContact(contactFromRepository);
    }

    @Test(expected = ContactNotFoundException.class)
    public void testDeleteContactDoesNotExists() {
        UUID uuid = UUID.randomUUID();
        when(phoneBookRepository.getContactById(uuid)).thenReturn(null);
        underTest.deleteContact(uuid);
    }

}