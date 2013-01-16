package com.bertazoli.client.core.widgetgroup.workout;

import com.bertazoli.client.core.widgetgroup.WidgetGroup;
import com.bertazoli.shared.beans.WorkoutCardio;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class CardioWidgetGroup extends Composite implements WidgetGroup<WorkoutCardio> {
    public interface Binder extends UiBinder<Widget, CardioWidgetGroup> {}
    
    @Inject
    public CardioWidgetGroup(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void mapBean() {
    }

    @Override
    public void setBean() {
    }
}
