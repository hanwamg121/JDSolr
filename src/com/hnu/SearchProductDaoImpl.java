package com.hnu;

import HttpSolrClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class SearchProductDaoImpl {
	@Autowired
	private SolrServer solrServer;

	public List<ProductModel> searchProduct(ProductSearch productSearch) throws Exception {
		final String solrUrl = "http://localhost:8983/solr/#/tika";
		HttpSolrClient client = new HttpSolrClient.Builder(solrUrl).build();
		
		SolrQuery solrQuery = new SolrQuery();
		QueryResponse=client.query(query);
		
		solrQuery.setQuery(productSearch.getQueryString());
		solrQuery.set("df", "product_keywords");

		if(null != productSearch.getCatalog_name() && !"".equals(productSearch.getCatalog_name())){
			solrQuery.set("fq", "product_catalog_name:" + productSearch.getCatalog_name());
		}
		if(null != productSearch.getPrice() && !"".equals(productSearch.getPrice())){

			String[] p = productSearch.getPrice().split("-");
			solrQuery.set("fq", "product_price:[" + p[0] + " TO " + p[1] + "]");
		}

		if ("1".equals(productSearch.getSort())) {
			solrQuery.addSort("product_price", ORDER.desc);
		} else {
			solrQuery.addSort("product_price", ORDER.asc);
		}

		solrQuery.setStart(0);
		solrQuery.setRows(16);
		solrQuery.set("fl", "id,product_name,product_price,product_picture");


		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("product_name");
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList docs = response.getResults();

		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		List<ProductModel> productModels = new ArrayList<ProductModel>();
		for (SolrDocument doc : docs) {
			ProductModel productModel = new ProductModel();
			productModel.setPid((String) doc.get("id"));
			productModel.setPrice((Float) doc.get("product_price"));
			productModel.setPicture((String) doc.get("product_picture"));
			Map<String, List<String>> map = highlighting.get((String) doc.get("id"));
			List<String> list = map.get("product_name");
			
			productModel.setName(list.get(0));
			productModels.add(productModel);
		}
		return productModels;
		
		client.commit();
		client.close();
	}

}
