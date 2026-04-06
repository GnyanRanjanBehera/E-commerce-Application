package com.ecommerce.ecommerce_product_service.domains.enities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id",nullable = false,unique = true)
    private Long prodId;

    @Column(name = "name",nullable = false)
    private  String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private  Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> productVariants=new ArrayList<>();

}
