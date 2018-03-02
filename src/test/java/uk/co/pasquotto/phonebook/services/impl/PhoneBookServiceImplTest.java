package uk.co.pasquotto.phonebook.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.repositories.ContactRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhoneBookServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private PhoneBookServiceImpl underTest;

    @Test
    public void listAllContacts() {

        List<Contact> contactsFromRepository = new ArrayList<>();
        contactsFromRepository.add(new Contact());
        contactsFromRepository.add(new Contact());
        contactsFromRepository.add(new Contact());
        when(contactRepository.findAll()).thenReturn(contactsFromRepository);

        List<Contact> contacts = underTest.listAllContacts();
        assertEquals(3, contacts.size());
    }
}