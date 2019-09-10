package com.hnu;

import java.util.List;

public interface SearchProductService {
	public List<ProductModel> searchProduct(ProductSearch productSearch) throws Exception;
}
