package com.imatrix.microservices.order.config;

import com.imatrix.microservices.order.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Created by IntelliJ IDEA.
 * Project : SpringBoot
 * Class : {@link RestClientConfig}
 *
 * @author : Nuwan Samarasinghe
 */

@Configuration
public class RestClientConfig
{
    @Value( "${inventory.url}" )
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryClient(){
        RestClient restClient = RestClient.builder()
                                        .baseUrl( inventoryServiceUrl )
                                        .build();
        var restClientAdapter = RestClientAdapter.create( restClient );
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor( restClientAdapter ).build();
        return httpServiceProxyFactory.createClient( InventoryClient.class );
    }
}
