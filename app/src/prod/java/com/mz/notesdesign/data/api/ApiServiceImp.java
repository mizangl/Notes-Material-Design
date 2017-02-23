package com.mz.notesdesign.data.api;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class ApiServiceImp implements ApiService {

  private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

  private static final ArrayMap<String, Note> NOTES_SERVICE_DATA;

  static {
    NOTES_SERVICE_DATA = new ArrayMap<>(5);
    addNote("Mock 1",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        null);
    addNote("Mock 2",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        null);
    addNote("Mock 3",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        null);
    addNote("Mock 4",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        "http://www.redcuadrada.com/wp-content/uploads/2015/08/lorem_ipsum_g.jpg");
    addNote("Mock 5", "Lorem ipsum dolor sit amet",
        "http://www.redcuadrada.com/wp-content/uploads/2015/08/lorem_ipsum_g.jpg");
  }

  @Inject public ApiServiceImp() {
  }

  private static void addNote(String title, String description, String image) {
    Note note = new Note(title, description, image);
    NOTES_SERVICE_DATA.put(note.getId(), note);
  }

  @Override public void getNotes(@NonNull final NoteRepository.Callback callback) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        callback.returnNotes(new ArrayList<Note>(NOTES_SERVICE_DATA.values()));
      }
    }, SERVICE_LATENCY_IN_MILLIS);
  }

  @Override public void addNote(@NonNull final Note note) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        NOTES_SERVICE_DATA.put(note.getId(), note);
      }
    }, SERVICE_LATENCY_IN_MILLIS);
  }

  @Override
  public void getNote(@NonNull final String id, @NonNull final NoteRepository.Callback callback) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        Note note = NOTES_SERVICE_DATA.get(id);
        if (note == null) {
          callback.onError();
        } else {
          callback.returnNote(note);
        }
      }
    }, SERVICE_LATENCY_IN_MILLIS);
  }

  @Override public void removeNote(@NonNull final String id) {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        NOTES_SERVICE_DATA.remove(id);
      }
    }, SERVICE_LATENCY_IN_MILLIS);
  }
}