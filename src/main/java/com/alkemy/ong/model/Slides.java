package com.alkemy.ong.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Slides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url")
    private String imageUrl;
    @Column
    private String text;
    @Column
    private String order;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organizations organizationId;



}
