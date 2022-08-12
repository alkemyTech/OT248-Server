package com.alkemy.ong.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE testimonials SET deleted = true WHERE id =?")
@Where(clause = "deleted = false")
@Table(name = "testimonials")

public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required.")
    @NotEmpty(message = "Name is required.")
    private String name;

    private String image;
    private String content;

    @Column(nullable = false, updatable = false,name = "created_on")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOnTimestamp;

    @Column(name = "updated_on")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOnTimestamp;

    private boolean deleted = Boolean.FALSE;
}