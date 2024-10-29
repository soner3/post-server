package net.sonerapp.db_course_project.interfaces.controller;

import java.util.stream.Stream;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sonerapp.db_course_project.application.dto.UserTokenControllerDto.UserTokenDto;
import net.sonerapp.db_course_project.core.service.UserTokenService;

@RestController
@RequestMapping("/api/v1/project/user-token")
public class UserTokenController {

    private final ConversionService conversionService;
    private final UserTokenService userTokenService;

    public UserTokenController(ConversionService conversionService, UserTokenService userTokenService) {
        this.conversionService = conversionService;
        this.userTokenService = userTokenService;
    }

    @GetMapping
    public ResponseEntity<Stream<UserTokenDto>> getUserToken(Pageable pageable) {
        Stream<UserTokenDto> userTokenStream = userTokenService.getUserTokenPage(pageable)
                .map(userToken -> conversionService.convert(userToken, UserTokenDto.class));
        return ResponseEntity.ok(userTokenStream);
    }

}
