package net.sonerapp.db_course_project.interfaces.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "DB course project", description = "Documentation for all API endpoints of this project", version = "1.0"), servers = {
        @Server(description = "LOCAL ENV", url = "http://localhost:8000")
})
public class OpenApiConfig {

}
