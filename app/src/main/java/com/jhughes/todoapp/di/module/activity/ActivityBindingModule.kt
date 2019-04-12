package com.jhughes.todoapp.di.module.activity

import androidx.lifecycle.ViewModel
import com.jhughes.todoapp.di.module.app.ViewModelKey
import com.jhughes.todoapp.di.scope.ActivityScope
import com.jhughes.todoapp.di.scope.FragmentScope
import com.jhughes.todoapp.ui.activity.ChooserActivity
import com.jhughes.todoapp.ui.activity.SplashActivity
import com.jhughes.todoapp.ui.activity.tasks.CoroutinesTasksActivity
import com.jhughes.todoapp.ui.activity.tasks.LiveDataTasksActivity
import com.jhughes.todoapp.ui.activity.tasks.PaperDbTasksActivity
import com.jhughes.todoapp.ui.activity.tasks.SimpleTasksActivity
import com.jhughes.todoapp.ui.fragment.addTask.CoroutinesAddTaskDialogFragment
import com.jhughes.todoapp.ui.fragment.addTask.LiveDataAddTaskDialogFragment
import com.jhughes.todoapp.ui.fragment.addTask.PaperDbAddTaskDialogFragment
import com.jhughes.todoapp.ui.fragment.addTask.SimpleAddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.ChooserViewModel
import com.jhughes.todoapp.ui.viewModel.addTask.CoroutinesAddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.addTask.LiveDataAddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.addTask.PaperDbAddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.addTask.SimpleAddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.tasks.CoroutinesTasksViewModel
import com.jhughes.todoapp.ui.viewModel.tasks.LiveDataTasksViewModel
import com.jhughes.todoapp.ui.viewModel.tasks.PaperDbTasksViewModel
import com.jhughes.todoapp.ui.viewModel.tasks.SimpleTasksViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun splashActivity() : SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ChooserActivityModule::class])
    abstract fun chooserActivity() : ChooserActivity

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
    abstract fun CoroutinesTasksActivity() : CoroutinesTasksActivity
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