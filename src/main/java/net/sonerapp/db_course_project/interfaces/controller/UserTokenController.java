package net.sonerapp.db_course_project.interfaces.controller;

import java.util.stream.Stream;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sonerapp.db_course_project.application.dto.UnauthorizedDto;
import net.sonerapp.db_course_project.application.dto.UserTokenControllerDto.UserTokenDto;
import net.sonerapp.db_course_project.core.service.UserTokenService;

@RestController
@RequestMapping("/api/v1/user-token")
@Tag(name = "User Token")
public class UserTokenController {

    private final ConversionService conversionService;
    private final UserTokenService userTokenService;

    public UserTokenController(ConversionService conversionService, UserTokenService userTokenService) {
        this.conversionService = conversionService;
        this.userTokenService = userTokenService;
    }

    @Operation(summary = "All User Token", description = "Returns all User Token if the user has the admin role", responses = {
            @ApiResponse(responseCode = "200", description = "Request Successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserTokenDto[].class))),
            @ApiResponse(responseCode = "401", description = "User not unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class)))
    }, security = @SecurityRequirement(name = "accessAuth"))
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stream<UserTokenDto>> getUserToken(Pageable pageable) {
        Stream<UserTokenDto> userTokenStream = userTokenService.getUserTokenPage(pageable)
                .map(userToken -> conversionService.convert(userToken, UserTokenDto.class));
        return ResponseEntity.ok(userTokenStream);
    }

}
