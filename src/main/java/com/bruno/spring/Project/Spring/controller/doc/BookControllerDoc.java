package com.bruno.spring.Project.Spring.controller.doc;

import com.bruno.spring.Project.Spring.data.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookControllerDoc {
    @Operation(summary = "Find all books",description = "Returns all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    List<BookDTO> findAll();

    @Operation(summary = "Find a books by ID", description = "Returns a single books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    BookDTO findById(@PathVariable Long id);

    @Operation(summary = "Create a new book", description = "Creates a new book and returns the created entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input body")
    })
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    BookDTO createBook(@RequestBody BookDTO bookDTO);


    @Operation(summary = "Update an existing book", description = "Updates an existing book and returns the updated entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input body"),
            @ApiResponse(responseCode = "404", description = "book not found")
    })
    @PutMapping("/update")
    BookDTO updateBook(@RequestBody BookDTO bookDTO);

    @Operation(summary = "Delete a book by ID", description = "book a person and returns no content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "book not found")
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteBook(@PathVariable Long id);
}
