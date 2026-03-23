package com.ecommerce.ecommerce_auth_service.domains.dtos;
import java.util.List;

public record CursorPageResponse <T>(List<T> data,
                                    int pageSize,
                                    Integer nextCursor,
                                    boolean hasNext){}
