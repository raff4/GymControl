package com.bertazoli.client.core.widgetgroup;

public interface WidgetGroup<T> {
    public T mapBean();
    public void setBean(T bean);
    public void validate();
}
