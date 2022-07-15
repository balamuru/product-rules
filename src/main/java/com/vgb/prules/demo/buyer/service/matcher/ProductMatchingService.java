package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.common.domain.Product;

public interface ProductMatchingService {

    ProductMatchResult match(Product product);
}
