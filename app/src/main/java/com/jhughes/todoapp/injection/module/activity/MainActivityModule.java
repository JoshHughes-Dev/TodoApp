package com.jhughes.todoapp.injection.module.activity;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;

import com.jhughes.todoapp.data.domain.repo.TaskRepository;
import com.jhughes.todoapp.injection.component.activity.MainActivitySubComponent;
import com.jhughes.todoapp.ui.MainActivity;
import com.jhughes.todoapp.ui.viewModel.factory.MainViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = MainActivitySubComponent.class)
public abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivityInjectorFactory(MainActivitySubComponent.Builder builder);


    @Provides
    public static ProgressDialog provideProgressDialog(Context context) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setMessage("loading");
        return progress;
    }

    @Provides
    public static MainViewModelFactory provideMainViewModelFactory(
            Application application, ConnectivityManager connectivityManager, TaskRepository taskRepository) {

        return new MainViewModelFactory(application, connectivityManager, taskRepository);
    }

}