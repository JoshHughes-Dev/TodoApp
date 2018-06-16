package com.jhughes.todoapp.injection.module.activity

import com.jhughes.todoapp.injection.scope.ActivityScope
import com.jhughes.todoapp.injection.scopedItems.BaseActivityItem
import dagger.Module
import dagger.Provides

@Module
class BaseActivityModule {
    @Provides
    @ActivityScope
    fun provideBaseActivityItem() : BaseActivityItem {
        return BaseActivityItem()
    }
}