package com.bertazoli.client.core.menu;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainMenu extends Composite {
    private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);
    
    interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {}
    
    public MainMenu() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
