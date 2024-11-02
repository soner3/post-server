package net.sonerapp.db_course_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DbCourseProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbCourseProjectApplication.class, args);
	}

}
