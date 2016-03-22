package com.rishabh.roposo.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rishabh.roposo.R;
import com.rishabh.roposo.models.Story;
import com.rishabh.roposo.ui.activities.StoryDetailActivity;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rishabh bhatia on 21-03-2016.
 */
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

    private Activity context;
    private List<Story> stories;
    private List<String> following;

    public StoriesAdapter(Activity context,List<Story> stories) {
        this.context = context;
        this.stories = stories;

        following  = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_story,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Story story = stories.get(position);

        holder.clear();

        if(story.getAuthor() != null && !story.getAuthor().isEmpty())
        {
            holder.tv_name.setText(story.getAuthor());
        }

        try {
            if (story.getCreatedOn() != null && story.getCreatedOn() != 0)
            {
                holder.tv_date.setVisibility(View.VISIBLE);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(story.getCreatedOn());
                holder.tv_date.setText(new PrettyTime().format(calendar.getTime()));
            }else if(story.getVerb() != null && !story.getVerb().isEmpty())
            {
                holder.tv_date.setVisibility(View.VISIBLE);
                holder.tv_date.setText(story.getVerb());
            }
        }catch (NullPointerException np) {
            if(story.getVerb() != null && !story.getVerb().isEmpty())
            {
                holder.tv_date.setVisibility(View.VISIBLE);
                holder.tv_date.setText(story.getVerb());
            }
        }

        if(story.getDescription() != null && !story.getDescription().isEmpty())
        {
            holder.tv_description.setVisibility(View.VISIBLE);
            holder.tv_description.setText(story.getDescription().trim());
        }

        if(story.getTitle() != null && !story.getTitle().isEmpty())
        {
            holder.tv_title.setVisibility(View.VISIBLE);
            holder.tv_title.setText(story.getTitle().trim());
        }

        if(story.getProfilePhotoUrl() != null && !story.getProfilePhotoUrl().isEmpty())
        {
            Glide.with(context)
                    .load(story.getProfilePhotoUrl())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.profilePhoto);
        }

        if(story.getContentPhoto() != null && !story.getContentPhoto().isEmpty())
        {

            holder.image.setVisibility(View.VISIBLE);

            Glide.with(context)
                    .load(story.getContentPhoto())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.image);
        }

        if(story.isFollowing() || following.contains(story.getAuthor())) {
            Log.d("rick","follow true: "+story.getAuthor());

            holder.btFollow.setText("Following");

            //assuming every story with no author is a self generated roposo content
            if(!following.contains(story.getAuthor()))
            {
                following.add(story.getAuthor());
            }
        }

        holder.btFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(story.isFollowing()) {
                    story.setFollowing(false);
                    holder.btFollow.setText("Follow");
                    Log.d("rick","unfollow: "+story.getAuthor());

                    if(following.contains(story.getAuthor())) {
                        following.remove(story.getAuthor());
                    }
                }else {
                    Log.d("rick","follow: "+story.getAuthor());
                    story.setFollowing(true);
                    holder.btFollow.setText("Following");

                    if(!following.contains(story.getAuthor()))
                    {
                        following.add(story.getAuthor());
                    }
                }
            }
        });

        holder.btViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchStoryDetaiView(story);
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchStoryDetaiView(story);
            }
        });
    }

    private void launchStoryDetaiView(Story story)
    {
        Intent intent = new Intent(context, StoryDetailActivity.class);

        intent.putExtra("title",story.getTitle());

        if(story.getAuthor() != null)
        {
            intent.putExtra("author", story.getAuthor());
        }

        if(story.getProfilePhotoUrl() != null)
        {
            intent.putExtra("profilePhoto", story.getProfilePhotoUrl());
        }

        if(story.getAbout() != null)
        {
            intent.putExtra("about", story.getAbout());
        }

        if(story.getCreatedOn() != null && story.getCreatedOn() != 0) {
            intent.putExtra("createdOn", story.getCreatedOn());
        }else {
            intent.putExtra("verb", story.getVerb());
        }

        if(story.getDescription() != null)
        {
            intent.putExtra("description", story.getDescription());
        }

        if(story.getContentPhoto() != null)
        {
            intent.putExtra("si", story.getContentPhoto());
        }

        if(following.contains(story.getAuthor())) {
            intent.putExtra("isFollowing", true);
        }else {
            intent.putExtra("isFollowing", story.isFollowing());
        }

        context.startActivity(intent);
        context.overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name,tv_date,tv_description,tv_title;
        private ImageView image;
        private CircleImageView profilePhoto;
        private Button btViewProfile,btFollow;
        private CardView card;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.name);
            tv_date = (TextView)view.findViewById(R.id.date);
            tv_description = (TextView)view.findViewById(R.id.tvDescription);
            tv_title = (TextView)view.findViewById(R.id.tvTitle);
            image = (ImageView) view.findViewById(R.id.image);
            profilePhoto = (CircleImageView) view.findViewById(R.id.photo);
            btViewProfile = (Button) view.findViewById(R.id.bt_view_profile);
            btFollow = (Button) view.findViewById(R.id.bt_follow);
            card = (CardView) view.findViewById(R.id.card);
        }

        public void clear() {
            tv_name.setText("Roposo");
            tv_date.setText("");

            image.setVisibility(View.GONE);
            tv_description.setVisibility(View.GONE);
            tv_title.setVisibility(View.GONE);

            //use some fallback photos here
            image.setImageBitmap(null);
            profilePhoto.setImageResource(R.mipmap.ic_launcher);

            btFollow.setText("Follow");
        }
    }
}
