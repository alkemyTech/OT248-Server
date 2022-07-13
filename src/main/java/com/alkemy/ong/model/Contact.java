package com.alkemy.ong.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "contacts", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "phone"),
        @UniqueConstraint(columnNames = "email")
})
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Integer idContact;

    @Column(nullable = false, name = "name")
    @NotNull(message = "Name can not be empty.")
    private String name;

    @Column(nullable = false, name = "phone")
    @NotNull(message = "Phone can not be empty.")
    private Integer phone;

    @Column(nullable = false, name = "email")
    @NotNull(message = "Email can not be empty.")
    private String email;

    @Column(name = "message")
    private String message;

    @Column(name = "delete_at")
    private boolean deleteAt;
}
