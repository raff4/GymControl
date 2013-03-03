package com.bertazoli.client.core.fields;

import java.util.Date;

import com.bertazoli.client.core.constants.Dictionary;
import com.bertazoli.client.core.validation.ValidationResult;
import com.bertazoli.client.core.validation.Validator;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.DateBox;

public class DateField extends Validator {
    protected DateBox field;

    public void setField(DateBox field, boolean emptyAllowed) {
        this.field = field;
        setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(Dictionary.DATEFORMAT)));
        super.setField(null,emptyAllowed);
    }

    @Override
    public ValidationResult validate() {
        if (!isEmptyAllowed() && field.getValue() == null) {
            validationResult.setError(field, Dictionary.EMPTYNOTALLOWED);
            return validationResult;
        }
        
        validationResult.setError(false);
        return validationResult;
    }
    
    @Override
    protected void setValueChangeHandlers() {
        field.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                validate();
                if (validationResult.isError()) {
                    validationResult.showError();
                } else {
                    validationResult.clear();
                }
            }
        });
    }
    
    public void setFormat(DateBox.DefaultFormat format) {
        field.setFormat(format);
    }
}
