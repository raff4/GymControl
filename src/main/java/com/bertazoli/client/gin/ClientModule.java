package com.bertazoli.client.gin;

import com.bertazoli.client.core.MainPresenter;
import com.bertazoli.client.core.MainView;
import com.bertazoli.client.core.login.LoginPresenter;
import com.bertazoli.client.core.login.LoginView;
import com.bertazoli.client.place.ClientPlaceManager;
import com.bertazoli.client.place.DefaultPlace;
import com.bertazoli.client.place.NameTokens;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule(ClientPlaceManager.class));

        bindPresenter(MainPresenter.class, MainPresenter.MyView.class, MainView.class, MainPresenter.MyProxy.class);
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class, LoginPresenter.MyProxy.class);
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
    }
}
