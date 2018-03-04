package uk.co.pasquotto.phonebook.services;

import uk.co.pasquotto.phonebook.model.Contact;
import java.util.List;
import java.util.UUID;

public interface PhoneBookService {

    /**
     * Lists all the contacts
     * @return a list of all the contacts
     */
    List<Contact> listAllContacts();

    void addContact(Contact contact);

    Contact getContactById(UUID contactId);
}
