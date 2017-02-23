package com.mz.notesdesign.internal.di.component;

import com.mz.notesdesign.data.api.ApiService;
import com.mz.notesdesign.data.cache.NoteCache;
import com.mz.notesdesign.data.repository.NoteRepository;
import com.mz.notesdesign.internal.di.module.ApplicationModule;
import com.mz.notesdesign.utils.BaseActivity;
import com.mz.notesdesign.utils.BaseFragment;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { ApplicationModule.class }) public interface ApplicationComponent {

  void inject(BaseActivity activity);

  void inject(BaseFragment fragment);

  NoteRepository noteRepository();

  NoteCache noteCache();

  ApiService apiService();
}
