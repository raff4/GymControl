package com.bertazoli.client.core.popup;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ErrorPopup extends PopupPanel {

    public interface Binder extends UiBinder<Widget, ErrorPopup> {}
    
    @UiField InlineLabel error;
    
    @Inject
    public ErrorPopup(Binder binder) {
        super(true);
        setWidget(binder.createAndBindUi(this));
    }

    public void setErrorMessage(String message) {
        error.setText(message);
        this.center();
    }
}
