package com.mz.notesdesign.data.source;

import android.support.annotation.NonNull;
import com.mz.notesdesign.data.api.ApiService;
import com.mz.notesdesign.data.cache.NoteCache;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteSourceFactory {

  private  final NoteCache noteCache;
  private final ApiService apiService;

  @Inject
  public NoteSourceFactory(NoteCache noteCache, ApiService apiService) {
    this.noteCache = noteCache;
    this.apiService = apiService;
  }

  public NoteSource createSource(boolean forceUpdate){

    NoteSource noteSource;

    if(!forceUpdate
        && noteCache.hasCachedData()){
      noteSource = new DiskNoteSource(noteCache);
    }else{
      noteSource = new CloudNoteSource(noteCache, apiService);
    }

    return noteSource;
  }

  public NoteSource createSource(@NonNull String id, boolean forceUpdate){

    NoteSource noteSource;

    if(!forceUpdate
        && noteCache.isCached(id)){
      noteSource = new DiskNoteSource(noteCache);
    }else{
      noteSource = new CloudNoteSource(noteCache, apiService);
    }

    return noteSource;
  }

  public NoteSource createCloudSource(){
    return new CloudNoteSource(noteCache, apiService);
  }

}
