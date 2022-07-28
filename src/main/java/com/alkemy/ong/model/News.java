package com.alkemy.ong.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE news SET deleted = true WHERE new_id =?")
@Where(clause = "deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "new_id")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name can not be empty.")
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Content can not be empty.")
    private String content;

    @Column
    private String image;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column
    private boolean deleted = Boolean.FALSE;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "news")
    private List<Testimonial> testimonials;

}