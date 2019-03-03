package com.jhughes.todoapp.di;

import android.app.Application;

import com.jhughes.todoapp.di.component.app.BaseTodoApplicationComponent;
import com.jhughes.todoapp.di.component.app.DaggerTodoApplicationComponent;

public class ComponentFactory {

    public static BaseTodoApplicationComponent buildComponentFor(Application application) {
        return DaggerTodoApplicationComponent.builder()
                .application(application)
                .context(application)
                .build();
    }
}
