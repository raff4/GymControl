package com.bertazoli.client.core.workout;

import java.util.Date;
import java.util.HashSet;
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
import com.google.gwt.user.client.ui.HTMLPanel;
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
        public void onDelete(Widget widget);
    }

    private Provider<CardioWidgetGroup> cardioProvider;
    private Provider<RegularWidgetGroup> regularProvider;
    private Provider<DropSetWidgetGroup> dropsetProvider;
    private Set<CardioWidgetGroup> cardioList = new HashSet<CardioWidgetGroup>(0);
    private Set<RegularWidgetGroup> regularList = new HashSet<RegularWidgetGroup>(0);
    private Set<DropSetWidgetGroup> dropsetList = new HashSet<DropSetWidgetGroup>(0);
    private Provider<WorkoutServiceAsync> workoutProvider;
    private DeleteHandler deleteHandler;

    @Inject
    public WorkoutPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            Provider<CardioWidgetGroup> cardioProvider,
            Provider<RegularWidgetGroup> regularProvider,
            Provider<DropSetWidgetGroup> dropsetProvider,
            Provider<WorkoutServiceAsync> workoutProvider) {
        super(eventBus, view, proxy);
        this.cardioProvider = cardioProvider;
        this.regularProvider = regularProvider;
        this.workoutProvider = workoutProvider;
        this.dropsetProvider = dropsetProvider;
        
        this.deleteHandler = new DeleteHandler() {
            
            @Override
            public void onDelete(Widget widget) {
                if (widget instanceof CardioWidgetGroup) {
                    cardioList.remove(widget);
                } else if (widget instanceof RegularWidgetGroup) {
                    regularList.remove(widget);
                } else if (widget instanceof DropSetWidgetGroup) {
                    dropsetList.remove(widget);
                }
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
//                redirectToHome();
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
            
            for (RegularWidgetGroup item : regularList) {
                workout.getRegulars().add(item.mapBean());
            }
            
            for (DropSetWidgetGroup item : dropsetList) {
                workout.getDropsets().add(item.mapBean());
            }
            
            WorkoutServiceAsync action = workoutProvider.get();
            action.add(workout, new AsyncCallback<Workout>() {
                @Override
                public void onSuccess(Workout result) {
                    redirectToHome();
                }
                
                @Override
                public void onFailure(Throwable caught) {
                    errorPopup.setErrorMessage("Something went wrong, Damn!!!");
                }
            });
        }
    }
    
    private void redirectToHome() {
        PlaceRequest request = new PlaceRequest(NameTokens.welcome);
        placeManager.revealPlace(request);
    }

    private void addCardio() {
        CardioWidgetGroup cardio = cardioProvider.get();
        getView().getWorkoutsPanel().add(cardio);
        cardio.setDeleteHandler(deleteHandler);
        cardioList.add(cardio);
    }

    protected void addRegular() {
        RegularWidgetGroup regular = regularProvider.get();
        getView().getWorkoutsPanel().add(regular);
        regular.setDeleteHandler(deleteHandler);
        regularList.add(regular);
    }

    protected void addDropSet() {
        DropSetWidgetGroup dropset = dropsetProvider.get();
        getView().getWorkoutsPanel().add(dropset);
        dropset.setDeleteHandler(deleteHandler);
        dropsetList.add(dropset);
    }
}
