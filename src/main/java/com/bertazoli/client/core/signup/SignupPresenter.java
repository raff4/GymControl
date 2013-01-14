package com.bertazoli.client.core.signup;

import java.util.Date;

import com.bertazoli.client.place.NameTokens;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;
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

    @Inject
    public SignupPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
        super(eventBus, view, proxy);
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
                
            }
        }));
    }
}
