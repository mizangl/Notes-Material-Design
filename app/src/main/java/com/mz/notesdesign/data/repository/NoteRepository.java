package com.mz.notesdesign.data.repository;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.entity.Note;
import java.util.List;

public interface NoteRepository {

  void getNotes(boolean forceUpdate, @NonNull NoteRepository.Callback callback);

  void addNote(@NonNull Note note);

  void getNote(@NonNull String id, boolean forceUpdate, @NonNull NoteRepository.Callback callback);

  void removeNote(@NonNull String id);

  void refreshData();

  public interface Callback {
    void returnNotes(List<Note> notes);

    void returnNote(Note note);

    void onError();
  }
}
