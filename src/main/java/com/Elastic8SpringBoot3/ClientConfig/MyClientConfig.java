package com.Elastic8SpringBoot3.ClientConfig;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Getter;
import lombok.Setter;


@ConfigurationProperties("spring.elasticsearch.rest")
@Configuration
@Getter
@Setter
public class MyClientConfig {
	private String hostName;
    private int port;
    private String username;
    private String password;
    
    @Bean
    public ElasticsearchClient getElasticsearchClient() {
    	
    	final CredentialsProvider credentialsProvider= new BasicCredentialsProvider();
    	
    	credentialsProvider.setCredentials(AuthScope.ANY,
    			 new UsernamePasswordCredentials(username, password));
    	RestClientBuilder builder = RestClient.builder(new HttpHost(hostName, port))
    			.setHttpClientConfigCallback(httpClientBuilder -> 
    			httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
    	
    	RestClient restClient = builder.build();
    	
    	ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
    	
    	return new ElasticsearchClient(transport);
    }
	

}
