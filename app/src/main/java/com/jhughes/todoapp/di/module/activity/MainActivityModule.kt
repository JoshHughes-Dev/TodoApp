package com.jhughes.todoapp.di.module.activity

import androidx.lifecycle.ViewModel
import com.jhughes.todoapp.di.module.app.ViewModelKey
import com.jhughes.todoapp.di.module.fragment.AddTaskModule
import com.jhughes.todoapp.di.scope.FragmentScope
import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

//module has to be abstract to contribute injector. but means providers have to be static (in java)
// which converts to some nasty kotlin code

@Module(includes = [ActivityModule::class])
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [AddTaskModule::class])
    abstract fun addTaskFragment(): AddTaskDialogFragment
}
