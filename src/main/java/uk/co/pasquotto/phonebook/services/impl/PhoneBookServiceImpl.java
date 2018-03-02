package uk.co.pasquotto.phonebook.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.repositories.ContactRepository;
import uk.co.pasquotto.phonebook.services.PhoneBookService;

import java.util.List;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> listAllContacts() {
        return contactRepository.findAll();
    }
}
