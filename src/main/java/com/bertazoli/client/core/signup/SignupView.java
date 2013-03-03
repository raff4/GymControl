package com.bertazoli.client.core.signup;

import java.util.Date;

import com.bertazoli.client.core.fields.DateField;
import com.bertazoli.client.core.fields.EmailField;
import com.bertazoli.client.core.fields.PasswordField;
import com.bertazoli.client.core.fields.TextField;
import com.bertazoli.client.core.validation.ValidationManager;
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
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.ViewImpl;

public class SignupView extends ViewImpl implements SignupPresenter.MyView {

    private final Widget widget;
    
    @UiField TextBox username;
    private TextField usernameField;
    @UiField TextBox firstname;
    private TextField firstnameField;
    @UiField TextBox lastname;
    private TextField lastnameField;
    @UiField PasswordTextBox password;
    private PasswordField passwordField;
    @UiField PasswordTextBox confirmPassword;
    private PasswordField confirmPasswordField;
    @UiField TextBox email;
    private EmailField emailField;
    @UiField DateBox dob;
    private DateField dobDateField;
    @UiField Button send;
    private ValidationManager validationManager;

    public interface Binder extends UiBinder<Widget, SignupView> {
    }

    @Inject
    public SignupView(final Binder binder,
            Provider<DateField> dateFieldProvider,
            Provider<TextField> textFieldProvider,
            Provider<PasswordField> passwordFieldProvider,
            Provider<EmailField> emailFieldProvider,
            ValidationManager validationManager) {
        widget = binder.createAndBindUi(this);
        this.validationManager = validationManager;
        
        usernameField = textFieldProvider.get();
        usernameField.setField(username, false);
        
        firstnameField = textFieldProvider.get();
        firstnameField.setField(firstname, false);
        
        lastnameField = textFieldProvider.get();
        lastnameField.setField(lastname, false);
        
        passwordField = passwordFieldProvider.get();
        passwordField.setMinLength(6);
        passwordField.setMaxLength(12);
        passwordField.setField(password, false);
        
        confirmPasswordField = passwordFieldProvider.get();
        confirmPasswordField.setMinLength(6);
        confirmPasswordField.setMaxLength(12);
        confirmPasswordField.setField(confirmPassword, false);
        
        emailField = emailFieldProvider.get();
        emailField.setField(email, false);
        
        dobDateField = dateFieldProvider.get();
        dobDateField.setField(dob, false);
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
    public PasswordTextBox getPassword() {
        return password;
    }

    @Override
    public PasswordTextBox getConfirmPassword() {
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

    @Override
    public boolean passwordMatch() {
        if (password.getText() != null && password.getText().length() >= passwordField.getMinLength() &&
                password.getText().equals(confirmPassword.getText())) {
            password.setStyleName("passwordMatch", true);
            confirmPassword.setStyleName("passwordMatch", true);
            return true;
        } else {
            password.setStyleName("passwordMatch", false);
            confirmPassword.setStyleName("passwordMatch", false);
            return false;
        }
    }

    @Override
    public void clear() {
        username.setText(null);
        firstname.setText(null);
        lastname.setText(null);
        password.setText(null);
        confirmPassword.setText(null);
        email.setText(null);
        dob.setValue(null);
    }

    @Override
    public boolean validate() {
        validationManager.reset();
        
        validationManager.addValidationResult(usernameField.validate());
        validationManager.addValidationResult(dobDateField.validate());
        validationManager.addValidationResult(passwordField.validate());
        validationManager.addValidationResult(confirmPasswordField.validate());
        validationManager.addValidationResult(emailField.validate());
        validationManager.addValidationResult(firstnameField.validate());
        validationManager.addValidationResult(lastnameField.validate());
        
        validationManager.displayErrors();
        
        return !validationManager.hasErrors();
    }
}
