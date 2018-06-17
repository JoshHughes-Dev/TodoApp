package com.jhughes.todoapp.injection.module.activity

import com.jhughes.todoapp.injection.scope.ActivityScope
import com.jhughes.todoapp.injection.scopedItems.BaseActivityItem
import com.jhughes.todoapp.injection.scopedItems.DebugBaseActivityItem
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    @ActivityScope
    fun provideBaseActivityItem() : BaseActivityItem {
        return DebugBaseActivityItem()
    }
}