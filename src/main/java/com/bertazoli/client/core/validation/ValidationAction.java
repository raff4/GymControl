package com.bertazoli.client.core.validation;

import com.google.gwt.user.client.ui.Widget;

public interface ValidationAction {
    public void execute(Widget widget);
    public void clean(Widget widget);
}
