package com.mz.notesdesign.data.repository;

import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;
import java.util.List;

public class DefaultCallback implements NoteRepository.Callback {
  @Override public void returnNotes(List<Note> notes) {

  }

  @Override public void returnNote(Note note) {

  }

  @Override public void onError() {

  }
}
