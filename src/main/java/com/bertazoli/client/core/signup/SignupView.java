package com.bertazoli.client.core.signup;

import java.util.Date;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class SignupView extends ViewImpl implements SignupPresenter.MyView {

    private final Widget widget;
    
    @UiField TextBox username;
    @UiField TextBox firstname;
    @UiField TextBox lastname;
    @UiField PasswordTextBox password;
    @UiField PasswordTextBox confirmPassword;
    @UiField TextBox email;
    @UiField DateBox dob;
    @UiField Button send;

    public interface Binder extends UiBinder<Widget, SignupView> {
    }

    @Inject
    public SignupView(final Binder binder) {
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
    public HasText getConfirmPassword() {
        return confirmPassword;
    }

    @Override
    public HasText getFirstName() {
        return firstname;
    }

    @Override
    public HasText getLastName() {
        return lastname;
    }

    @Override
    public HasText getEmail() {
        return email;
    }

    @Override
    public Date getDOB() {
        return dob.getValue();
    }

    @Override
    public HasClickHandlers getSendButton() {
        return send;
    }
}
