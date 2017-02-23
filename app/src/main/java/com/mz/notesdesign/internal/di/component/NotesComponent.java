package com.mz.notesdesign.internal.di.component;

import com.mz.notesdesign.internal.di.ActivityScope;
import com.mz.notesdesign.internal.di.module.ActivityModule;
import com.mz.notesdesign.internal.di.module.NotesModule;
import com.mz.notesdesign.notes.NotesFragment;
import dagger.Component;

@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, NotesModule.class
}) public interface NotesComponent {

  void inject(NotesFragment fragment);
}
