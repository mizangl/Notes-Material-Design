package com.mz.notesdesign.internal.di.component;

import android.content.Context;
import com.mz.notesdesign.internal.di.ActivityScope;
import com.mz.notesdesign.internal.di.module.ActivityModule;
import dagger.Component;

@ActivityScope @Component(dependencies = ApplicationComponent.class,
    modules = ActivityModule.class) public interface ActivityComponent {

  Context context();
}
