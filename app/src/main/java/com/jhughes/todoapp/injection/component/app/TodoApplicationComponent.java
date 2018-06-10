package com.jhughes.todoapp.injection.component.app;

import android.app.Application;
import android.net.ConnectivityManager;

import com.jhughes.todoapp.TodoApplication;
import com.jhughes.todoapp.injection.module.activity.MainActivityModule;
import com.jhughes.todoapp.injection.module.activity.SplashActivityModule;
import com.jhughes.todoapp.injection.module.app.TodoApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


/* extends android injector means dont need explicit void inject(TodoApplication application) method*/

@Singleton
@Component(modules = {
        TodoApplicationModule.class,
        SplashActivityModule.class,
        MainActivityModule.class,
        AndroidSupportInjectionModule.class
})
public interface TodoApplicationComponent extends AndroidInjector<TodoApplication> {

    ConnectivityManager conectivityManager();

    @Component.Builder
    interface Builder {
        @BindsInstance
        TodoApplicationComponent.Builder application(Application application);

        TodoApplicationComponent build();
    }
}
