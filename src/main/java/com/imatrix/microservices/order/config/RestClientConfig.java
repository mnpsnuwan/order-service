package com.imatrix.microservices.order.config;

import com.imatrix.microservices.order.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

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
    public InventoryClient inventoryClient()
    {
        RestClient restClient = RestClient.builder()
                                          .baseUrl( inventoryServiceUrl )
                                          .requestFactory( getClientRequestFactory() )
                                          .build();
        var restClientAdapter = RestClientAdapter.create( restClient );
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor( restClientAdapter ).build();
        return httpServiceProxyFactory.createClient( InventoryClient.class );
    }

    private ClientHttpRequestFactory getClientRequestFactory()
    {
        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
                                                                                    .withConnectTimeout( Duration.ofSeconds( 3 ) )
                                                                                    .withReadTimeout( Duration.ofSeconds( 3 ) );
        return ClientHttpRequestFactories.get( clientHttpRequestFactorySettings );
    }
}
