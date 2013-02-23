package com.bertazoli.client.core.signup;

import java.sql.Timestamp;
import java.util.Date;

import com.bertazoli.client.custom.CustomPresenter;
import com.bertazoli.client.custom.CustomView;
import com.bertazoli.client.events.LoginAuthenticatedEvent;
import com.bertazoli.client.place.NameTokens;
import com.bertazoli.client.rpc.UserServiceAsync;
import com.bertazoli.shared.beans.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class SignupPresenter extends CustomPresenter<SignupPresenter.MyView, SignupPresenter.MyProxy> {

    public interface MyView extends CustomView {
        HasText getUsername();
        PasswordTextBox getPassword();
        PasswordTextBox getConfirmPassword();
        HasText getFirstName();
        HasText getLastName();
        HasText getEmail();
        Date getDOB();
        HasClickHandlers getSendButton();
        boolean passwordMatch();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.signup)
    public interface MyProxy extends ProxyPlace<SignupPresenter> {
    }
    private Provider<UserServiceAsync> userProvider;
    private PlaceManager placeManager;

    @Inject
    public SignupPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            Provider<UserServiceAsync> userProvider,
            PlaceManager placeManager) {
        super(eventBus, view, proxy);
        this.userProvider = userProvider;
        this.placeManager = placeManager;
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
                doLogin();
            }
        }));
        
        registerHandler(getView().getConfirmPassword().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                validatePassword();
            }
        }));
        registerHandler(getView().getConfirmPassword().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                validatePassword();
            }
        }));
        
        registerHandler(getView().getPassword().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                validatePassword();
            }
        }));
        registerHandler(getView().getPassword().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                validatePassword();
            }
        }));
    }
    
    private boolean validatePassword() {
        return getView().passwordMatch();
    }
    
    private void doLogin() {
        if (getView().validate()) {
            UserServiceAsync action = userProvider.get();
            User user = new User();
            user.setUsername(getView().getUsername().getText());
            user.setPassword(getView().getPassword().getText());
            user.setFirstName(getView().getFirstName().getText());
            user.setLastName(getView().getLastName().getText());
            user.setEmail(getView().getEmail().getText());
            user.setDob(new Timestamp(getView().getDOB().getTime()));
            action.create(user, new AsyncCallback<User>() {
                @Override
                public void onFailure(Throwable caught) {
                    errorPopup.setErrorMessage("error creating account");
                    errorPopup.show();
                }

                @Override
                public void onSuccess(User result) {
                    if (result != null && result.isLoggedIn()) {
                        getEventBus().fireEvent(new LoginAuthenticatedEvent(result));
                        PlaceRequest request = new PlaceRequest(NameTokens.welcome);
                        placeManager.revealPlace(request);
                    }
                }
            });
        }
    }
}
