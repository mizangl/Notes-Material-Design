package com.mz.notesdesign.data.api;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import com.mz.notesdesign.data.entity.Note;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class ApiServiceImp implements ApiService {

  private static final ArrayMap<String, Note> NOTES_SERVICE_DATA;

  static {
    NOTES_SERVICE_DATA = new ArrayMap<>(5);
    addNote("Mock 1",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        "https://krebsonsecurity.com/wp-content/uploads/2014/08/loremipsum.png");
    addNote("Mock 2",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        "http://www.blogodisea.com/wp-content/uploads/2012/07/tintin-lorem-ipsum.jpg");
    addNote("Mock 3",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTvIA8ribgALEW17SRiK7RCZnifjAxLHOtDqowIM0o6qmEFIE1M");
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

  @Override public List<Note> getNotes() {
    List<Note> notes = new ArrayList<>(NOTES_SERVICE_DATA.values());

    Collections.sort(notes, new Comparator<Note>() {
      @Override public int compare(Note o1, Note o2) {
        return o1.getTitle().compareTo(o2.getTitle());
      }
    });

    return notes;
  }

  @Override public void addNote(@NonNull final Note note) {
    NOTES_SERVICE_DATA.put(note.getId(), note);
  }

  @Override public Note getNote(@NonNull String id) {
    return NOTES_SERVICE_DATA.get(id);
  }

  @Override public void removeNote(@NonNull final String id) {
    NOTES_SERVICE_DATA.remove(id);
  }
}