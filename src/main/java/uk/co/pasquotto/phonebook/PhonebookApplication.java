package uk.co.pasquotto.phonebook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uk.co.pasquotto.phonebook.repositories.PhoneBookRepository;

@SpringBootApplication
public class PhonebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonebookApplication.class, args);
	}


	@Bean
	public CommandLineRunner run(PhoneBookRepository phoneBookRepository) throws Exception {
		return args -> {
            phoneBookRepository.setUpDatabase();
		};
	}

}
