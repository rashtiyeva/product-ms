package org.example.domain.product.dao.repository;

import org.example.domain.product.dao.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
}
