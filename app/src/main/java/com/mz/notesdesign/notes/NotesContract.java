package com.mz.notesdesign.notes;

import android.widget.ImageView;
import com.mz.notesdesign.data.entity.Note;
import java.util.List;

/**
 * Define a contract between NotesFragment and Presenter
 */
public interface NotesContract {

  interface View{

    void showProgress();
    void hideProgress();
    void showAddNoteScreen();

    void showErrorMessage();

    void showNotes(List<Note> notes);
  }

  interface ActionsListener{
    void bind(View view);
    void loadNotes(boolean forceUpdate);
    void addNotes();
  }

  interface ItemActionsListener{
    void onNoteItemClicked(ImageView imageView, Note note);
  }

}
