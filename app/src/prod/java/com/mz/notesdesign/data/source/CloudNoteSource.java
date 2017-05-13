package com.mz.notesdesign.data.source;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.mz.notesdesign.data.api.ApiService;
import com.mz.notesdesign.data.cache.NoteCache;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;

public class CloudNoteSource implements NoteSource {

  private static final int SERVICE_LATENCY_IN_MILLIS = 2000;

  private final NoteCache noteCache;
  private final ApiService apiService;

  public CloudNoteSource(NoteCache noteCache, ApiService apiService) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {

      }
    }, SERVICE_LATENCY_IN_MILLIS);
    this.noteCache = noteCache;
    this.apiService = apiService;
  }

  @Override public void getNotes(final NoteRepository.Callback callback) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        callback.returnNotes(apiService.getNotes());
        noteCache.put(apiService.getNotes());
      }
    }, SERVICE_LATENCY_IN_MILLIS);
  }

  @Override public void addNote(@NonNull final Note note) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        apiService.addNote(note);
        noteCache.put(note);
      }
    }, SERVICE_LATENCY_IN_MILLIS);
  }

  @Override public void getNote(@NonNull final String id, final NoteRepository.Callback callback) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        callback.returnNote(apiService.getNote(id));
        noteCache.put(apiService.getNote(id));
      }
    }, SERVICE_LATENCY_IN_MILLIS);
  }

  @Override public void removeNote(@NonNull final String id) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        noteCache.remove(id);
        apiService.removeNote(id);
      }
    }, SERVICE_LATENCY_IN_MILLIS);
  }

  @Override public void refreshData() {
    noteCache.evictAll();
  }
}
