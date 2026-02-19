package com.bruno.spring.Project.Spring.controller.doc;

import com.bruno.spring.Project.Spring.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonControllerDoc {

    @Operation(summary = "Find a person by ID", description = "Returns a single person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    PersonDTO findById(@PathVariable Long id);

    @Operation(summary = "Find all people", description = "Returns a list of people")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    List<PersonDTO> findAll();

    @Operation(summary = "Create a new person", description = "Creates a new person and returns the created entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input body")
    })
    @PostMapping(value = "/create",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    PersonDTO creatingPerson(@RequestBody PersonDTO person);

    @Operation(summary = "Update an existing person", description = "Updates an existing person and returns the updated entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input body"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @PutMapping("/update")
    PersonDTO updatingPerson(@RequestBody PersonDTO person);

    @Operation(summary = "Delete a person by ID", description = "Deletes a person and returns no content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Person deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deletePerson(@PathVariable Long id);
}
