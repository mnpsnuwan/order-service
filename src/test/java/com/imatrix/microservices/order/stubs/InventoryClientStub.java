package com.imatrix.microservices.order.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by IntelliJ IDEA.
 * Project : OrderService
 * Class : {@link InventoryClientStub}
 *
 * @author : Nuwan Samarasinghe
 */
public class InventoryClientStub
{
    public static void stubInventoryCall( String skuCode, Integer quantity )
    {
        stubFor( get( urlEqualTo( "/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity ) )
                         .willReturn( aResponse()
                                              .withStatus( 200 )
                                              .withHeader( "Content-Type", "application/json" )
                                              .withBody( "true" ) ) );
    }
}
