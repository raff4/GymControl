package com.bertazoli.client.core.validation;

import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.inject.Inject;

public abstract class Validator {
    
    protected ValueBoxBase<String> field;
    protected ValidationResult validationResult = new ValidationResult();
    protected boolean emptyAllowed;
    
    @Inject
    public Validator() {
        
    }

    public void setField(ValueBoxBase<String> field) {
        this.field = field;
    }
    
    public abstract ValidationResult validate();

    public boolean isEmptyAllowed() {
        return emptyAllowed;
    }

    public void setEmptyAllowed(boolean emptyAllowed) {
        this.emptyAllowed = emptyAllowed;
    }
}
