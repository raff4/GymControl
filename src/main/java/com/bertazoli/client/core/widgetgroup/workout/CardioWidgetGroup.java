package com.bertazoli.client.core.widgetgroup.workout;

import com.bertazoli.client.core.widgetgroup.WidgetGroup;
import com.bertazoli.client.manager.SecurityManager;
import com.bertazoli.shared.beans.WorkoutCardio;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class CardioWidgetGroup extends Composite implements WidgetGroup<WorkoutCardio> {
    public interface Binder extends UiBinder<Widget, CardioWidgetGroup> {}
    
    @UiField TextBox name;
    @UiField TextBox hours;
    @UiField TextBox minutes;
    private SecurityManager securityManager;
    
    @Inject
    public CardioWidgetGroup(Binder binder, SecurityManager securityManager) {
        initWidget(binder.createAndBindUi(this));
        this.securityManager = securityManager;
    }

    @Override
    public WorkoutCardio mapBean() {
        WorkoutCardio cardio = new WorkoutCardio();
        cardio.setName(name.getText());
        cardio.setHours(Integer.parseInt(hours.getText()));
        cardio.setMinutes(Integer.parseInt(minutes.getText()));
        cardio.setUser(securityManager.getUser());
        return cardio;
    }

    @Override
    public void setBean(WorkoutCardio bean) {
    }
}
