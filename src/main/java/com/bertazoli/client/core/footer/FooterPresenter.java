package com.bertazoli.client.core.footer;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class FooterPresenter extends PresenterWidget<FooterPresenter.MyView> {

    public interface MyView extends View {
    }

    @Inject
    public FooterPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
    }


    @Override
    protected void onBind() {
        super.onBind();
    }
}
