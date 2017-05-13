package com.mz.notesdesign.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mz.notesdesign.R;
import com.mz.notesdesign.data.entity.Note;
import com.mz.notesdesign.internal.di.component.NotesComponent;
import com.mz.notesdesign.note.NoteActivity;
import com.mz.notesdesign.utils.BaseFragment;
import java.util.List;
import javax.inject.Inject;

public class NotesFragment extends BaseFragment
    implements NotesContract.View, NotesContract.ItemActionsListener {

  @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
  @BindView(R.id.recycler) RecyclerView recyclerView;

  @Inject NotesContract.ActionsListener actionsListener;

  private NotesAdapter adapter;

  public static NotesFragment newInstance() {
    Bundle args = new Bundle();
    NotesFragment fragment = new NotesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

    ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    swipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(getActivity(), R.color.colorPrimary),
        ContextCompat.getColor(getActivity(), R.color.colorAccent),
        ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        actionsListener.loadNotes(true);
      }
    });

    int numColumns = getContext().getResources().getInteger(R.integer.columns);

    recyclerView.setHasFixedSize(false);
    recyclerView.setLayoutManager(
        new StaggeredGridLayoutManager(numColumns, StaggeredGridLayoutManager.VERTICAL));

    adapter = new NotesAdapter(getActivity(), this);

    recyclerView.setAdapter(adapter);
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    getComponent(NotesComponent.class).inject(this);

    actionsListener.bind(this);

    actionsListener.loadNotes(false);
  }

  @Override public void showProgress() {
    swipeRefreshLayout.post(new Runnable() {
      @Override public void run() {
        swipeRefreshLayout.setRefreshing(true);
      }
    });
  }

  @Override public void hideProgress() {
    swipeRefreshLayout.post(new Runnable() {
      @Override public void run() {
        swipeRefreshLayout.setRefreshing(false);
      }
    });
  }

  @Override public void showAddNoteScreen() {

  }

  @Override public void showErrorMessage() {
    Snackbar.make(getView(), "Error", Snackbar.LENGTH_SHORT);
  }

  @Override public void showNotes(List<Note> notes) {
    adapter.bind(notes);
  }

  @Override public void onNoteItemClicked(ImageView imageView, Note note) {

    ActivityOptionsCompat options =
        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), imageView,
            getString(R.string.transition_backdrop));

    Intent intent = new Intent(getActivity(), NoteActivity.class);
    intent.putExtra("image", note.getImage());

    startActivity(intent, options.toBundle());
  }
}
