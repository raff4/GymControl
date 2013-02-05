package com.bertazoli.client.core.validation;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ErrorPopup {
    private PopupPanel popupPanel = null;

    public void setErrorMessage(String errorMessage, Widget widget) {
        popupPanel = new PopupPanel(false);
        showError(errorMessage, widget);
    }

    public void showError(String errorMessage, Widget widget) {
        if (popupPanel != null) {
            popupPanel.setStyleName("error");
            HTML html = new HTML(errorMessage, true);
            popupPanel.add(html);
            
            popupPanel.setPopupPosition(widget.getAbsoluteLeft()+widget.getOffsetWidth(), widget.getAbsoluteTop());
        }
    }

    public void hide() {
        if (popupPanel != null) {
            popupPanel.hide();
        }
    }
}
