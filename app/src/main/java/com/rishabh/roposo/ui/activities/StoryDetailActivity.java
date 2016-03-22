package com.rishabh.roposo.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rishabh.roposo.R;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rishabh bhatia on 22-03-2016.
 */
public class StoryDetailActivity extends AppCompatActivity {

    @Bind(R.id.bt_view_profile) Button btProfile;
    @Bind(R.id.bt_follow) Button btFollow;
    @Bind(R.id.about) TextView tvAbout;
    @Bind(R.id.name) TextView tvName;
    @Bind(R.id.tvTitle) TextView tvTitle;
    @Bind(R.id.photo) CircleImageView profilePhoto;
    @Bind(R.id.image) ImageView contentPhoto;
    @Bind(R.id.date) TextView tvDate;
    @Bind(R.id.tvDescription) TextView tvDescription;
    @Bind(R.id.space) Space space;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);

        btProfile.setVisibility(View.GONE);
        space.setVisibility(View.GONE);

        populateData();

    }

    private void populateData()
    {
        if(getIntent().getStringExtra("author") != null && !getIntent().getStringExtra("author").isEmpty())
        {
            tvName.setText(getIntent().getStringExtra("author"));
        }

        if(getIntent().getStringExtra("title") != null && !getIntent().getStringExtra("title").isEmpty())
        {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(getIntent().getStringExtra("title"));
        }

        if(getIntent().getLongExtra("createdOn",0) != 0) {
            tvDate.setVisibility(View.VISIBLE);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(getIntent().getLongExtra("createdOn",0));
            tvDate.setText(new PrettyTime().format(calendar.getTime()));
        }else {
            tvDate.setVisibility(View.VISIBLE);
            tvDate.setText(getIntent().getStringExtra("verb"));
        }

        if(getIntent().getStringExtra("profilePhoto") != null && !getIntent().getStringExtra("profilePhoto").isEmpty())
        {
            Glide.with(this)
                    .load(getIntent().getStringExtra("profilePhoto"))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(profilePhoto);
        }

        if(getIntent().getStringExtra("about") != null && !getIntent().getStringExtra("about").isEmpty()) {
            tvAbout.setVisibility(View.VISIBLE);
            tvAbout.setText(getIntent().getStringExtra("about"));
        }

        if(getIntent().getStringExtra("si") != null && !getIntent().getStringExtra("si").isEmpty()) {
            contentPhoto.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(getIntent().getStringExtra("si"))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(contentPhoto);
        }

        if(getIntent().getStringExtra("description") != null && !getIntent().getStringExtra("description").isEmpty()) {
            tvDescription.setVisibility(View.VISIBLE);
            tvDescription.setText(getIntent().getStringExtra("description"));
        }

        if(getIntent().getBooleanExtra("isFollowing",false)) {
            btFollow.setText("Following");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
