package com.bertazoli.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.bertazoli.client.place.ClientPlaceManager;
import com.bertazoli.client.core.MainPresenter;
import com.bertazoli.client.core.MainView;
import com.bertazoli.client.place.DefaultPlace;
import com.bertazoli.client.place.NameTokens;

public class ClientModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule(ClientPlaceManager.class));

        bindPresenter(MainPresenter.class, MainPresenter.MyView.class, MainView.class, MainPresenter.MyProxy.class);

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.welcome);
    }
}
