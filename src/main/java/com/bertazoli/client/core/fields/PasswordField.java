package com.bertazoli.client.core.fields;

import com.bertazoli.client.core.constants.Dictionary;
import com.bertazoli.client.core.validation.ValidationResult;
import com.bertazoli.client.core.validation.Validator;
import com.bertazoli.shared.Util;

public class PasswordField extends Validator implements HasMaxLength, HasMinLength {
    private Integer minLength = -1;
    private Integer maxLength = -1;
    
    @Override
    public ValidationResult validate() {
        if (!isEmptyAllowed() && Util.isNullOrEmpty(field.getValue())) {
            validationResult.setError(field, Dictionary.EMPTYNOTALLOWED);
            return validationResult;
        }
        
        if (minLength != -1 && field.getText().length() < minLength) {
            validationResult.setError(field, Dictionary.MINLENGTH(minLength));
            return validationResult;
        }
        
        if (maxLength != -1 && field.getText().length() > maxLength) {
            validationResult.setError(field, Dictionary.MAXLENGTH(maxLength));
            return validationResult;
        }
        
        validationResult.setError(false);
        return validationResult;
    }

    @Override
    public void setMinLength(Integer length) {
        this.minLength = length;
    }

    @Override
    public Integer getMinLength() {
        return minLength;
    }

    @Override
    public void setMaxLength(Integer length) {
        this.maxLength = length;
    }

    @Override
    public Integer getMaxLength() {
        return maxLength;
    }
}
