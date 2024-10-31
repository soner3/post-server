package net.sonerapp.db_course_project.application.dto;

public record UnauthorizedDto(String path, String error, String message, int status) {

}
