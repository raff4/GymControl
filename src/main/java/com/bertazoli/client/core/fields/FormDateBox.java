package com.bertazoli.client.core.fields;

import com.bertazoli.client.core.constants.Dictionary;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.DateBox;

public class FormDateBox extends DateBox {

    public FormDateBox() {
        this.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(Dictionary.DATEFORMAT)));
    }
}
