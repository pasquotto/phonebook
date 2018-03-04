package uk.co.pasquotto.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.services.PhoneBookService;

import java.util.List;
import java.util.UUID;

/**
 * Controller for everything related to the phonebook
 */
@Controller
public class PhoneBookController {

    private final String MAIN_PAGE = "phonebook";
    private final String CONTACTS = "contacts";
    private final String SELECTED_CONTACT = "selectedContact";

    @Autowired
    private PhoneBookService phoneBookService;

    @GetMapping("/")
    public String listContacts(Model model) {
        List<Contact> contacts = phoneBookService.listAllContacts();
        model.addAttribute(CONTACTS, contacts);
        if (!model.containsAttribute(SELECTED_CONTACT)) {
            model.addAttribute(SELECTED_CONTACT, new Contact());
        }
        return MAIN_PAGE;
    }

    @GetMapping("/phonebook")
    public String phoneBook(Model model) {
        return listContacts(model);
    }

    @PostMapping("/phonebook")
    public String createContact(@ModelAttribute Contact contact, Model model) {
        phoneBookService.addContact(contact);
        return listContacts(model);
    }

    @GetMapping("/phonebook/contacts/{id}")
    public String getContact(@PathVariable("id") UUID contactId, Model model) {
        Contact contact = phoneBookService.getContactById(contactId);
        model.addAttribute(SELECTED_CONTACT, contact);
        return listContacts(model);
    }

    @PutMapping("/phonebook/contacts/{id}")
    public String updateContact(@PathVariable("id") UUID contactId, @ModelAttribute Contact contact, Model model) {
        phoneBookService.updateContact(contactId, contact);
        return listContacts(model);
    }

    @DeleteMapping("/phonebook/contacts/{id}")
    public String deleteContact(@PathVariable("id") UUID contactId, Model model) {
        phoneBookService.deleteContact(contactId);
        return "redirect:/";
    }
}
