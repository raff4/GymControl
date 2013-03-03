package com.bertazoli.client.core.widgetgroup.workout;


import com.bertazoli.client.core.fields.NumberField;
import com.bertazoli.client.core.fields.TextField;
import com.bertazoli.client.core.validation.ValidationManager;
import com.bertazoli.client.core.widgetgroup.WidgetGroup;
import com.bertazoli.client.core.workout.add.WorkoutAddPresenter.DeleteHandler;
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
import com.google.inject.Provider;

public class CardioWidgetGroup extends Composite implements WidgetGroup<WorkoutCardio> {
    public interface Binder extends UiBinder<Widget, CardioWidgetGroup> {}
    
    @UiField TextBox name;
    private TextField nameField;
    @UiField TextBox hours;
    private NumberField hoursField;
    @UiField TextBox minutes;
    private NumberField minutesField;
    @UiField Image delete;
    private SecurityManager securityManager;
    private DeleteHandler deleteHandler;
    
    private ValidationManager validationManager;
    
    @Inject
    public CardioWidgetGroup(Binder binder, SecurityManager securityManager,
            Provider<TextField> textFieldProvider,
            Provider<NumberField> numberFieldProvider,
            ValidationManager validationManager) {
        initWidget(binder.createAndBindUi(this));
        this.securityManager = securityManager;
        this.validationManager = validationManager;
        
        delete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                deleteHandler.onDelete(CardioWidgetGroup.this);
            }
        });
        
        nameField = textFieldProvider.get();
        nameField.setField(name, false);
        
        hoursField = numberFieldProvider.get();
        hoursField.setField(hours, false);
        
        minutesField = numberFieldProvider.get();
        minutesField.setField(minutes, false);
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

    @Override
    public void validate() {
        validationManager.addValidationResult(nameField.validate());
        validationManager.addValidationResult(hoursField.validate());
        validationManager.addValidationResult(minutesField.validate());
    }
}
