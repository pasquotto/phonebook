package uk.co.pasquotto.phonebook.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.model.PhoneBook;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${phonebook.repository.url}")
    private String url;

    @Override
    public List<Contact> findAll() {
        PhoneBook phoneBook = restTemplate.getForObject(url, PhoneBook.class);
        return phoneBook.getContacts();
    }
}
