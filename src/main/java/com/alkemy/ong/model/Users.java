package com.alkemy.ong.model;

import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE Users SET deleted = true WHERE id=?")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "firstName")
    @NotNull(message = "firstName can not be empty.")
    private String firstName;

    @Column(nullable = false, name = "lastName")
    @NotNull(message = "lastName can not be empty.")
    private String lastName;

    @Email
    @Column(nullable = false,name = "email",unique=true)
    @NotNull(message = "email can not be empty.")
    private String email;

    @Column(nullable = false, name = "password")
    @NotNull(message = "password can not be empty.")
    private String password;

    @Column(name = "photo")
    @NotNull(message = "photo can not be empty.")
    private String photo;

    @Column(name = "role_id")
    private Long roleId;

    @Column(nullable = false, updatable = false,name = "created_on")
    @CreationTimestamp
    private Date createdOnTimestamp;

    @Column(nullable = false,name = "updated_on")
    @UpdateTimestamp
    private Date updatedOnTimestamp;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;
}