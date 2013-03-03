package com.bertazoli.client.core.fields;

import com.bertazoli.client.core.constants.Dictionary;
import com.bertazoli.client.core.validation.ValidationResult;
import com.bertazoli.client.core.validation.Validator;
import com.bertazoli.shared.Util;

public class NumberField extends Validator {

    @Override
    public ValidationResult validate() {
        if (!isEmptyAllowed() && Util.isNullOrEmpty(field.getValue())) {
            validationResult.setError(field, Dictionary.EMPTYNOTALLOWED);
            return validationResult;
        }
        
        if (!field.getValue().matches("^\\d*$")) {
            validationResult.setError(field, Dictionary.ONLYNUMBERSALLOWED);
            return validationResult;
        }
        
        validationResult.setError(false);
        return validationResult;
    }

}
