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
@Table(name = "slides")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url")
    private String imageUrl;
    @Column
    private String text;
    @Column
    private Integer position;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;



}
