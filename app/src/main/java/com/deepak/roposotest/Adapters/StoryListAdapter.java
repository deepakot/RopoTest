package com.deepak.roposotest.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deepak.roposotest.MainActivity;
import com.deepak.roposotest.Models.StoryData;
import com.deepak.roposotest.Models.UserData;
import com.deepak.roposotest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sharma on 5/16/2016.
 */
public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.MyViewHolder>{
    Context m_context;
    public MyOnclickListener myOnclickListener;
    ArrayList<StoryData> storyList=new ArrayList<>();
    ArrayList<UserData> userList=new ArrayList<>();
    public StoryListAdapter(ArrayList<StoryData> storyList,ArrayList<UserData> userList,Context m_context) {
        this.storyList = storyList;
        this.userList = userList;
        this.m_context = m_context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView storyTittleTV;
        TextView stroyDescTV;
        //  TextView storyIsFollowingTV;
        TextView storyUserName;
        CircleImageView userImageIV;
        ImageView storyImageIV;
        TextView likeCountTV;
        TextView commentCountTV;
        ImageView likeButton;
        ImageView shareButton;
        ImageView commentButton;
        LinearLayout ll;

        TextView followButton;
        public MyViewHolder(View convertView){
            super(convertView);
            storyTittleTV = (TextView) convertView.findViewById(R.id.titleTV);
            //  storyIsFollowingTV = (TextView) convertView.findViewById(R.id.isFollowingTV);
            storyUserName = (TextView) convertView.findViewById(R.id.usernameTV);
            userImageIV = (CircleImageView) convertView.findViewById(R.id.userImageIV);
            storyImageIV = (ImageView) convertView.findViewById(R.id.storyImageIV);
            likeCountTV = (TextView) convertView.findViewById(R.id.likeCountTV);
            commentCountTV = (TextView) convertView.findViewById(R.id.commentCountTV);
            followButton = (TextView) convertView.findViewById(R.id.followButton);

            likeButton = (ImageView) convertView.findViewById(R.id.likeButton);
            shareButton = (ImageView) convertView.findViewById(R.id.shareButton);
            commentButton = (ImageView) convertView.findViewById(R.id.commentButton);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_view, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final StoryData story = storyList.get(position);
        String db = story.getDb();
        UserData user=new UserData();
        if(userList.get(0)!=null)
        {
            if(db.equals(userList.get(0).getId()))
            {
                user= userList.get(0);
            }
            else{
                user= userList.get(1);
            }
        }
        holder.storyTittleTV.setText(story.getTitle());
        holder.storyUserName.setText(user.getUsername());
        int likeCount=story.getLikes_count();
            holder.likeCountTV.setText(String.valueOf(likeCount));
        int commentCount=story.getComment_count();
            holder.commentCountTV.setText(String.valueOf(commentCount));
        if (!story.isLike_flag())
            holder.likeButton.setImageResource(R.drawable.like);
        else
            holder.likeButton.setImageResource(R.drawable.like_filled);
        if (!story.is_following()) {
            holder.followButton.setText("Follow");
            holder.followButton.setTextColor(m_context.getResources().getColor(R.color.black));
            holder.followButton.setBackgroundResource(R.drawable.follow);
        } else {
            holder.followButton.setText(R.string.unfollow);
            holder.followButton.setTextColor(m_context.getResources().getColor(R.color.white));
            holder.followButton.setBackgroundResource(R.drawable.un_follow);
        }
        holder.commentButton.setImageResource(R.drawable.comment);
        WindowManager windowManager = (WindowManager) m_context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        int width = p.x;


        Picasso.with(m_context)
                .load(story.getSi())
                .placeholder(R.drawable.ropo)
                .resize(width, 0)
                .error(R.color.colorAccent)
                .into(holder.storyImageIV);


        Picasso.with(m_context)
                .load(user.getImage())
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorAccent)
                .into(holder.userImageIV);

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!storyList.get(position).isLike_flag())
                {
                    holder.likeButton.setImageResource(R.drawable.like_filled);
                    holder.likeCountTV.setText(String.valueOf(storyList.get(position).getLikes_count()+1));
                    storyList.get(position).setLikes_count(storyList.get(position).getLikes_count()+1);
                    storyList.get(position).setLike_flag(true);
                }
                else
                {
                    holder.likeButton.setImageResource(R.drawable.like);
                    holder.likeCountTV.setText(String.valueOf(storyList.get(position).getLikes_count()-1));
                    storyList.get(position).setLikes_count(storyList.get(position).getLikes_count()-1);
                    storyList.get(position).setLike_flag(false);
                }

            }
        });
        holder.storyTittleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callListener(position);
            }
        });
        holder.storyUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callListener(position);
            }
        });
        holder.userImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callListener(position);
            }
        });
        holder.storyImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callListener(position);
            }
        });
        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callListener(position);
            }
        });
        holder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storyList.get(position).is_following()) {
                    holder.followButton.setText("Follow");
                    holder.followButton.setTextColor(m_context.getResources().getColor(R.color.black));
                    holder.followButton.setBackgroundResource(R.drawable.follow);
                    storyList.get(position).setIs_following(false);
                } else {
                    holder.followButton.setText(R.string.unfollow);
                    holder.followButton.setTextColor(m_context.getResources().getColor(R.color.white));
                    holder.followButton.setBackgroundResource(R.drawable.un_follow);
                    storyList.get(position).setIs_following(true);
                }
                myOnclickListener = ((MainActivity)m_context);
                myOnclickListener.changeImage(storyList.get(position).getDb(),storyList.get(position).is_following());
            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, storyList.get(position).getUrl());
                sendIntent.setType("text/plain");
                m_context.startActivity(sendIntent);
            }
        });
    }

    public int getItemCount() {
        return storyList.size();
    }

    public interface MyOnclickListener {
        public void changeImage(String year1,Boolean dofollow);
        public void openDialog(StoryData storyData,UserData userData);
    }
    public void callListener(int position){
        myOnclickListener = ((MainActivity)m_context);
        UserData user=new UserData();
        if(userList.get(0)!=null)
        {
            if((storyList.get(position).getDb()).equals(userList.get(0).getId()))
            {
                user= userList.get(0);
            }
            else{
                user= userList.get(1);
            }
        }
        myOnclickListener.openDialog(storyList.get(position),user);
    }

}
