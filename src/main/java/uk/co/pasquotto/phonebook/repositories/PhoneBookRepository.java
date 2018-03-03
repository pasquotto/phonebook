package uk.co.pasquotto.phonebook.repositories;

import uk.co.pasquotto.phonebook.model.Contact;

import java.util.List;

public interface PhoneBookRepository {
    List<Contact> findAll();

    void setUpDatabase();

    Contact addContact(Contact contact);
}
