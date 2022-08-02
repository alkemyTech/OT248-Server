package com.alkemy.ong.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import javax.validation.constraints.NotNull;
import lombok.Builder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categories")
    private Long categoyId;

    @NotNull(message = "Name may not be null")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Name may not be null")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Name may not be null")
    private String image;

    @Column(name = "cration_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Column(name = "update_date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column
    private boolean deleted = Boolean.FALSE;
}
