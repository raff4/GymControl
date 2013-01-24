package com.bertazoli.client.gin;

import com.bertazoli.client.core.MainPresenter;
import com.bertazoli.client.core.MainView;
import com.bertazoli.client.core.footer.FooterPresenter;
import com.bertazoli.client.core.footer.FooterView;
import com.bertazoli.client.core.header.HeaderPresenter;
import com.bertazoli.client.core.header.HeaderView;
import com.bertazoli.client.core.login.LoginPresenter;
import com.bertazoli.client.core.login.LoginView;
import com.bertazoli.client.core.signup.SignupPresenter;
import com.bertazoli.client.core.signup.SignupView;
import com.bertazoli.client.gatekeeper.LoggedInGatekeeper;
import com.bertazoli.client.place.ClientPlaceManager;
import com.bertazoli.client.place.DefaultPlace;
import com.bertazoli.client.place.NameTokens;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.bertazoli.client.core.workout.WorkoutPresenter;
import com.bertazoli.client.core.workout.WorkoutView;

public class ClientModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule(ClientPlaceManager.class));
        bind(LoggedInGatekeeper.class).in(Singleton.class);
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
        bindPresenter(MainPresenter.class, MainPresenter.MyView.class, MainView.class, MainPresenter.MyProxy.class);
        bindPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class, HeaderView.class);
        bindPresenterWidget(FooterPresenter.class, FooterPresenter.MyView.class, FooterView.class);
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class, LoginPresenter.MyProxy.class);
        bindPresenter(SignupPresenter.class, SignupPresenter.MyView.class, SignupView.class, SignupPresenter.MyProxy.class);

        bindPresenter(WorkoutPresenter.class, WorkoutPresenter.MyView.class, WorkoutView.class, WorkoutPresenter.MyProxy.class);
    }
}
