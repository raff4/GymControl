package com.bertazoli.client.core.workout;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class WorkoutView extends ViewImpl implements WorkoutPresenter.MyView {

    private final Widget widget;

    public interface Binder extends UiBinder<Widget, WorkoutView> {
    }

    @Inject
    public WorkoutView(final Binder binder) {
        widget = binder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }
}
