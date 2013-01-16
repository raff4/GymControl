package com.bertazoli.client.core.workout;

import com.bertazoli.client.core.MainPresenter;
import com.bertazoli.client.core.widgetgroup.workout.CardioWidgetGroup;
import com.bertazoli.client.gatekeeper.LoggedInGatekeeper;
import com.bertazoli.client.place.NameTokens;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class WorkoutPresenter extends Presenter<WorkoutPresenter.MyView, WorkoutPresenter.MyProxy> {

    public interface MyView extends View {
        HasClickHandlers getAddCardioButton();
        HasClickHandlers getAddRegularButton();
        HasClickHandlers getAddDropSetButton();
        HTMLPanel getWorkoutsPanel();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.workout)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<WorkoutPresenter> {
    }

    private Provider<CardioWidgetGroup> cardioProvider;

    @Inject
    public WorkoutPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            Provider<CardioWidgetGroup> cardioProvider) {
        super(eventBus, view, proxy);
        this.cardioProvider = cardioProvider;
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, MainPresenter.TYPE_SetContent, this);
    }

    @Override
    protected void onBind() {
        super.onBind();
        registerHandler(getView().getAddCardioButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                addCardio();
            }
        }));
    }

    private void addCardio() {
        CardioWidgetGroup cardio = cardioProvider.get();
        getView().getWorkoutsPanel().add(cardio);
    }
}
