package com.jhughes.todoapp.injection;

import android.app.Application;

import com.jhughes.todoapp.injection.component.app.BaseTodoApplicationComponent;
import com.jhughes.todoapp.injection.component.app.DaggerDebugTodoApplicationComponent;

public class ComponentFactory {

    public static BaseTodoApplicationComponent buildComponentFor(Application application) {
        return DaggerDebugTodoApplicationComponent
                .builder()
                .application(application)
                .build();
    }
}
