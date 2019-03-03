package com.jhughes.todoapp.di.module.app

import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [
    AndroidSupportInjectionModule::class,
    SystemServiceModule::class,
    DataBaseModule::class,
    RepositoryModule::class
])
class TodoApplicationModule
