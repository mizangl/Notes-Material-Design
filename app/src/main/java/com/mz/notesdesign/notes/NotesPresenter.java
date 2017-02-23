package com.mz.notesdesign.notes;

import com.mz.notesdesign.data.api.DefaultCallback;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.data.repository.NoteRepository;
import com.mz.notesdesign.internal.di.ActivityScope;
import java.util.List;
import javax.inject.Inject;

@ActivityScope public class NotesPresenter implements NotesContract.ActionsListener {

  private NotesContract.View view;

  private final NoteRepository noteRepository;

  @Inject public NotesPresenter(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  @Override public void bind(NotesContract.View view) {
    this.view = view;
  }

  @Override public void loadNotes(boolean forceUpdate) {
    view.showProgress();

    noteRepository.getNotes(forceUpdate, new DefaultCallback(){
      @Override public void returnNotes(List<Note> notes) {

        view.showNotes(notes);
        view.hideProgress();
      }

      @Override public void onError() {
        view.showErrorMessage();
      }
    });
  }

  @Override public void addNotes() {

  }
}
