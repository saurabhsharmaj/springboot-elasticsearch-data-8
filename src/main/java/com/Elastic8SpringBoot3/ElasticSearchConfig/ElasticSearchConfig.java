package com.Elastic8SpringBoot3.ElasticSearchConfig;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    		
    		String keyStorePass = "welcome1";
            Path trustStorePath = Paths.get("D:\\springboot-elasticsearch-data-8\\src\\main\\resources\\http.p12");
            KeyStore truststore = KeyStore.getInstance("pkcs12");
            try (InputStream is = Files.newInputStream(trustStorePath)) {
                truststore.load(is, keyStorePass.toCharArray());
            }
            SSLContextBuilder sslBuilder = SSLContexts.custom()
                .loadTrustMaterial(truststore, null);
            SSLContext sslContext = sslBuilder.build();
            
    	    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    	 
    	       credentialsProvider.setCredentials(AuthScope.ANY,  new UsernamePasswordCredentials(username, password));
    	       RestClientBuilder builder = RestClient.builder(new HttpHost(host, Integer.parseInt(port), "https"))
    	         .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
    	           @Override
    	           public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
    	             return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
    	            		 .setSSLContext(sslContext)
    	            		 ;
    	               }
    	             })
    	    		   ;
    	       return builder.build();
    	   }catch(Exception e) {
    	    e.printStackTrace();
    	    return null;
    	   }
    	    }
    }
