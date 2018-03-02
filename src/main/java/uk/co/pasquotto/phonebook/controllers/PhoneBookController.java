package uk.co.pasquotto.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Contended;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.services.PhoneBookService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Controller for everything related to the phonebook
 */
@Controller
public class PhoneBookController {

    @Autowired
    private PhoneBookService phoneBookService;

    @RequestMapping(method=GET, value = "/")
    public String listContacts(Model model) {
        List<Contact> contacts = phoneBookService.listAllContacts();
        model.addAttribute("contacts", contacts);
        return "phonebook";
    }
}
