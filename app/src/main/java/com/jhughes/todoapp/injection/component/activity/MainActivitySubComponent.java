package com.jhughes.todoapp.injection.component.activity;

import com.jhughes.todoapp.injection.module.fragment.AddTaskDialogFragmentModule;
import com.jhughes.todoapp.ui.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {AddTaskDialogFragmentModule.class})
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
}