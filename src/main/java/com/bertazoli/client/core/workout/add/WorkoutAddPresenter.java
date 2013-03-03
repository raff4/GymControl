package com.bertazoli.client.core.workout.add;

import java.util.Date;
import java.util.Set;

import com.bertazoli.client.core.MainPresenter;
import com.bertazoli.client.core.widgetgroup.workout.CardioWidgetGroup;
import com.bertazoli.client.core.widgetgroup.workout.DropSetWidgetGroup;
import com.bertazoli.client.core.widgetgroup.workout.RegularWidgetGroup;
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
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class WorkoutAddPresenter extends CustomPresenter<WorkoutAddPresenter.MyView, WorkoutAddPresenter.MyProxy> {

    public interface MyView extends CustomView {
        HasClickHandlers getAddCardioButton();
        HasClickHandlers getAddRegularButton();
        HasClickHandlers getAddDropSetButton();
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
        HasText getWorkoutName();
        Date getWorkoutDate();
        void removeItem(Widget widget);
        void setDeleteHandler(DeleteHandler deleteHandler);
        Set<CardioWidgetGroup> getCardioWorkouts();
        Set<RegularWidgetGroup> getRegularWorkouts();
        Set<DropSetWidgetGroup> getDropSetWorkouts();
        void addCardio();
        void addRegular();
        void addDropSet();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.addworkout)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<WorkoutAddPresenter> {
    }
    
    public interface DeleteHandler {
        public void onDelete(Widget widget);
    }

    private Provider<WorkoutServiceAsync> workoutProvider;
    private DeleteHandler deleteHandler;

    @Inject
    public WorkoutAddPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            Provider<WorkoutServiceAsync> workoutProvider) {
        super(eventBus, view, proxy);
        this.workoutProvider = workoutProvider;
        
        this.deleteHandler = new DeleteHandler() {
            
            @Override
            public void onDelete(Widget widget) {
                getView().removeItem(widget);
            }
        };
        getView().setDeleteHandler(this.deleteHandler);
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
        
        registerHandler(getView().getAddDropSetButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                addDropSet();
            }
        }));
        
        registerHandler(getView().getAddRegularButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                addRegular();
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
                redirectToWorkouts();
            }
        }));
    }

    private void doSave() {
        if (getView().validate()) {
            Workout workout = new Workout();
            workout.setDay(getView().getWorkoutDate());
            workout.setName(getView().getWorkoutName().getText());
            workout.setUserId(securityManager.getUser().getId());
            
            for (CardioWidgetGroup item : getView().getCardioWorkouts()) {
                workout.getCardios().add(item.mapBean());
            }
            
            for (RegularWidgetGroup item : getView().getRegularWorkouts()) {
                workout.getRegulars().add(item.mapBean());
            }
            
            for (DropSetWidgetGroup item : getView().getDropSetWorkouts()) {
                workout.getDropsets().add(item.mapBean());
            }
            
            WorkoutServiceAsync action = workoutProvider.get();
            action.add(workout, new AsyncCallback<Workout>() {
                @Override
                public void onSuccess(Workout result) {
                    redirectToWorkouts();
                }
                
                @Override
                public void onFailure(Throwable caught) {
                    errorPopup.setErrorMessage("Something went wrong, Damn!!!");
                }
            });
        }
    }
    
    private void redirectToWorkouts() {
        PlaceRequest request = new PlaceRequest(NameTokens.workout);
        placeManager.revealPlace(request);
    }

    private void addCardio() {
        getView().addCardio();
    }

    protected void addRegular() {
        getView().addRegular();
    }

    protected void addDropSet() {
        getView().addDropSet();
    }
}
