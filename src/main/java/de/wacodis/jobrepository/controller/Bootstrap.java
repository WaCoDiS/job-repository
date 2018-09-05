package de.wacodis.jobrepository.controller;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;
import io.swagger.models.auth.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Bootstrap extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        Info info =
                new Info()
                        .title("OpenAPI Server")
                        .description("The API for the WaCoDiS Job Repository ")
                        .termsOfService("https://wacodis.fbg-hsbo.de/")
                        .contact(new Contact().email("m.rieke@52north.org"))
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://www.apache.org/licenses/LICENSE-2.0.html"));

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger().info(info);

        new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
    }
}
