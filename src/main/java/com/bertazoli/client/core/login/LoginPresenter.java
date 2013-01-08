package com.bertazoli.client.core.login;

import com.bertazoli.client.place.NameTokens;
import com.bertazoli.client.rpc.LoginServiceAsync;
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
        HasClickHandlers getCreateAccountButton();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.login)
    public interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private Provider<LoginServiceAsync> loginServiceProvider;

    @Inject
    public LoginPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy, Provider<LoginServiceAsync> loginServiceProvider) {
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
        
        registerHandler(getView().getCreateAccountButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                System.out.println("user clicked on create account button");
            }
        }));
    }

    private void doLogin(String username, String password) {
        LoginServiceAsync login = loginServiceProvider.get();
        login.helloWorld(username, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("Result from server: " + result);
            }
            
            @Override
            public void onFailure(Throwable caught) {
                System.out.println("deu merda");
            }
        });
    }
}
