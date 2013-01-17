package com.bertazoli.client.core.workout;

import java.util.HashSet;
import java.util.Set;

import com.bertazoli.client.core.MainPresenter;
import com.bertazoli.client.core.widgetgroup.workout.CardioWidgetGroup;
import com.bertazoli.client.custom.CustomPresenter;
import com.bertazoli.client.custom.CustomView;
import com.bertazoli.client.gatekeeper.LoggedInGatekeeper;
import com.bertazoli.client.place.NameTokens;
import com.bertazoli.client.rpc.WorkoutCardioServiceAsync;
import com.bertazoli.shared.beans.WorkoutCardio;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class WorkoutPresenter extends CustomPresenter<WorkoutPresenter.MyView, WorkoutPresenter.MyProxy> {

    public interface MyView extends CustomView {
        HasClickHandlers getAddCardioButton();
        HasClickHandlers getAddRegularButton();
        HasClickHandlers getAddDropSetButton();
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
        HTMLPanel getWorkoutsPanel();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.workout)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<WorkoutPresenter> {
    }

    private Provider<CardioWidgetGroup> cardioProvider;
    private Set<CardioWidgetGroup> cardioList = new HashSet<CardioWidgetGroup>(0);
    private Provider<WorkoutCardioServiceAsync> workoutCardioProvider;

    @Inject
    public WorkoutPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            Provider<CardioWidgetGroup> cardioProvider,
            Provider<WorkoutCardioServiceAsync> workoutCardioProvider) {
        super(eventBus, view, proxy);
        this.cardioProvider = cardioProvider;
        this.workoutCardioProvider = workoutCardioProvider;
    }
    
    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        getView().clear();
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
        
        registerHandler(getView().getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doSave();
            }
        }));
        
        registerHandler(getView().getCancelButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                errorPopup.setErrorMessage("Something went wrong, Damn!!!");
            }
        }));
    }

    private void doSave() {
        Set<WorkoutCardio> list = new HashSet<WorkoutCardio>(0);
        for (CardioWidgetGroup item : cardioList) {
            list.add(item.mapBean());
        }
        WorkoutCardioServiceAsync action = workoutCardioProvider.get();
        action.addAll(list, new AsyncCallback<Set<WorkoutCardio>>() {
            @Override
            public void onSuccess(Set<WorkoutCardio> result) {
                PlaceRequest request = new PlaceRequest(NameTokens.welcome);
                placeManager.revealPlace(request);
            }
            
            @Override
            public void onFailure(Throwable caught) {
                errorPopup.setErrorMessage("Something went wrong, Damn!!!");
            }
        });
    }

    private void addCardio() {
        CardioWidgetGroup cardio = cardioProvider.get();
        getView().getWorkoutsPanel().add(cardio);
        cardioList.add(cardio);
    }
}
