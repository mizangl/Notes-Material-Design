package com.mz.notesdesign.data.cache;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.entity.Note;
import java.util.List;

public interface NoteCache {

  void put(@NonNull List<Note> notes);

  void remove(@NonNull String id);

  void put(Note note);

  Note get(@NonNull String id);

  List<Note> get();

  boolean isCached(@NonNull String id);

  boolean hasCachedData();

  void evictAll();
}
