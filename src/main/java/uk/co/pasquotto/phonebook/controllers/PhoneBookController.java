package uk.co.pasquotto.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.services.PhoneBookService;

import java.util.List;

/**
 * Controller for everything related to the phonebook
 */
@Controller
public class PhoneBookController {

    @Autowired
    private PhoneBookService phoneBookService;

    @GetMapping("/")
    public String listContacts(Model model) {
        List<Contact> contacts = phoneBookService.listAllContacts();
        model.addAttribute("contacts", contacts);
        return "phonebook";
    }

    @PostMapping("/phonebook")
    public String createContact(@ModelAttribute Contact contact, Model model) {
        phoneBookService.addContact(contact);
        return listContacts(model);
    }

}
