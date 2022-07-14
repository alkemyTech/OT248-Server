package com.alkemy.ong.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Slides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url")
    private String imageUrl;
    @Column
    private String text;
    @Column
    private Integer order;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organizations organizationId;



}
