package com.mz.notesdesign.notes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.IdlingResource;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mz.notesdesign.R;
import com.mz.notesdesign.internal.di.Component;
import com.mz.notesdesign.internal.di.component.DaggerNotesComponent;
import com.mz.notesdesign.internal.di.component.NotesComponent;
import com.mz.notesdesign.internal.di.module.NotesModule;
import com.mz.notesdesign.utils.BaseActivity;

public class NotesActivity extends BaseActivity implements Component<NotesComponent>{

  @BindView(R.id.drawer) DrawerLayout drawerLayout;

  private NotesComponent notesComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes);
    ButterKnife.bind(this);

    notesComponent = DaggerNotesComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .notesModule(new NotesModule())
        .build();

    setupToolbar();

    setupDrawer();

    setupNavigation();

    if (savedInstanceState == null) {
      navigateToNotesFragments();
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (android.R.id.home == item.getItemId()) {
      drawerLayout.openDrawer(GravityCompat.START);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public NotesComponent getComponent() {
    return notesComponent;
  }

  /**
   * Setup Toolbar
   */
  private void setupToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar supportActionBar = getSupportActionBar();
    supportActionBar.setDisplayHomeAsUpEnabled(true);
  }
  /**
   * Setup Drawer
   */
  private void setupDrawer() {

    drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_open,
            R.string.navigation_close);

    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();
  }

  /**
   * Setup NavigationView
   */
  private void setupNavigation() {
    NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            drawerLayout.closeDrawers();
            return true;
          }
        });
  }

  private void navigateToNotesFragments() {
    getSupportFragmentManager().beginTransaction()
        .add(R.id.container, NotesFragment.newInstance())
        .commit();
  }

  @VisibleForTesting public IdlingResource getCountingIdlingResources() {
    return null;
  }

}
