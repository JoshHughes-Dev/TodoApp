package com.jhughes.todoapp.injection.component.fragment;

import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface AddTaskDialogFragmentSubComponent extends AndroidInjector<AddTaskDialogFragment> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<AddTaskDialogFragment> {}
}
