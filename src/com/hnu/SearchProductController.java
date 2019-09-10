package com.hnu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

public class SearchProductController {
	
	@Autowired
	private SearchProductImpl searchProductImpl ;
	

	@RequestMapping(value="/list")
	public String searchProduct(ProductSearch productSearch , Model model) throws Exception{

		List<ProductModel> searchProducts = searchProductImpl.searchProduct(productSearch);

		model.addAttribute("productModels", searchProducts);
		model.addAttribute("queryString", productSearch.getQueryString());
		model.addAttribute("catalog_name", productSearch.getCatalog_name());
		model.addAttribute("price", productSearch.getPrice());
		model.addAttribute("sort", productSearch.getSort());
		return "product_list";

	}
}