package com.bertazoli.client.core.signup;

import java.sql.Timestamp;
import java.util.Date;

import com.bertazoli.client.place.NameTokens;
import com.bertazoli.client.rpc.UserServiceAsync;
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

public class SignupPresenter extends Presenter<SignupPresenter.MyView, SignupPresenter.MyProxy> {

    public interface MyView extends View {
        HasText getUsername();
        HasText getPassword();
        HasText getConfirmPassword();
        HasText getFirstName();
        HasText getLastName();
        HasText getEmail();
        Date getDOB();
        HasClickHandlers getSendButton();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.signup)
    public interface MyProxy extends ProxyPlace<SignupPresenter> {
    }
    private Provider<UserServiceAsync> userProvider;

    @Inject
    public SignupPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            Provider<UserServiceAsync> userProvider) {
        super(eventBus, view, proxy);
        this.userProvider = userProvider;
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    @Override
    protected void onBind() {
        super.onBind();
        registerHandler(getView().getSendButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
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
                        System.out.println("fail");
                    }

                    @Override
                    public void onSuccess(User result) {
                        System.out.println("success");
                    }
                });
            }
        }));
    }
}
