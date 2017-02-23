package com.mz.notesdesign.notes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mz.notesdesign.R;
import com.mz.notesdesign.data.entity.Note;
import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

  private final Context context;
  private List<Note> data = new ArrayList<>();

  public NotesAdapter(Context context) {
    this.context = context;
  }

  @Override public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new NoteViewHolder(
        LayoutInflater.from(context).inflate(R.layout.layout_note_item, parent, false));
  }

  @Override public void onBindViewHolder(NoteViewHolder holder, int position) {
    Note note = data.get(position);

    holder.titleView.setText(note.getTitle());
    holder.contentView.setText(note.getContent());
  }

  public void bind(List<Note> notes){
    if(notes == null) return;

    data.clear();
    data.addAll(notes);

    notifyDataSetChanged();
  }

  @Override public int getItemCount() {
    return data.size();
  }

  public class NoteViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.note_detail_title) TextView titleView;
    @BindView(R.id.note_detail_content) TextView contentView;

    public NoteViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }
}
