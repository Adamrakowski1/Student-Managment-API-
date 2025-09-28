package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;
@Configuration
public class studentConfig {
    @Bean
    CommandLineRunner commandLineRunner(studentRepository repository){
        return args -> {
			student adam = new student(
				"Adam",                // name
				"adam@gmail.com",      // email
				LocalDate.of(2000, 1, 1)  // dob
			);
            student alex = new student(
				"Alex",                // name
				"Alex@gmail.com",      // email
				LocalDate.of(2003, 5, 5)  // dob
			);
            repository.saveAll(List.of(adam, alex));
        };
    }
}
