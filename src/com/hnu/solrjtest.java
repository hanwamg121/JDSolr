package com.hnu;

public class solrjtest {
	package solrj;

	import java.io.File;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.solr.client.solrj.impl.HttpSolrClient;
	import org.apache.solr.common.SolrInputDocument;
	import org.junit.Test;



	public class solrjtest {
		


		            
		public static void  addDocument() throws Exception {
	        
					
	        final String solrUrl = "http://localhost:8081/solr/new_core";

	        //创建solrClient同时指定超时时间，不指定走默认配置

	        HttpSolrClient solrServer = new HttpSolrClient.Builder(solrUrl)

	                .withConnectionTimeout(10000)

	                .withSocketTimeout(60000)

	                .build();        		   

	        // 2、 创建SolrInputDocument对象，然后通过它来添加域。
	       SolrInputDocument document = new SolrInputDocument();
	        // 第一个参数：域的名称，域的名称必须是在schema.xml中定义的
	        // 第二个参数：域的值
	        // 注意：id的域不能少
	       
		        
		  	  
		  	     //添加数据
		    document.addField("id", "i");
	        document.addField("filename", "f1.getName() ");
	        document.addField("length", "f1.length()");
	        document.addField("path", "f1.getAbsolutePath()");
	        document.addField("content_ik", "fileText");
	        // 3、 通过HttpSolrServer对象将SolrInputDocument添加到索引库。
	       solrServer.add(document);
	        // 4、 提交。
	        solrServer.commit();
	     

	        }
		
	        public static void main(String[] args) throws Exception {  
	        	addDocument();  
	        }  
		
		
		
	}

}
