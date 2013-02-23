package com.bertazoli.client.core.validation;

import java.util.ArrayList;

import com.google.inject.Singleton;

@Singleton
public class ValidationManager {
    
    private ArrayList<ValidationResult> results = new ArrayList<ValidationResult>();
    
    public void reset() {
        for (ValidationResult result : results) {
            if (result != null && result.isError()) {
                result.clear();
            }
        }
        results.clear();
    }

    public void add(ValidationResult validationResult) {
        if (validationResult != null) {
            results.add(validationResult);
        }
    }

    public boolean hasErrors() {
        for (ValidationResult result : results) {
            if (result.isError()) {
                return true;
            }
        }
        return false;
    }
    
    public void displayErrors() {
        if (results.size() > 0) {
            for (ValidationResult result : results) {
                if (result.isError()) {
                    result.showError();
                }
            }
        }
    }

    public void addValidationResult(ValidationResult result) {
        if (result != null) {
            results.add(result);
        }
    }
}
