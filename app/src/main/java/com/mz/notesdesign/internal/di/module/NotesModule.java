package com.mz.notesdesign.internal.di.module;

import com.mz.notesdesign.data.repository.NoteRepository;
import com.mz.notesdesign.notes.NotesContract;
import com.mz.notesdesign.notes.NotesPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class NotesModule {

  @Provides
  NotesContract.ActionsListener provideActionsListener(NoteRepository noteRepository){
    return new NotesPresenter(noteRepository);
  }
}
