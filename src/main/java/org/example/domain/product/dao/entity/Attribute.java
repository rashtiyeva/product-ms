package org.example.domain.product.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.domain.product.model.enums.AttributeStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    AttributeStatus status;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "attribute",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true)
    Set<AttributeValue> values = new HashSet<>();

    @OneToMany(mappedBy = "attribute")
    Set<CategoryAttribute> categories = new HashSet<>();

}