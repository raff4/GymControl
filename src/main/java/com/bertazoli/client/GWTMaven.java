package com.bertazoli.client;

import com.google.gwt.core.client.EntryPoint;
import com.bertazoli.client.gin.ClientGinjector;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class GWTMaven implements EntryPoint {

    private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

    @Override
    public void onModuleLoad() {
        // This is required for Gwt-Platform proxy's generator
        DelayedBindRegistry.bind(ginjector);
    
        ginjector.getPlaceManager().revealCurrentPlace();
    }
}
