package uk.co.pasquotto.phonebook.repositories;

import uk.co.pasquotto.phonebook.model.Contact;

import java.util.List;
import java.util.UUID;

public interface PhoneBookRepository {
    List<Contact> findAll();

    void setUpDatabase();

    Contact addContact(Contact contact);

    Contact getContactById(UUID contactId);
}
