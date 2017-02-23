package com.mz.notesdesign.data.api;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;

public interface ApiService {

  void getNotes(@NonNull NoteRepository.Callback callback);

  void addNote(@NonNull Note note);

  void getNote(@NonNull String id, @NonNull NoteRepository.Callback callback);

  void removeNote(@NonNull String id);
}
