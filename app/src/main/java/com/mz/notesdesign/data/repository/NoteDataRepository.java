package com.mz.notesdesign.data.repository;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.source.NoteSourceFactory;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class NoteDataRepository implements NoteRepository {

  private final NoteSourceFactory noteSourceFactory;

  @Inject public NoteDataRepository(NoteSourceFactory noteSourceFactory) {
    this.noteSourceFactory = noteSourceFactory;
  }

  @Override public void getNotes(boolean forceUpdate, @NonNull NoteRepository.Callback callback) {
    noteSourceFactory.createSource(forceUpdate).getNotes(callback);
  }

  @Override public void addNote(@NonNull Note note) {
    noteSourceFactory.createCloudSource().addNote(note);
  }

  @Override public void removeNote(@NonNull String id) {
    noteSourceFactory.createCloudSource().removeNote(id);
  }

  @Override public void refreshData() {
    noteSourceFactory.createCloudSource().refreshData();
  }

  @Override public void getNote(@NonNull String id, boolean forceUpdate,
      @NonNull NoteRepository.Callback callback) {

    noteSourceFactory.createSource(id, forceUpdate).getNote(id, callback);
  }
}
