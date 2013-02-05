package com.bertazoli.client.core.validation;

import java.util.ArrayList;

import com.google.inject.Singleton;

@Singleton
public class ValidationManager {
    
    private ArrayList<ValidationResult> results = new ArrayList<ValidationResult>();
    private ArrayList<ValidationAction> actions = new ArrayList<ValidationAction>();
    
    public void reset() {
        for (ValidationResult result : results) {
            if (result.getErrorPopup() != null) {
                result.getErrorPopup().hide();
            }
        }
    }

    public void add(ValidationResult validationResult) {
        if (validationResult != null) {
            results.add(validationResult);
        }
    }

    public boolean hasErrors() {
        return results.size() > 0;
    }
    
    public void displayErrors() {
        if (results.size() > 0) {
            for (ValidationResult result : results) {
                showError(result);
            }
        }
    }

    private void showError(ValidationResult result) {
        if (result.getErrorPopup() == null) {
            ErrorPopup errorPopup = new ErrorPopup();
            errorPopup.setErrorMessage(result.getErrorMessage(), result.getWidget());
            result.setErrorPopup(errorPopup);
        } else {
            result.getErrorPopup().setErrorMessage(result.getErrorMessage(), result.getWidget());
        }
    }
    
    public void doActions() {
        for (ValidationResult result : results) {
            for (ValidationAction action : actions) {
                action.execute(result.getWidget());
            }
        }
    }
}
