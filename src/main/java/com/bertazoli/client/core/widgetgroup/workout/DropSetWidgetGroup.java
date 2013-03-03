package com.bertazoli.client.core.widgetgroup.workout;


import java.util.LinkedHashSet;
import java.util.Set;

import com.bertazoli.client.core.widgetgroup.WidgetGroup;
import com.bertazoli.client.core.workout.add.WorkoutAddPresenter.DeleteHandler;
import com.bertazoli.client.manager.SecurityManager;
import com.bertazoli.shared.beans.WorkoutDropSet;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DropSetWidgetGroup extends Composite implements WidgetGroup<WorkoutDropSet> {
    public interface Binder extends UiBinder<Widget, DropSetWidgetGroup> {}
    
    @UiField TextBox name;
    @UiField Image delete;
    @UiField Image add;
    @UiField HTMLPanel setsPanel;
    private SecurityManager securityManager;
    private DeleteHandler deleteHandler;
    private DeleteHandler dhDropSetSetWidget;
    private Set<DropSetSetWidgetGroup> dropsets = new LinkedHashSet<DropSetSetWidgetGroup>(0);
    private Provider<DropSetSetWidgetGroup> dropsetProvider;
    
    @Inject
    public DropSetWidgetGroup(Binder binder,
            SecurityManager securityManager,
            Provider<DropSetSetWidgetGroup> dsProvider) {
        initWidget(binder.createAndBindUi(this));
        this.securityManager = securityManager;
        this.dropsetProvider = dsProvider;
        
        delete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                deleteHandler.onDelete(DropSetWidgetGroup.this);
            }
        });
        
        dhDropSetSetWidget = new DeleteHandler() {
            @Override
            public void onDelete(Widget widget) {
                if (dropsets.size() > 1) {
                    dropsets.remove(widget);
                    setsPanel.remove(widget);
                }
            }
        };
        
        add.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                DropSetSetWidgetGroup ds = dropsetProvider.get();
                ds.setDeleteHandler(dhDropSetSetWidget);
                dropsets.add(ds);
                setsPanel.add(ds);
            }
        });
        
        DropSetSetWidgetGroup ds = dropsetProvider.get();
        ds.setDeleteHandler(dhDropSetSetWidget);
        dropsets.add(ds);
        setsPanel.add(ds);
    }

    @Override
    public WorkoutDropSet mapBean() {
        WorkoutDropSet workout = new WorkoutDropSet();
        workout.setName(name.getText());
        for (DropSetSetWidgetGroup dss : dropsets) {
            workout.getDropSetSet().add(dss.mapBean());
        }
        return workout;
    }

    @Override
    public void setBean(WorkoutDropSet bean) {
    }
    
    public void setDeleteHandler(DeleteHandler deleteHandler) {
        this.deleteHandler = deleteHandler;
    }

    @Override
    public void validate() {
    }
}
