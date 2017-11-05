package com.jhughes.todoapp.injection.component

import android.app.ProgressDialog
import com.jhughes.todoapp.injection.module.MainActivityModule
import com.jhughes.todoapp.injection.scope.PerActivity
import com.jhughes.todoapp.ui.MainActivity
import dagger.Component

@PerActivity
@Component(
        modules = arrayOf(MainActivityModule::class),
        dependencies = arrayOf(ApplicationComponent::class)
)
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    fun progressDialog(): ProgressDialog
}