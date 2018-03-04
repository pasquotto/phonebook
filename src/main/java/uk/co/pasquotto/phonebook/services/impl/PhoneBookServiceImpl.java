package uk.co.pasquotto.phonebook.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.repositories.PhoneBookRepository;
import uk.co.pasquotto.phonebook.repositories.PhoneBookRepositoryImpl;
import uk.co.pasquotto.phonebook.services.PhoneBookService;

import java.util.List;
import java.util.UUID;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookServiceImpl.class);

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Override
    public List<Contact> listAllContacts() {
        return phoneBookRepository.findAll();
    }

    @Override
    public void addContact(Contact contact) {
        logger.debug("addContact({})", contact);
        phoneBookRepository.addContact(contact);
    }

    @Override
    public Contact getContactById(UUID contactId) {
        logger.debug("getContactById({})", contactId);
        Contact contact = phoneBookRepository.getContactById(contactId);

        if(contact == null) {
            throw new ContactNotFoundException(contactId.toString());
        }
        return contact;
    }

    @Override
    public void updateContact(UUID contactId, Contact contact) {
        logger.debug("updateContact({})", contactId);
        if (phoneBookRepository.getContactById(contactId) == null) {
            throw new ContactNotFoundException(contactId.toString());
        }
        phoneBookRepository.updateContact(contact);
    }

    @Override
    public void deleteContact(UUID contactId) {
        logger.debug("deleteContact({})", contactId);
        Contact contact = phoneBookRepository.getContactById(contactId);
        if (contact == null) {
            throw new ContactNotFoundException(contactId.toString());
        }
        phoneBookRepository.deleteContact(contact);
    }
}
