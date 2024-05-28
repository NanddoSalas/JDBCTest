package com.nanddosalas.jdbctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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
	}
}
