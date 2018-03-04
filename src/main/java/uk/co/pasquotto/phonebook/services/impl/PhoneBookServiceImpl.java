package uk.co.pasquotto.phonebook.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.repositories.PhoneBookRepository;
import uk.co.pasquotto.phonebook.services.PhoneBookService;

import java.util.List;
import java.util.UUID;

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

    @Override
    public Contact getContactById(UUID contactId) {

        Contact contact = phoneBookRepository.getContactById(contactId);

        if(contact == null) {
            throw new RuntimeException("contact not found");
        }

        return contact;
    }

    @Override
    public void updateContact(UUID contactId, Contact contact) {
        if (phoneBookRepository.getContactById(contactId) == null) {
            throw new RuntimeException("Contact not found");
        }
        phoneBookRepository.updateContact(contact);
    }
}
