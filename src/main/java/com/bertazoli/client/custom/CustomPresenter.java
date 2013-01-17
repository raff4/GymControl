package com.bertazoli.client.custom;

import com.bertazoli.client.core.popup.ErrorPopup;
import com.bertazoli.client.manager.SecurityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;

public abstract class CustomPresenter<V extends CustomView, Proxy_ extends Proxy<?>> extends Presenter<V, Proxy<?>> {
    
    public CustomPresenter(EventBus eventBus, V view, Proxy_ proxy) {
        super(eventBus, view, proxy);
    }

    protected SecurityManager securityManager;
    protected PlaceManager placeManager;
    protected ErrorPopup errorPopup;

    @Inject
    public void setPlaceManager(PlaceManager placeManager) {
        this.placeManager = placeManager;
    }
    
    @Inject
    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }
    
    @Inject
    public void setErrorPopup(ErrorPopup errorPopup) {
        this.errorPopup = errorPopup;
    }
    
    public void setErrorMessage(String message) {
        errorPopup.setErrorMessage(message);
    }
}
