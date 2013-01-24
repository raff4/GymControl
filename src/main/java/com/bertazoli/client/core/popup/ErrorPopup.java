package com.bertazoli.client.core.popup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ErrorPopup extends PopupPanel {

    public interface Binder extends UiBinder<Widget, ErrorPopup> {}
    
    @UiField Label error;
    @UiField Button close;
    
    @Inject
    public ErrorPopup(Binder binder) {
        super(true);
        setWidget(binder.createAndBindUi(this));
        close.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ErrorPopup.this.hide();
            }
        });
        
        this.setStyleName("box gradientBackground1 padding10");
    }

    public void setErrorMessage(String message) {
        error.setText(message);
        this.center();
    }
}
