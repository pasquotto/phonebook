package uk.co.pasquotto.phonebook.repositories;

import uk.co.pasquotto.phonebook.model.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> findAll();
}
