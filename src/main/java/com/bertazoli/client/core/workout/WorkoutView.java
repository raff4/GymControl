package com.bertazoli.client.core.workout;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class WorkoutView extends ViewImpl implements WorkoutPresenter.MyView {

    private final Widget widget;

    public interface Binder extends UiBinder<Widget, WorkoutView> {
    }
    
    @UiField Button addCardio;
    @UiField Button addRegular;
    @UiField Button addDropSet;
    @UiField HTMLPanel workouts;

    @Inject
    public WorkoutView(final Binder binder) {
        widget = binder.createAndBindUi(this);
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
}
