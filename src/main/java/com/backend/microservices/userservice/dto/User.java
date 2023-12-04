package com.backend.microservices.userservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
@Document
public class User {

    @Id
    private String userId;

    @NotEmpty(message = "User First Name should not be empty")
    private String userFirstName;

    @NotEmpty(message = "User Last Name should not be empty")
    private String userLastName;

    @NotNull(message = "date should not be null")
    private Date dob;

//    @NotEmpty(message = "date should not be empty")
    private String email;

    private List<Address> address;

    private List<Blogs> blogs;

}
