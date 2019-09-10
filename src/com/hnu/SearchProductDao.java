package com.hnu;

import java.util.List;

public interface SearchProductDao {
	public List<ProductModel> searchProduct(ProductSearch productSearch) throws Exception;
}
