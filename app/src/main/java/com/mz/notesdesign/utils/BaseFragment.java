package com.mz.notesdesign.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.mz.notesdesign.internal.NotesApplication;
import com.mz.notesdesign.internal.di.Component;
import com.mz.notesdesign.internal.di.component.ApplicationComponent;

public class BaseFragment extends Fragment {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
    getApplicationComponent().inject(this);
  }

  protected ApplicationComponent getApplicationComponent() {
    return NotesApplication.getAppComponent(getContext());
  }

  protected <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((Component<C>) getActivity()).getComponent());
  }
}
