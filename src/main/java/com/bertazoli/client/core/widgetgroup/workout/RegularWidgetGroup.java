package com.bertazoli.client.core.widgetgroup.workout;


import com.bertazoli.client.core.widgetgroup.WidgetGroup;
import com.bertazoli.client.core.workout.WorkoutPresenter.DeleteHandler;
import com.bertazoli.client.manager.SecurityManager;
import com.bertazoli.shared.beans.WorkoutRegular;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class RegularWidgetGroup extends Composite implements WidgetGroup<WorkoutRegular> {
    public interface Binder extends UiBinder<Widget, RegularWidgetGroup> {}
    
    @UiField TextBox name;
    @UiField TextBox weight;
    @UiField TextBox sets;
    @UiField TextBox repetitions;
    @UiField Image delete;
    private SecurityManager securityManager;
    private DeleteHandler deleteHandler;
    
    @Inject
    public RegularWidgetGroup(Binder binder, SecurityManager securityManager) {
        initWidget(binder.createAndBindUi(this));
        this.securityManager = securityManager;
        
        delete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                deleteHandler.onDelete(RegularWidgetGroup.this);
            }
        });
    }

    @Override
    public WorkoutRegular mapBean() {
        WorkoutRegular workout = new WorkoutRegular();
        workout.setName(name.getText());
        workout.setWeight(Integer.parseInt(weight.getText()));
        workout.setSets(Integer.parseInt(sets.getText()));
        workout.setRepetitions(Integer.parseInt(repetitions.getText()));
        return workout;
    }

    @Override
    public void setBean(WorkoutRegular bean) {
    }
    
    public void setDeleteHandler(DeleteHandler deleteHandler) {
        this.deleteHandler = deleteHandler;
    }
}
