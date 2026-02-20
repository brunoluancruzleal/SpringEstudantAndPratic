package com.bruno.spring.Project.Spring.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;


public class BookDTO extends RepresentationModel<BookDTO> {


    @Schema(description = "Unique identifier of the books.", example = "1")
    private Long id;
    @Schema(description = "Author name", example = "Michael C. Feathers")
    private String author;
    @Schema(description = "Value book",example = "20.20")
    private BigDecimal price;
    @Schema(description = "Title", example = "Working effectively with legacy code")
    private String title;

    public BookDTO() {
    }

    public BookDTO(Long id, String author, BigDecimal price, String title) {
        this.id = id;
        this.author = author;

        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
