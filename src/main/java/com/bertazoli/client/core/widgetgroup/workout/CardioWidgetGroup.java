package com.bertazoli.client.core.widgetgroup.workout;


import com.bertazoli.client.core.widgetgroup.WidgetGroup;
import com.bertazoli.client.core.workout.WorkoutPresenter.DeleteHandler;
import com.bertazoli.client.manager.SecurityManager;
import com.bertazoli.shared.beans.WorkoutCardio;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class CardioWidgetGroup extends Composite implements WidgetGroup<WorkoutCardio> {
    public interface Binder extends UiBinder<Widget, CardioWidgetGroup> {}
    
    @UiField TextBox name;
    @UiField TextBox hours;
    @UiField TextBox minutes;
    @UiField Image delete;
    private SecurityManager securityManager;
    private DeleteHandler deleteHandler;
    
    @Inject
    public CardioWidgetGroup(Binder binder, SecurityManager securityManager) {
        initWidget(binder.createAndBindUi(this));
        this.securityManager = securityManager;
        
        delete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                deleteHandler.onDelete(CardioWidgetGroup.this);
            }
        });
    }

    @Override
    public WorkoutCardio mapBean() {
        WorkoutCardio cardio = new WorkoutCardio();
        cardio.setName(name.getText());
        cardio.setHours(Integer.parseInt(hours.getText()));
        cardio.setMinutes(Integer.parseInt(minutes.getText()));
        return cardio;
    }

    @Override
    public void setBean(WorkoutCardio bean) {
    }
    
    public void setDeleteHandler(DeleteHandler deleteHandler) {
        this.deleteHandler = deleteHandler;
    }
}
