package com.mz.notesdesign.internal.di.module;

import com.mz.notesdesign.data.api.ApiService;
import com.mz.notesdesign.data.api.ApiServiceImp;
import com.mz.notesdesign.data.cache.NoteCache;
import com.mz.notesdesign.data.cache.NoteCacheImp;
import com.mz.notesdesign.data.repository.NoteDataRepository;
import com.mz.notesdesign.data.repository.NoteRepository;
import com.mz.notesdesign.internal.NotesApplication;
import dagger.Module;
import dagger.Provides;

@Module public class ApplicationModule {

  private final NotesApplication application;

  public ApplicationModule(NotesApplication application) {
    this.application = application;
  }

  @Provides NoteRepository provideNoteRepository(NoteDataRepository noteDataRepository) {
    return noteDataRepository;
  }

  @Provides NoteCache provideNoteCache(NoteCacheImp noteCacheImp) {
    return noteCacheImp;
  }

  @Provides ApiService provideApiService(ApiServiceImp apiServiceImp) {
    return apiServiceImp;
  }
}
