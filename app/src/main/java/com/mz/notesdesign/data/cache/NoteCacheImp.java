package com.mz.notesdesign.data.cache;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import com.mz.notesdesign.data.entity.Note;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class NoteCacheImp implements NoteCache {

  private final static ArrayMap<String, Note> DATA;

  static {
    DATA = new ArrayMap<>();
  }

  @Inject public NoteCacheImp() {
  }

  @Override public void put(@NonNull List<Note> notes) {
    for (Note note : notes) {
      DATA.put(note.getId(), note);
    }
  }

  @Override public void put(Note note) {
    DATA.put(note.getId(), note);
  }

  @Override public Note get(@NonNull String id) {
    return DATA.get(id);
  }

  @Override public void remove(@NonNull String id) {
    DATA.remove(id);
  }

  @Override public List<Note> get() {
    return new ArrayList<>(DATA.values());
  }

  @Override public boolean isCached(@NonNull String id) {
    return DATA.containsKey(id);
  }

  @Override public boolean hasCachedData() {
    return !DATA.isEmpty();
  }

  @Override public void evictAll() {
    DATA.clear();
  }
}
