package com.bertazoli.client.core.workout.add;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bertazoli.client.core.constants.Dictionary;
import com.bertazoli.client.core.fields.DateField;
import com.bertazoli.client.core.validation.ValidationManager;
import com.bertazoli.client.core.widgetgroup.workout.CardioWidgetGroup;
import com.bertazoli.client.core.widgetgroup.workout.DropSetWidgetGroup;
import com.bertazoli.client.core.widgetgroup.workout.RegularWidgetGroup;
import com.bertazoli.client.core.workout.add.WorkoutAddPresenter.DeleteHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.ViewImpl;

public class WorkoutAddView extends ViewImpl implements WorkoutAddPresenter.MyView {

    private final Widget widget;

    public interface Binder extends UiBinder<Widget, WorkoutAddView> {
    }
    
    @UiField Button addCardio;
    @UiField Button addRegular;
    @UiField Button addDropSet;
    @UiField Button save;
    @UiField Button cancel;
    @UiField HTMLPanel workouts;
    @UiField TextBox workoutName;
    @UiField DateBox workoutDate;
    private DateField workoutDateField;
    private ValidationManager validationManager;
    
    private Provider<CardioWidgetGroup> cardioProvider;
    private Provider<RegularWidgetGroup> regularProvider;
    private Provider<DropSetWidgetGroup> dropsetProvider;
    private Set<CardioWidgetGroup> cardioList = new HashSet<CardioWidgetGroup>(0);
    private Set<RegularWidgetGroup> regularList = new HashSet<RegularWidgetGroup>(0);
    private Set<DropSetWidgetGroup> dropsetList = new HashSet<DropSetWidgetGroup>(0);
    private DeleteHandler deleteHandler;

    @Inject
    public WorkoutAddView(final Binder binder,
            ValidationManager validationManager,
            Provider<DateField> dateFieldProvider,
            Provider<CardioWidgetGroup> cardioProvider,
            Provider<RegularWidgetGroup> regularProvider,
            Provider<DropSetWidgetGroup> dropsetProvider,
            Dictionary dictionary) {
        widget = binder.createAndBindUi(this);
        this.validationManager = validationManager;
        this.cardioProvider = cardioProvider;
        this.regularProvider = regularProvider;
        this.dropsetProvider = dropsetProvider;
        
        workoutDateField = dateFieldProvider.get();
        workoutDateField.setField(workoutDate, false);
    }

    @Override
    public void clear() {
        workouts.clear();
        cardioList.clear();
        regularList.clear();
        dropsetList.clear();
    }
    
    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasClickHandlers getAddCardioButton() {
        return addCardio;
    }

    @Override
    public HasClickHandlers getAddRegularButton() {
        return addRegular;
    }

    @Override
    public HasClickHandlers getAddDropSetButton() {
        return addDropSet;
    }

    @Override
    public HasClickHandlers getSaveButton() {
        return save;
    }

    @Override
    public HasClickHandlers getCancelButton() {
        return cancel;
    }

    @Override
    public HasText getWorkoutName() {
        return workoutName;
    }

    @Override
    public Date getWorkoutDate() {
        return workoutDate.getValue();
    }

    @Override
    public boolean validate() {
        validationManager.reset();
        
        validationManager.addValidationResult(workoutDateField.validate());
        
        for (CardioWidgetGroup item : cardioList) {
            item.validate();
        }
        for (RegularWidgetGroup item : regularList) {
            item.validate();
        }
        for (DropSetWidgetGroup item : dropsetList) {
            item.validate();
        }
        
        validationManager.displayErrors();
        
        return !validationManager.hasErrors();
    }

    @Override
    public void removeItem(Widget widget) {
        if (widget instanceof CardioWidgetGroup) {
            cardioList.remove(widget);
        } else if (widget instanceof RegularWidgetGroup) {
            regularList.remove(widget);
        } else if (widget instanceof DropSetWidgetGroup) {
            dropsetList.remove(widget);
        }
        workouts.remove(widget);
    }

    @Override
    public void setDeleteHandler(DeleteHandler deleteHandler) {
        this.deleteHandler = deleteHandler;
    }

    @Override
    public Set<CardioWidgetGroup> getCardioWorkouts() {
        return cardioList;
    }

    @Override
    public Set<RegularWidgetGroup> getRegularWorkouts() {
        return regularList;
    }

    @Override
    public Set<DropSetWidgetGroup> getDropSetWorkouts() {
        return dropsetList;
    }

    @Override
    public void addCardio() {
        CardioWidgetGroup cardio = cardioProvider.get();
        workouts.add(cardio);
        cardio.setDeleteHandler(deleteHandler);
        cardioList.add(cardio);
    }

    @Override
    public void addRegular() {
        RegularWidgetGroup regular = regularProvider.get();
        workouts.add(regular);
        regular.setDeleteHandler(deleteHandler);
        regularList.add(regular);
    }

    @Override
    public void addDropSet() {
        DropSetWidgetGroup dropset = dropsetProvider.get();
        workouts.add(dropset);
        dropset.setDeleteHandler(deleteHandler);
        dropsetList.add(dropset);
    }
}
