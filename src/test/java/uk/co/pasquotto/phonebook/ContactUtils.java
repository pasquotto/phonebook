package uk.co.pasquotto.phonebook;

import uk.co.pasquotto.phonebook.model.Contact;

import java.util.UUID;

public class ContactUtils {
    public static Contact createContact(UUID id, String name, String phone, String address) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setPhoneNumber(phone);
        contact.setAddress(address);
        return contact;
    }
}