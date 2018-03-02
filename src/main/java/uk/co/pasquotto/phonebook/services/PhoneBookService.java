package uk.co.pasquotto.phonebook.services;

import uk.co.pasquotto.phonebook.model.Contact;
import java.util.List;

public interface PhoneBookService {

    List<Contact> listAllContacts();
}
