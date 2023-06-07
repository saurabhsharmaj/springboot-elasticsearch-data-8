package com.Elastic8SpringBoot3.ElasticSearchConfig;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ElasticSearchConfig {

	@Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private String port;
    @Value("${server.ssl.key-store}")
    private Resource trustStore;
    @Value("${server.ssl.key-store-password}")
    private String trustStorePassword;
    
    String username = "elastic";
    String password = "yL+ulSl=4q=2_Or*e99j";
    
    @Bean 
    public RestClient elasticsearchRestClient()
    {
    	try {
    	    final SSLContext sslContext = SSLContexts.custom()
    	            .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
    	            .build();
    	    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    	 
    	       credentialsProvider.setCredentials(AuthScope.ANY,
    	                  new UsernamePasswordCredentials(username, password));
    	 
    	       //Create a client.
    	       RestClientBuilder builder = RestClient.builder(new HttpHost(host, Integer.parseInt(port), "https"))
    	         .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
    	           @Override
    	           public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
    	             return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider).setSSLContext(sslContext);
    	               }
    	             });
//    	         .setDefaultHeaders(compatibilityHeaders());
    	       return builder.build();
    	   }catch(Exception e) {
    	    e.printStackTrace();
    	    return null;
    	   }
    	    }
//    	    private Header[] compatibilityHeaders() {
//    	        return new Header[]{new BasicHeader(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7"),
//    	          new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7")};
//    	     }
    }
