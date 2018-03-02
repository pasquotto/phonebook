package uk.co.pasquotto.phonebook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.pasquotto.phonebook.controllers.PhoneBookController;
import uk.co.pasquotto.phonebook.model.Contact;
import uk.co.pasquotto.phonebook.services.PhoneBookService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PhonebookApplicationTests {


	@Test
	public void testListContacts() {
		List<Contact> contacts = new ArrayList<>();



	}

}
