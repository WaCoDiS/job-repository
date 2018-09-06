package de.wacodis.jobrepository.controller;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2018-09-05T11:37:50.848+02:00[Europe/Berlin]")
public class NotFoundException extends ApiException {

    private int code;

    public NotFoundException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
