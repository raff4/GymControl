package com.bertazoli.client.core;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class MainView extends ViewImpl implements MainPresenter.MyView {

    private final Widget widget;
    
    @UiField HTMLPanel header;
    @UiField HTMLPanel content;
    @UiField HTMLPanel footer;
    @UiField HTMLPanel menu;

    public interface Binder extends UiBinder<Widget, MainView> {
    }

    @Inject
    public MainView(final Binder binder) {
        widget = binder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }
    
    public void setInSlot(Object slot, Widget widget) {
        if (slot == MainPresenter.TYPE_SetHeader) {
            header.clear();
            header.add(widget);
        } else if (slot == MainPresenter.TYPE_SetContent) {
            content.clear();
            content.add(widget);
        } else if (slot == MainPresenter.TYPE_SetFooter) {
            footer.clear();
            footer.add(widget);
        } else {
            super.setInSlot(slot, widget);
        }
    }

    @Override
    public HTMLPanel getMenuPanel() {
        return menu;
    }
}
