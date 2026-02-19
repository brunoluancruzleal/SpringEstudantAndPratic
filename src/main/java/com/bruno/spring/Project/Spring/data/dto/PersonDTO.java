package com.bruno.spring.Project.Spring.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "Unique identifier of the Person.", example = "1")
    private Long id;
    @Schema(description = "Name of the Person.", example = "Bruno")
    private String name;
    @Schema(description = "First name of the Person.", example = "Bruno")
    private String firstName;
    @Schema(description = "Last name of the Person.", example = "Bruno")
    private String lastName;
    @Schema(description = "Address of the Person.", example = "123 Main St, Anytown, USA")
    private String address;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "Phone number of the Person.", example = "+55 99999-9999")
    private String phoneNumber;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PersonDTO() {
    }

    public PersonDTO(Long id, String name, String firstName) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFoneNumber() {
        return phoneNumber;
    }

    public void setFoneNumber(String foneNumber) {
        this.phoneNumber = foneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTO person)) return false;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
