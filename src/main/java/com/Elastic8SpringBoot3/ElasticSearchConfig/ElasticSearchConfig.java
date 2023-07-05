package com.Elastic8SpringBoot3.ElasticSearchConfig;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import co.elastic.clients.transport.TransportUtils;

@Configuration
public class ElasticSearchConfig {

	@Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private String port;
    @Value("${elasticsearch.rest.username}")
    private String username;
    @Value("${elasticsearch.rest.password}")
	private String password;    
    @Value("${elasticsearch.fingerprint}")
    private String fingerprint;
 
    
   
    
    @Bean 
    public RestClient elasticsearchRestClient()
    {
    	try {
    		SSLContext sslContext = TransportUtils .sslContextFromCaFingerprint(fingerprint);
			/*
			 * final SSLContext sslContext = SSLContexts.custom()
			 * .loadTrustMaterial(keyStore.getURL(), keyStorePassword.toCharArray())
			 * .build();
			 */
    	    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    	 
    	       credentialsProvider.setCredentials(AuthScope.ANY,
    	                  new UsernamePasswordCredentials(username, password));
    	       RestClientBuilder builder = RestClient.builder(new HttpHost(host, Integer.parseInt(port), "https"))
    	         .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
    	           @Override
    	           public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
    	             return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider).setSSLContext(sslContext);
    	               }
    	             });
    	       return builder.build();
    	   }catch(Exception e) {
    	    e.printStackTrace();
    	    return null;
    	   }
    	    }
    }
