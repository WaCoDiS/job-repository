package de.wacodis.jobrepository.controller;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2018-09-05T11:37:50.848+02:00[Europe/Berlin]")
public class ApiException extends Exception {

    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
