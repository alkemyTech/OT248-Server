package com.alkemy.ong.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@SQLDelete(sql = "UPDATE news SET is_on = false WHERE new_id =?")
@Where(clause = "is_on = true")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "new_id")
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
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
