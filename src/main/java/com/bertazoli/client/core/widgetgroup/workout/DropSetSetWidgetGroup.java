package com.bertazoli.client.core.widgetgroup.workout;


import com.bertazoli.client.core.widgetgroup.WidgetGroup;
import com.bertazoli.client.core.workout.WorkoutPresenter.DeleteHandler;
import com.bertazoli.client.manager.SecurityManager;
import com.bertazoli.shared.beans.WorkoutDropSetSet;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class DropSetSetWidgetGroup extends Composite implements WidgetGroup<WorkoutDropSetSet> {
    public interface Binder extends UiBinder<Widget, DropSetSetWidgetGroup> {}
    
    @UiField TextBox weight;
    @UiField TextBox sets;
    @UiField TextBox repetitions;
    @UiField Image delete;
    private SecurityManager securityManager;
    private DeleteHandler deleteHandler;
    
    @Inject
    public DropSetSetWidgetGroup(Binder binder, SecurityManager securityManager) {
        initWidget(binder.createAndBindUi(this));
        this.securityManager = securityManager;
        
        delete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                deleteHandler.onDelete(DropSetSetWidgetGroup.this);
            }
        });
    }

    @Override
    public WorkoutDropSetSet mapBean() {
        WorkoutDropSetSet dss = new WorkoutDropSetSet();
        dss.setWeight(Integer.parseInt(weight.getText()));
        dss.setSets(Integer.parseInt(sets.getText()));
        dss.setRepetitions(Integer.parseInt(repetitions.getText()));
        return dss;
    }

    @Override
    public void setBean(WorkoutDropSetSet bean) {
    }
    
    public void setDeleteHandler(DeleteHandler deleteHandler) {
        this.deleteHandler = deleteHandler;
    }
}
