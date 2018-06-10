package com.jhughes.todoapp.injection.module.fragment;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.jhughes.todoapp.data.domain.repo.TaskRepository;
import com.jhughes.todoapp.injection.component.fragment.AddTaskDialogFragmentSubComponent;
import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment;
import com.jhughes.todoapp.ui.viewModel.factory.AddTaskViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = AddTaskDialogFragmentSubComponent.class)
public abstract class AddTaskDialogFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(AddTaskDialogFragment.class)
    public abstract AndroidInjector.Factory<? extends Fragment>
    bindYourFragmentInjectorFactory(AddTaskDialogFragmentSubComponent.Builder builder);


    @Provides
    public static AddTaskViewModelFactory provideAddTaskViewModelFactory(
            Application application, TaskRepository taskRepository) {
        return new AddTaskViewModelFactory(application, taskRepository);
    }
}
