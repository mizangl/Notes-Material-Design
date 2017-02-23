package com.mz.notesdesign.internal.di.module;

import android.app.Activity;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides
  Context getContext(){
    return activity;
  }
}
