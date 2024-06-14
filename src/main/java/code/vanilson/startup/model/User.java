package code.vanilson.startup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

//@Entity
//@Table(name = "tb_users")
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Data
//@Builder
//public class User {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//    private String firstName;
//    private String lastName;
//    @Column(unique = true)
//    private String username;
//    @Column(unique = true)
//    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
//    @NotEmpty(message = "Email cannot be empty")
//    private String email;
//    private String password;
//    @Enumerated(EnumType.STRING)
//    private ROLE roles;
//}
