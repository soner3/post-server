package net.sonerapp.db_course_project;

import org.springframework.boot.SpringApplication;

public class TestDbCourseProjectApplication {

	public static void main(String[] args) {
		SpringApplication.from(DbCourseProjectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
