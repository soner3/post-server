package net.sonerapp.db_course_project.interfaces.controller;

import java.util.stream.Stream;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.UnauthorizedDto;
import net.sonerapp.db_course_project.application.dto.UserTokenControllerDto.UserTokenDto;
import net.sonerapp.db_course_project.core.service.UserTokenService;
import net.sonerapp.db_course_project.infrastructure.annotations.RoleValidator;

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
            @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class)))
    }, security = @SecurityRequirement(name = "accessAuth"))
    @GetMapping
    @RoleValidator
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stream<UserTokenDto>> getUserTokenList(Pageable pageable) {
        Stream<UserTokenDto> userTokenStream = userTokenService.getUserTokenPage(pageable)
                .map(userToken -> conversionService.convert(userToken, UserTokenDto.class));
        return ResponseEntity.ok(userTokenStream);
    }

    @Operation(summary = "Get User Token", description = "Returns the User Token Information by the Token", responses = {
            @ApiResponse(responseCode = "200", description = "Request Successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserTokenDto.class))),
            @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "No entity found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    }, security = @SecurityRequirement(name = "accessAuth"))
    @GetMapping("/{token}")
    @RoleValidator
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserTokenDto> getUserToken(@PathVariable String token) {
        UserTokenDto userTokenDto = conversionService.convert(userTokenService.getToken(token), UserTokenDto.class);
        return ResponseEntity.ok(userTokenDto);
    }

    @Operation(summary = "Delete User Token", description = "Deletes the User Token", responses = {
            @ApiResponse(responseCode = "200", description = "Deletion Successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkDto.class))),
            @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    }, security = @SecurityRequirement(name = "accessAuth"))
    @DeleteMapping("/{token}")
    @RoleValidator
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OkDto> deleteUserToken(@PathVariable String token) {
        userTokenService.deleteToken(token);
        return ResponseEntity.ok(new OkDto("Deletion Successfull"));
    }

}
