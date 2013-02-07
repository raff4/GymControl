package com.bertazoli.client.core.fields;

import com.bertazoli.client.core.validation.ValidationResult;
import com.bertazoli.client.core.validation.Validator;
import com.google.gwt.user.datepicker.client.DateBox;

public class DateField extends Validator {
    protected DateBox field;

    public void setField(DateBox field) {
        super.setField(null);
        this.field = field;
    }

    @Override
    public ValidationResult validate() {
        if (!isEmptyAllowed()) {
            if (field.getValue() == null) {
                validationResult.setError(field, "Empty not allowed");
            }
        }
        return validationResult;
    }
}
