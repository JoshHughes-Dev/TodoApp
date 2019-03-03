package com.jhughes.todoapp.di;

import android.app.Application;

import com.jhughes.todoapp.di.component.app.BaseTodoApplicationComponent;
import com.jhughes.todoapp.di.component.app.DaggerDebugTodoApplicationComponent;

public class ComponentFactory {

    public static BaseTodoApplicationComponent buildComponentFor(Application application) {
        return DaggerDebugTodoApplicationComponent.builder()
                .application(application)
                .context(application)
                .build();
    }
}
