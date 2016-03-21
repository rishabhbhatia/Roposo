package com.rishabh.roposo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rishabh.roposo.R;
import com.rishabh.roposo.models.Story;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * Created by rishabh bhatia on 21-03-2016.
 */
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

    private Context context;
    private List<Story> stories;

    public StoriesAdapter(Context context,List<Story> stories) {
        this.context = context;
        this.stories = stories;
        Log.d("rick","size is: "+stories.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_story,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Story story = stories.get(position);

        holder.clear();

        if(story.getTitle() != null && !story.getTitle().isEmpty()) {
            holder.tv_name.setText(story.getTitle());
        }

        try {
            if (story.getCreatedOn() != null && !story.getCreatedOn().isEmpty()) {
                holder.tv_date.setVisibility(View.VISIBLE);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.
                        getLong(story.getCreatedOn()));
                holder.tv_date.setText(new PrettyTime().format(calendar.getTime()));
            }
        }catch (NullPointerException np){
        }

        if(story.getDescription() != null && !story.getDescription().isEmpty()) {
            holder.tv_description.setVisibility(View.VISIBLE);
            holder.tv_description.setText(story.getDescription().trim());
        }

        if(story.getProfilePhotoUrl() != null && !story.getProfilePhotoUrl().isEmpty()) {
            Glide.with(context)
                    .load(story.getProfilePhotoUrl())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.photo);
        }

        if(story.getContentPhoto() != null && !story.getContentPhoto().isEmpty()) {

            holder.image.setVisibility(View.VISIBLE);

            Glide.with(context)
                    .load(story.getContentPhoto())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_date,tv_description;
        private ImageViewTouch image;
        private CircleImageView photo;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.name);
            tv_date = (TextView)view.findViewById(R.id.date);
            tv_description = (TextView)view.findViewById(R.id.tvDescription);
            image = (ImageViewTouch) view.findViewById(R.id.image);
            photo = (CircleImageView) view.findViewById(R.id.photo);
        }

        public void clear() {
            tv_name.setText("Test title");
            tv_date.setText("");
            tv_description.setText("");

            image.setVisibility(View.GONE);
            tv_description.setVisibility(View.GONE);

            //use some fallback photos here
            image.setImageBitmap(null);
            photo.setImageBitmap(null);
        }
    }
}
