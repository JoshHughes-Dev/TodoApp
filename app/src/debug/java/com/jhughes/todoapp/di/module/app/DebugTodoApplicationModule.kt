package com.jhughes.todoapp.di.module.app

import dagger.Module

@Module(includes = [
    SystemServiceModule::class,
    DataBaseModule::class,
    RepositoryModule::class
])
class DebugTodoApplicationModule