package com.mz.notesdesign.data.source;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;
import java.util.List;

public interface NoteSource {

  void getNotes(NoteRepository.Callback callback);

  void addNote(@NonNull Note note);

  void getNote(@NonNull String id, NoteRepository.Callback callback);

  void removeNote(@NonNull String id);

  void refreshData();
}
