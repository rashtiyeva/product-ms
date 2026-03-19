package org.example.domain.product.dao.repository;

import org.example.domain.product.dao.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}
