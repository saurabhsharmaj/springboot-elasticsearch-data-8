package com.Elastic8SpringBoot3.service;

import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticSearchService {

	private final RestClient elasticsearchRestClient;
	
	@Autowired
	public ElasticSearchService(RestClient elasticsearchRestClient)
	{
		this.elasticsearchRestClient = elasticsearchRestClient;
	}
}
