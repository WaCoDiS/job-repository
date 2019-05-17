/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.3.1).
 * https://openapi-generator.tech Do not edit the class manually.
 */
package de.wacodis.jobdefinition.controller;

import de.wacodis.jobdefinition.model.Error;
import de.wacodis.jobdefinition.model.PaginatedWacodisJobDefinitionResponse;
import de.wacodis.jobdefinition.model.WacodisJobDefinition;
import io.swagger.annotations.*;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2018-10-16T11:18:11.410+02:00[Europe/Berlin]")
@Validated
@Api(value = "jobDefinitions", description = "the jobDefinitions API")
public interface JobDefinitionsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(
            value = "",
            nickname = "createWacodisJobDefinition",
            notes = "Creates a new WacodisJobDefinition in the repository ",
            response = WacodisJobDefinition.class,
            tags = {})
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 201,
                        message = "WacodisJobDefinition response ",
                        response = WacodisJobDefinition.class),
                @ApiResponse(code = 500, message = "unexpected error ", response = Error.class)
            })
    @RequestMapping(
            value = "/jobDefinitions",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    default ResponseEntity<WacodisJobDefinition> createWacodisJobDefinition(
            @ApiParam(value = "WacodisJobDefinition to add to the repository ", required = true)
                    @Valid
                    @RequestBody
                    WacodisJobDefinition wacodisJobDefinition) {
        getRequest()
                .ifPresent(
                        request -> {
                            for (MediaType mediaType :
                                    MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                                if (mediaType.isCompatibleWith(
                                        MediaType.valueOf("application/json"))) {
                                    ApiUtil.setExampleResponse(
                                            request,
                                            "application/json",
                                            "{  \"lastFinishedExecution\" : \"2000-01-23T04:56:07.000+00:00\",  \"execution\" : {    \"pattern\" : \"pattern\",    \"event\" : \"{}\"  },  \"useCase\" : \"useCase\",  \"temporalCoverage\" : {    \"duration\" : \"duration\",    \"previousExecution\" : true  },  \"processingTool\" : \"processingTool\",  \"created\" : \"2000-01-23T04:56:07.000+00:00\",  \"areaOfInterest\" : {    \"extent\" : [ -151.17018, -151.17018, -151.17018, -151.17018 ]  },  \"inputs\" : [ {    \"identifier\" : \"identifier\",    \"sourceType\" : \"SensorWebSubsetDefinition\"  }, {    \"identifier\" : \"identifier\",    \"sourceType\" : \"SensorWebSubsetDefinition\"  } ],  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",  \"status\" : \"waiting\"}");
                                    break;
                                }
                            }
                        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(
            value = "",
            nickname = "deleteWacodisJobDefinition",
            notes = "deletes a single WacodisJobDefinition based on the ID supplied ",
            tags = {})
    @ApiResponses(
            value = {
                @ApiResponse(code = 204, message = "WacodisJobDefinition deleted "),
                @ApiResponse(code = 500, message = "unexpected error ", response = Error.class)
            })
    @RequestMapping(
            value = "/jobDefinitions/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteWacodisJobDefinition(
            @ApiParam(value = "ID of WacodisJobDefinition to delete ", required = true)
                    @PathVariable("id")
                    String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(
            value = "",
            nickname = "retrieveWacodisJobDefinitionById",
            notes = "Returns a WacodisJobDefinition based on a single ID ",
            response = WacodisJobDefinition.class,
            tags = {})
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "WacodisJobDefinition response ",
                        response = WacodisJobDefinition.class),
                @ApiResponse(code = 500, message = "unexpected error ", response = Error.class)
            })
    @RequestMapping(
            value = "/jobDefinitions/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<WacodisJobDefinition> retrieveWacodisJobDefinitionById(
            @ApiParam(value = "ID of WacodisJobDefinition to retrieve ", required = true)
                    @PathVariable("id")
                    String id) {
        getRequest()
                .ifPresent(
                        request -> {
                            for (MediaType mediaType :
                                    MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                                if (mediaType.isCompatibleWith(
                                        MediaType.valueOf("application/json"))) {
                                    ApiUtil.setExampleResponse(
                                            request,
                                            "application/json",
                                            "{  \"lastFinishedExecution\" : \"2000-01-23T04:56:07.000+00:00\",  \"execution\" : {    \"pattern\" : \"pattern\",    \"event\" : \"{}\"  },  \"useCase\" : \"useCase\",  \"temporalCoverage\" : {    \"duration\" : \"duration\",    \"previousExecution\" : true  },  \"processingTool\" : \"processingTool\",  \"created\" : \"2000-01-23T04:56:07.000+00:00\",  \"areaOfInterest\" : {    \"extent\" : [ -151.17018, -151.17018, -151.17018, -151.17018 ]  },  \"inputs\" : [ {    \"identifier\" : \"identifier\",    \"sourceType\" : \"SensorWebSubsetDefinition\"  }, {    \"identifier\" : \"identifier\",    \"sourceType\" : \"SensorWebSubsetDefinition\"  } ],  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",  \"status\" : \"waiting\"}");
                                    break;
                                }
                            }
                        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(
            value = "",
            nickname = "retrieveWacodisJobDefinitions",
            notes = "Returns a paginated list of WacodisJobDefinition ",
            response = PaginatedWacodisJobDefinitionResponse.class,
            tags = {})
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "WacodisJobDefinition response ",
                        response = PaginatedWacodisJobDefinitionResponse.class),
                @ApiResponse(code = 500, message = "unexpected error ", response = Error.class)
            })
    @RequestMapping(
            value = "/jobDefinitions",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<PaginatedWacodisJobDefinitionResponse> retrieveWacodisJobDefinitions(
            @ApiParam(value = "the page as an offset (default=0) ")
                    @Valid
                    @RequestParam(value = "page", required = false)
                    Integer page,
            @ApiParam(value = "the maximum number of results (default=100) ")
                    @Valid
                    @RequestParam(value = "size", required = false)
                    Integer size) {
        getRequest()
                .ifPresent(
                        request -> {
                            for (MediaType mediaType :
                                    MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                                if (mediaType.isCompatibleWith(
                                        MediaType.valueOf("application/json"))) {
                                    ApiUtil.setExampleResponse(request, "application/json", "null");
                                    break;
                                }
                            }
                        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
