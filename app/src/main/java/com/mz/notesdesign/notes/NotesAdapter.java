package com.mz.notesdesign.notes;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.mz.notesdesign.R;
import com.mz.notesdesign.data.entity.Note;
import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

  private List<Note> data = new ArrayList<>();
  private final LayoutInflater inflater;
  private final DrawableRequestBuilder<String> drawableRequestBuilder;
  private final NotesContract.ItemActionsListener listener;

  public NotesAdapter(Context context, NotesContract.ItemActionsListener listener) {
    inflater = LayoutInflater.from(context);
    drawableRequestBuilder = Glide.with(context).fromString().thumbnail(0.5f);
    this.listener = listener;
  }

  @Override public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new NoteViewHolder(
        inflater.inflate(R.layout.layout_note_item, parent, false));
  }

  @Override public void onBindViewHolder(NoteViewHolder holder, int position) {
    Note note = data.get(position);

    holder.titleView.setText(note.getTitle());
    if (!TextUtils.isEmpty(note.getImage())){
      drawableRequestBuilder
          .load(note.getImage())
          .into(holder.imageView);

    }
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

  public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.note_detail_title) TextView titleView;
    @BindView(R.id.note_detail_image) ImageView imageView;

    public NoteViewHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      ButterKnife.bind(this, itemView);
    }

    @Override public void onClick(View v) {
      listener.onNoteItemClicked(imageView, data.get(getAdapterPosition()));
    }
  }

}
