package com.mz.notesdesign.data.entity;

import android.support.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public class Note {

  private final String id;
  @Nullable private final String title;
  @Nullable private final String content;
  @Nullable private final String image;

  public Note(String title, String content){
    this(title, content, null);
  }

  public Note(@Nullable String title, @Nullable String content, @Nullable String image){
    id = UUID.randomUUID().toString();
    this.title = title;
    this.content = content;
    this.image = image;
  }

  @Nullable public String getContent() {
    return content;
  }

  public String getId() {
    return id;
  }

  @Nullable public String getImage() {
    return image;
  }

  @Nullable public String getTitle() {
    return title;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Note o = (Note) obj;

    return Objects.equals(id, o.getId())
        && Objects.equals(title, o.getTitle())
        && Objects.equals(content, o.getContent())
        && Objects.equals(image, o.getImage());
  }

  @Override public int hashCode() {
    return Objects.hash(id, title, content, image);
  }
}
