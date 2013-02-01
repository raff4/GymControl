package com.bertazoli.client.core.workout;

import java.util.Date;

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
import com.gwtplatform.mvp.client.ViewImpl;

public class WorkoutView extends ViewImpl implements WorkoutPresenter.MyView {

    private final Widget widget;

    public interface Binder extends UiBinder<Widget, WorkoutView> {
    }
    
    @UiField Button addCardio;
    @UiField Button addRegular;
    @UiField Button addDropSet;
    @UiField Button save;
    @UiField Button cancel;
    @UiField HTMLPanel workouts;
    @UiField TextBox workoutName;
    @UiField DateBox workoutDate;

    @Inject
    public WorkoutView(final Binder binder) {
        widget = binder.createAndBindUi(this);
    }

    @Override
    public void clear() {
        workouts.clear();
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
    public HTMLPanel getWorkoutsPanel() {
        return workouts;
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
        return true;
    }
}
