package com.hnu;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

@Service
public class SearchProductImpl implements SearchProductService{
	@Autowired
	private SearchProductDaoImpl searchProductDaoImpl;
	
	public java.util.List<ProductModel> searchProduct(ProductSearch productSearch) throws Exception {
		return searchProductDaoImpl.searchProduct(productSearch);
	}

	

	

}

