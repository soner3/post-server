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
import net.sonerapp.db_course_project.application.dto.RoleController.RoleDto;
import net.sonerapp.db_course_project.core.service.RoleService;
import net.sonerapp.db_course_project.infrastructure.annotations.RoleValidator;

@RestController
@RequestMapping("/api/v1/role")
@Tag(name = "Role")
public class RoleController {

    private final RoleService roleService;

    private final ConversionService conversionService;

    public RoleController(RoleService roleService, ConversionService conversionService) {
        this.roleService = roleService;
        this.conversionService = conversionService;
    }

    @Operation(summary = "All Roles", description = "Returns all existing Roles", responses = {
            @ApiResponse(responseCode = "200", description = "Request Successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoleDto[].class))),
            @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class)))
    }, security = @SecurityRequirement(name = "accessAuth"))
    @GetMapping
    @RoleValidator
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stream<RoleDto>> getAllRoles(Pageable pageable) {
        Stream<RoleDto> roleList = roleService.getRolePage(pageable)
                .map(role -> conversionService.convert(role, RoleDto.class));
        return ResponseEntity.ok(roleList);
    }

    @Operation(summary = "Get Role", description = "Returns the role by the UUID", responses = {
            @ApiResponse(responseCode = "200", description = "Request Successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoleDto.class))),
            @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "No entity found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))

    }, security = @SecurityRequirement(name = "accessAuth"))
    @GetMapping("/{uuid}")
    @RoleValidator
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDto> getRole(@PathVariable String uuid) {
        RoleDto roleDto = conversionService.convert(roleService.getRole(uuid), RoleDto.class);
        return ResponseEntity.ok(roleDto);
    }

    @Operation(summary = "Get Role", description = "Deletes the role by the UUID", responses = {
            @ApiResponse(responseCode = "200", description = "Deletion Successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkDto.class))),
            @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    }, security = @SecurityRequirement(name = "accessAuth"))
    @DeleteMapping("/{uuid}")
    @RoleValidator
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OkDto> deleteRole(@PathVariable String uuid) {
        roleService.deleteRole(uuid);
        return ResponseEntity.ok(new OkDto("Deletion Successfull"));
    }

}
