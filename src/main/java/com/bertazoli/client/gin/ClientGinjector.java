package com.bertazoli.client.gin;

import com.bertazoli.client.core.MainPresenter;
import com.bertazoli.client.core.footer.FooterPresenter;
import com.bertazoli.client.core.header.HeaderPresenter;
import com.bertazoli.client.core.login.LoginPresenter;
import com.bertazoli.client.gatekeeper.LoggedInGatekeeper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.bertazoli.client.core.signup.SignupPresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {
    EventBus getEventBus();
    PlaceManager getPlaceManager();
    LoggedInGatekeeper getLoggedInGatekeeper();
    AsyncProvider<MainPresenter> getMainPresenter();
    AsyncProvider<HeaderPresenter> getHeaderPresenter();
    AsyncProvider<FooterPresenter> getFooterPresenter();
    AsyncProvider<LoginPresenter> getLoginPresenter();
    AsyncProvider<SignupPresenter> getSignupPresenter();
}
