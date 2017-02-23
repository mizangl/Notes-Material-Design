package com.mz.notesdesign.data.source;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.cache.NoteCache;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;

public class DiskNoteSource implements NoteSource {

  private final NoteCache noteCache;

  public DiskNoteSource(NoteCache noteCache) {
    this.noteCache = noteCache;
  }

  @Override public void getNotes(NoteRepository.Callback callback) {
    callback.returnNotes(noteCache.get());
  }

  @Override public void addNote(@NonNull Note note) {
    noteCache.put(note);
  }

  @Override public void getNote(@NonNull String id, NoteRepository.Callback callback) {
    callback.returnNote(noteCache.get(id));
  }

  @Override public void removeNote(@NonNull String id) {
    noteCache.remove(id);
  }

  @Override public void refreshData() {
    noteCache.evictAll();
  }
}
