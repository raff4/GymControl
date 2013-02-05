package com.bertazoli.client.core.validation;

import com.google.gwt.user.client.ui.Widget;

public class ValidationResult {
    
    private String errorMessage = null;
    private ErrorPopup errorPopup;
    private Widget widget;
    
    public ValidationResult(String errorMessage, Widget widget) {
        this.errorMessage = errorMessage;
        this.widget = widget;
    }

    public ErrorPopup getErrorPopup() {
        return errorPopup;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setErrorPopup(ErrorPopup errorPopup) {
        this.errorPopup = errorPopup;
    }
}
