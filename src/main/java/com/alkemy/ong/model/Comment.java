package com.alkemy.ong.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Comment can not be empty")
    @NotNull(message = "Comment can not be null")
    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinTable(name = "user_id")
    private Users userId;

    @ManyToOne
    @JoinTable(name = "news_id")
    private News newsId;

}
