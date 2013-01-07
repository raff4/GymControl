package com.bertazoli.client.core.login;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class LoginView extends ViewImpl implements LoginPresenter.MyView {

    private final Widget widget;
    
    @UiField TextBox username;
    @UiField PasswordTextBox password;
    @UiField Button send;

    public interface Binder extends UiBinder<Widget, LoginView> {
    }

    @Inject
    public LoginView(final Binder binder) {
        widget = binder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasText getUsername() {
        return username;
    }

    @Override
    public HasText getPassword() {
        return password;
    }

    @Override
    public HasClickHandlers getSendButton() {
        return send;
    }
}
