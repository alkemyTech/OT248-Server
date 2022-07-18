package com.alkemy.ong.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Data
public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required.")
    @NotEmpty(message = "Name is required.")
    private String name;

    private String image;
    private String content;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    private Boolean status = true; //status = false, is deleted
}
