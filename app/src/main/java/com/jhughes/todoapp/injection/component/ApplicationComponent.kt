package com.jhughes.todoapp.injection.component

import android.net.ConnectivityManager
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
public interface ApplicationComponent {

    fun inject(app : TodoApplication)

    fun connectivityManager(): ConnectivityManager
}

//AndroidInjectionModule::class,

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): ApplicationComponent
//    }