package com.alkemy.ong.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@SQLDelete(sql = "UPDATE news SET is_on = false WHERE new_id =?")
@Where(clause = "is_on = true")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "new_id")
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Name can not be empty.")
    @NotEmpty(message = "Name can not be empty.")
    @NotBlank(message = "Name can not be blank.")
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "Content can not be empty.")
    @NotEmpty(message = "Content can not be empty.")
    @NotBlank(message = "Content can not be blank.")
    private String content;

    @Column
    private String image;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    @Column(name = "is_on")
    private boolean isOn = Boolean.TRUE; //is_on = true if the entity persisted is on, false otherwise

}
