package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sonerapp.db_course_project.application.dto.UserTokenControllerDto.UserTokenDto;
import net.sonerapp.db_course_project.core.model.UserToken;

@RestController
@RequestMapping("/api/v1/project/user-token")
public class UserTokenController {

    private final ConversionService conversionService;

    public UserTokenController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/{id}")
    public UserTokenDto getUserToken(@PathVariable("id") UserToken userToken) {
        return conversionService.convert(userToken, UserTokenDto.class);
    }

}
