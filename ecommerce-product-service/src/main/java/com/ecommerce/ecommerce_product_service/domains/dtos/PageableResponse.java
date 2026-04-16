package com.ecommerce.ecommerce_product_service.domains.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse<T> {
    private List<T> data;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private  int totalPages;
    private boolean lastPage;
}
