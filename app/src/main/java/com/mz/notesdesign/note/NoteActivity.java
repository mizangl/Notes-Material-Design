package com.mz.notesdesign.note;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mz.notesdesign.R;
import com.mz.notesdesign.utils.BaseActivity;

public class NoteActivity extends BaseActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    supportPostponeEnterTransition();

    setContentView(R.layout.activity_note);
    ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar supportActionBar = getSupportActionBar();
    supportActionBar.setDisplayHomeAsUpEnabled(true);

    final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
    final String image = getIntent().getStringExtra("image");

    Glide.with(this).load(image).asBitmap().into(new SimpleTarget<Bitmap>() {
      @Override
      public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
        imageView.setImageBitmap(resource);
        supportStartPostponedEnterTransition();
      }
    });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        supportFinishAfterTransition();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
