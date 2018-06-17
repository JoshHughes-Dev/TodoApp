package com.jhughes.todoapp.injection.module.activity

import android.app.Application
import android.net.ConnectivityManager
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.injection.module.fragment.AddTaskModule
import com.jhughes.todoapp.injection.scope.ActivityScope
import com.jhughes.todoapp.injection.scope.FragmentScope
import com.jhughes.todoapp.injection.scopedItems.ActivityItem
import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.factory.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

//module has to be abstract to contribute injector. but means providers have to be static (in java)
// which converts to some nasty kotlin code

@Module(includes = [ActivityModule::class])
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [AddTaskModule::class])
    abstract fun addTaskFragment(): AddTaskDialogFragment

    @Module
    companion object {
        @Provides
        @ActivityScope
        @JvmStatic
        fun provideMainViewModelFactory(application: Application,
                                        connectivityManager: ConnectivityManager,
                                        taskRepository: TaskRepository): MainViewModelFactory {
            return MainViewModelFactory(application, connectivityManager, taskRepository)
        }

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideFragmentItem() : ActivityItem {
            return ActivityItem()
        }
    }

}
