package com.mz.notesdesign.internal;

import android.app.Application;
import android.content.Context;
import com.mz.notesdesign.internal.di.component.ApplicationComponent;
import com.mz.notesdesign.internal.di.component.DaggerApplicationComponent;
import com.mz.notesdesign.internal.di.module.ApplicationModule;

public class NotesApplication extends Application {

  private ApplicationComponent applicationComponent;

  public static ApplicationComponent getAppComponent(Context context) {
    return ((NotesApplication) context.getApplicationContext()).getApplicationComponent();
  }

  @Override public void onCreate() {
    super.onCreate();

    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
