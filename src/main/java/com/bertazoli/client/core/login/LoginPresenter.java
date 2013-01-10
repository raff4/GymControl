package com.bertazoli.client.core.login;

import com.bertazoli.client.place.NameTokens;
import com.bertazoli.client.rpc.LoginServiceAsync;
import com.bertazoli.shared.beans.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> {

    public interface MyView extends View {
        HasText getUsername();
        HasText getPassword();
        HasClickHandlers getSendButton();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.login)
    public interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private Provider<LoginServiceAsync> loginServiceProvider;

    @Inject
    public LoginPresenter(EventBus eventBus, MyView view, MyProxy proxy, Provider<LoginServiceAsync> loginServiceProvider) {
        super(eventBus, view, proxy);
        this.loginServiceProvider = loginServiceProvider;
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    @Override
    protected void onBind() {
        super.onBind();
        setuClickHandlers();
    }

    private void setuClickHandlers() {
        registerHandler(getView().getSendButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doLogin(getView().getUsername().getText(), getView().getPassword().getText());
            }
        }));
    }

    private void doLogin(String username, String password) {
        LoginServiceAsync login = loginServiceProvider.get();
        login.validateUser(username, password, new AsyncCallback<User>() {
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(User result) {
                System.out.println(result);
            }
        });
    }
    
    public void display() {
        revealInParent();
    }
}
