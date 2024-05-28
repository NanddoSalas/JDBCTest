package com.nanddosalas.jdbctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nanddosalas.jdbctest.models.User;
import com.nanddosalas.jdbctest.repositories.UserRepository;

@SpringBootApplication
public class JdbcTestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JdbcTestApplication.class, args);

		UserRepository repository = context.getBean(UserRepository.class);

		System.out.println("\nretrieve all users\n");
		System.out.println(repository.findAll());

		System.out.println("\nretrieve user that does not exist\n");
		try {
			repository.findById(99);
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("\nretrieve user by id 4\n");
		System.out.println(repository.findById(4));

		System.out.println("\nUsers count: " + repository.count());

		System.out.println("\ninsert 1 user");
		User user1 = new User();

		user1.setFullName("Fernando Salas");
		user1.setEmail("luis.nando.salas28@gmail.com");
		user1.setAvatar("");
		user1.setAbout("Something about me!");
		user1.setRole("ServerAdmin");
		user1.setPassword("password");

		repository.insert(user1);

		System.out.println("\nUsers count: " + repository.count());

		System.out.println("\ndelete user with id 5");
		System.out.println("\nrows affected " + repository.delete(5));
		System.out.println("\nUsers count: " + repository.count());

	}
}
