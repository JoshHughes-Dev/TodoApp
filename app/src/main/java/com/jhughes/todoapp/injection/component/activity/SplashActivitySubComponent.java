package com.jhughes.todoapp.injection.component.activity;

import com.jhughes.todoapp.ui.SplashActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface SplashActivitySubComponent extends AndroidInjector<SplashActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SplashActivity> {}
}
