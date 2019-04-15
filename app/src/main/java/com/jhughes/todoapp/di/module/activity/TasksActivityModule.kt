package com.jhughes.todoapp.di.module.activity

import androidx.lifecycle.ViewModel
import com.jhughes.todoapp.di.module.app.ViewModelKey
import com.jhughes.todoapp.di.scope.ActivityScope
import com.jhughes.todoapp.di.scope.FragmentScope
import com.jhughes.todoapp.ui.activity.tasks.*
import com.jhughes.todoapp.ui.fragment.addTask.*
import com.jhughes.todoapp.ui.viewModel.ChooserViewModel
import com.jhughes.todoapp.ui.viewModel.addTask.*
import com.jhughes.todoapp.ui.viewModel.tasks.*
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap



@Module
abstract class TasksBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SimpleTaskActivityModule::class])
    abstract fun simpleTasksActivity() : SimpleTasksActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [LiveDataTasksActivityModule::class])
    abstract fun liveDataTasksActivity() : LiveDataTasksActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PaperDbTasksActivityModule::class])
    abstract fun paperDbTasksActivity() : PaperDbTasksActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [CoroutinesTasksActivityModule::class])
    abstract fun coroutinesTasksActivity() : CoroutinesTasksActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SuperActivityModule::class])
    abstract fun superTasksActivity() : SuperTasksActivity
}

@Module(includes = [ActivityModule::class])
abstract class ChooserActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(ChooserViewModel::class)
    abstract fun bindChooserViewModel(viewModel: ChooserViewModel) : ViewModel
}

@Module(includes = [ActivityModule::class])
abstract class SimpleTaskActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(SimpleTasksViewModel::class)
    abstract fun bindSimpleTasksViewModel(viewModel: SimpleTasksViewModel) : ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [SimpleAddTaskDialogFragmentModule::class])
    abstract fun addSimpleAddTaskFragment(): SimpleAddTaskDialogFragment
}

@Module
abstract class SimpleAddTaskDialogFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SimpleAddTaskViewModel::class)
    abstract fun bindSimpleAddTaskViewModel(viewModel: SimpleAddTaskViewModel) : ViewModel
}

@Module(includes = [ActivityModule::class])
abstract class LiveDataTasksActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(LiveDataTasksViewModel::class)
    abstract fun bindLiveDataTasksViewModel(viewModel: LiveDataTasksViewModel) : ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [LiveDataAddTaskDialogFragmentModule::class])
    abstract fun addTaskFragment(): LiveDataAddTaskDialogFragment
}

@Module
abstract class LiveDataAddTaskDialogFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(LiveDataAddTaskViewModel::class)
    abstract fun bindLiveDataAddTaskViewModel(viewModel: LiveDataAddTaskViewModel) : ViewModel
}

@Module(includes = [ActivityModule::class])
abstract class PaperDbTasksActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(PaperDbTasksViewModel::class)
    abstract fun bindPaperDbTasksViewModel(viewModel: PaperDbTasksViewModel) : ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [PaperDbAddTaskDialogFragmentModule::class])
    abstract fun addTaskFragment(): PaperDbAddTaskDialogFragment
}

@Module
abstract class PaperDbAddTaskDialogFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(PaperDbAddTaskViewModel::class)
    abstract fun bindPaperDbAddTaskViewModel(viewModel: PaperDbAddTaskViewModel) : ViewModel
}

@Module(includes = [ActivityModule::class])
abstract class CoroutinesTasksActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoroutinesTasksViewModel::class)
    abstract fun bindCoroutinesTasksViewModel(viewModel: CoroutinesTasksViewModel) : ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [CoroutinesAddTaskDialogFragmentModule::class])
    abstract fun addTaskFragment(): CoroutinesAddTaskDialogFragment
}

@Module
abstract class CoroutinesAddTaskDialogFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoroutinesAddTaskViewModel::class)
    abstract fun bindCoroutinesAddTaskViewModel(viewModel: CoroutinesAddTaskViewModel) : ViewModel
}

@Module(includes = [ActivityModule::class])
abstract class SuperActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(SuperTasksViewModel::class)
    abstract fun bindSuperTasksViewModel(viewModel: SuperTasksViewModel) : ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [SuperAddTaskDialogFragmentModule::class])
    abstract fun addTaskFragment(): SuperAddTaskDialogFragment
}

@Module
abstract class SuperAddTaskDialogFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SuperAddTaskViewModel::class)
    abstract fun bindSuperAddTaskViewModel(viewModel: SuperAddTaskViewModel) : ViewModel
}