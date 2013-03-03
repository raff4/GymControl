package com.bertazoli.client.core.login;

import com.bertazoli.client.custom.CustomPresenter;
import com.bertazoli.client.custom.CustomView;
import com.bertazoli.client.events.LoginAuthenticatedEvent;
import com.bertazoli.client.place.NameTokens;
import com.bertazoli.client.rpc.LoginServiceAsync;
import com.bertazoli.shared.action.LoginAction;
import com.bertazoli.shared.action.LoginResult;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class LoginPresenter extends CustomPresenter<LoginPresenter.MyView, LoginPresenter.MyProxy> {

    public interface MyView extends CustomView {
        HasKeyDownHandlers getFocusPanel();
        HasText getUsername();
        HasText getPassword();
        HasClickHandlers getSendButton();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.login)
    @NoGatekeeper
    public interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private DispatchAsync dispatcher;
    private EventBus eventBus;
    private PlaceManager placeManager;
    private Provider<LoginServiceAsync> loginProvider;

    @Inject
    public LoginPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            DispatchAsync dispatcher, PlaceManager placeManager,
            Provider<LoginServiceAsync> loginProvider) {
        super(eventBus, view, proxy);
        this.dispatcher = dispatcher;
        this.eventBus = eventBus;
        this.placeManager = placeManager;
        this.loginProvider = loginProvider;
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }
    
    @Override
    protected void onReveal() {
        super.onReveal();
        getView().clear();
    }

    @Override
    protected void onBind() {
        super.onBind();
        registerHandler(getView().getSendButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                sendCredentialsToServer();
            }
        }));
        
        registerHandler(getView().getFocusPanel().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendCredentialsToServer();
                }
            }
        }));
    }

    private void sendCredentialsToServer() {
        dispatcher.execute(new LoginAction(getView().getUsername().getText(), getView().getPassword().getText()), new AsyncCallback<LoginResult>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println("login failed");
            }
            @Override
            public void onSuccess(LoginResult result) {
                if (result != null) {
                    LoginAuthenticatedEvent.fire(getEventBus(), result.getUser());
                    PlaceRequest placeRequest = new PlaceRequest(NameTokens.welcome);
                    placeManager.revealPlace(placeRequest);
                } else {
                    errorPopup.setErrorMessage("Invalid password");
                    getView().clear();
                }
            }
        });
    }
}
