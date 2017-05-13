package com.mz.notesdesign.data.api;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;
import java.util.List;

public interface ApiService {

  List<Note> getNotes();

  void addNote(@NonNull Note note);

  Note getNote(@NonNull String id);

  void removeNote(@NonNull String id);
}
