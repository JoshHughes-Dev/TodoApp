package com.jhughes.todoapp.injection.module.activity;

import android.app.Activity;
import android.app.Application;

import com.jhughes.todoapp.data.util.AppExecutors;
import com.jhughes.todoapp.injection.component.activity.SplashActivitySubComponent;
import com.jhughes.todoapp.ui.SplashActivity;
import com.jhughes.todoapp.ui.viewModel.factory.SplashViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = SplashActivitySubComponent.class)
public abstract class SplashActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(SplashActivity.class)
    public abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivityInjectorFactory(SplashActivitySubComponent.Builder builder);

    @Provides
    public static SplashViewModelFactory provideSplashViewModelFactory(Application application, AppExecutors appExecutors) {
        return new SplashViewModelFactory(application, appExecutors);
    }
}
