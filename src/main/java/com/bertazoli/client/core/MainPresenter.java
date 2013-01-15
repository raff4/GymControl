package com.bertazoli.client.core;

import com.bertazoli.client.core.footer.FooterPresenter;
import com.bertazoli.client.core.header.HeaderPresenter;
import com.bertazoli.client.core.menu.MainMenu;
import com.bertazoli.client.gatekeeper.LoggedInGatekeeper;
import com.bertazoli.client.place.NameTokens;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPresenter extends Presenter<MainPresenter.MyView, MainPresenter.MyProxy> {

    public interface MyView extends View {

        HTMLPanel getMenuPanel();
    }
    
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetHeader = new Type<RevealContentHandler<?>>();
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetContent = new Type<RevealContentHandler<?>>();
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetFooter = new Type<RevealContentHandler<?>>();
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMenu = new Type<RevealContentHandler<?>>();

    @ProxyCodeSplit
    @UseGatekeeper(LoggedInGatekeeper.class)
    @NameToken(NameTokens.welcome)
    public interface MyProxy extends ProxyPlace<MainPresenter> {
    }

    private HeaderPresenter headerPresenter;
    private FooterPresenter footerPresenter;
    private MainMenu menu;

    @Inject
    public MainPresenter(EventBus eventBus, MyView view, MyProxy proxy,
            HeaderPresenter headerPresenter,
            FooterPresenter footerPresenter,
            Provider<MainMenu> menuProvider) {
        super(eventBus, view, proxy);
        this.headerPresenter = headerPresenter;
        this.footerPresenter = footerPresenter;
        this.menu = menuProvider.get();
        getView().getMenuPanel().add(menu);
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
        setInSlot(TYPE_SetHeader, headerPresenter);
        setInSlot(TYPE_SetFooter, footerPresenter);
    }

    @Override
    protected void onBind() {
        super.onBind();
    }
}
