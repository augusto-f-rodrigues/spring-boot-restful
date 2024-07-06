package com.example.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity()
@Table(name = "TB_PRODUCTS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID productId;

    @Column(nullable = false, columnDefinition = "text")
    public String name;

    @Column
    public BigDecimal value;
}
