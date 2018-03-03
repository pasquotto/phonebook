package uk.co.pasquotto.phonebook.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.repositories.PhoneBookRepository;
import uk.co.pasquotto.phonebook.services.PhoneBookService;

import java.util.List;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Override
    public List<Contact> listAllContacts() {
        return phoneBookRepository.findAll();
    }

    @Override
    public void addContact(Contact contact) {
        phoneBookRepository.addContact(contact);
    }
}
