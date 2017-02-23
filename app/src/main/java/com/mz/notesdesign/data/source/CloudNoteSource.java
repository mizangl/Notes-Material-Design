package com.mz.notesdesign.data.source;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.api.ApiService;
import com.mz.notesdesign.data.cache.NoteCache;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;

public class CloudNoteSource implements NoteSource {

  private final NoteCache noteCache;
  private final ApiService apiService;

  public CloudNoteSource(NoteCache noteCache, ApiService apiService) {
    this.noteCache = noteCache;
    this.apiService = apiService;
  }

  @Override public void getNotes(NoteRepository.Callback callback) {
    apiService.getNotes(callback);
  }

  @Override public void addNote(@NonNull Note note) {
    apiService.addNote(note);
    noteCache.put(note);
  }

  @Override public void getNote(@NonNull String id, NoteRepository.Callback callback) {
    apiService.getNote(id, callback);
  }

  @Override public void removeNote(@NonNull String id) {
    noteCache.remove(id);
    apiService.removeNote(id);
  }

  @Override public void refreshData() {
    noteCache.evictAll();
  }
}
