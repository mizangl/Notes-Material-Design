package com.mz.notesdesign.utils;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import com.mz.notesdesign.internal.NotesApplication;
import com.mz.notesdesign.internal.di.component.ApplicationComponent;
import com.mz.notesdesign.internal.di.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

  @Override public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);

    getApplicationComponent().inject(this);
  }

  protected ActivityModule getActivityModule(){
    return new ActivityModule(this);
  }

  protected ApplicationComponent getApplicationComponent(){
    return NotesApplication.getAppComponent(this);
  }
}
