package com.bertazoli.client.core.workout;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bertazoli.client.core.MainPresenter;
import com.bertazoli.client.core.widgetgroup.workout.CardioWidgetGroup;
import com.bertazoli.client.custom.CustomPresenter;
import com.bertazoli.client.custom.CustomView;
import com.bertazoli.client.gatekeeper.LoggedInGatekeeper;
import com.bertazoli.client.place.NameTokens;
import com.bertazoli.client.rpc.WorkoutServiceAsync;
import com.bertazoli.shared.beans.Workout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
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
        HasText getWorkoutName();
        Date getWorkoutDate();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.workout)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<WorkoutPresenter> {
    }
    
    public interface DeleteHandler {
        public void onDelete(CardioWidgetGroup widget);
    }

    private Provider<CardioWidgetGroup> cardioProvider;
    private Set<CardioWidgetGroup> cardioList = new HashSet<CardioWidgetGroup>(0);
    private Provider<WorkoutServiceAsync> workoutProvider;
    private DeleteHandler deleteHandler;

    @Inject
    public WorkoutPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            Provider<CardioWidgetGroup> cardioProvider,
            Provider<WorkoutServiceAsync> workoutProvider) {
        super(eventBus, view, proxy);
        this.cardioProvider = cardioProvider;
        this.workoutProvider = workoutProvider;
        
        this.deleteHandler = new DeleteHandler() {
            
            @Override
            public void onDelete(CardioWidgetGroup widget) {
                cardioList.remove(widget);
                getView().getWorkoutsPanel().remove(widget);
            }
        };
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
        if (getView().validate()) {
            Workout workout = new Workout();
            workout.setDay(getView().getWorkoutDate());
            workout.setName(getView().getWorkoutName().getText());
            workout.setUserId(securityManager.getUser().getId());
            
            for (CardioWidgetGroup item : cardioList) {
                workout.getCardios().add(item.mapBean());
            }
            
            WorkoutServiceAsync action = workoutProvider.get();
            action.add(workout, new AsyncCallback<Workout>() {
                @Override
                public void onSuccess(Workout result) {
                    PlaceRequest request = new PlaceRequest(NameTokens.welcome);
                    placeManager.revealPlace(request);
                }
                
                @Override
                public void onFailure(Throwable caught) {
                    errorPopup.setErrorMessage("Something went wrong, Damn!!!");
                }
            });
        }
    }

    private void addCardio() {
        CardioWidgetGroup cardio = cardioProvider.get();
        getView().getWorkoutsPanel().add(cardio);
        cardio.setDeleteHandler(deleteHandler);
        cardioList.add(cardio);
    }
}
