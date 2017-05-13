package com.mz.notesdesign.data.cache;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import com.mz.notesdesign.data.entity.Note;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    List<Note> notes = new ArrayList<>(DATA.values());
    Collections.sort(notes, new Comparator<Note>() {
      @Override public int compare(Note o1, Note o2) {
        return o1.getTitle().compareTo(o2.getTitle());
      }
    });
    return notes;
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
