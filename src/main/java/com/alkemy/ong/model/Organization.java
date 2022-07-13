package com.alkemy.ong.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "organizations", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "address"),
        @UniqueConstraint(columnNames = "email")
})
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organization")
    private Integer idOrganization;

    @Column(nullable = false, name = "name")
    @NotNull(message = "Name can not be empty.")
    private String name;

    @Column(name = "image")
    @NotNull(message = "Image can not be empty.")
    private String image;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private Integer phone;

    @Column(nullable = false, name = "email")
    @NotNull(message = "Email can not be empty.")
    private String email;

    @Column(nullable = false, name = "welcome_text", columnDefinition = "TEXT")
    @NotNull(message = "WelcomeText can not be empty.")
    private String welcomeText;

    @Column(name = "about_us_text", columnDefinition = "TEXT")
    private String aboutUsText;

    @Column(nullable = false, updatable = false, name = "time_stamps")
    @CreationTimestamp
    private Date timestamp;

    @Column(name = "soft_delete")
    private boolean isSoftDelete;
}
