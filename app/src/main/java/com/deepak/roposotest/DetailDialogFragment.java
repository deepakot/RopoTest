package com.deepak.roposotest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepak.roposotest.Models.StoryData;
import com.deepak.roposotest.Models.UserData;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sharma on 5/16/2016.
 */
public class DetailDialogFragment extends DialogFragment{
    Dialog d;
    String id,db,description,si,title,type,url,user_name,user_id,user_image;
    int likes_count,comment_count;
    Boolean is_like,is_following;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        likes_count= getArguments().getInt("likes_count");
        comment_count= getArguments().getInt("comment_count");
        is_following= getArguments().getBoolean("is_following");
        is_like= getArguments().getBoolean("is_like");
        db= getArguments().getString("db");
        id= getArguments().getString("id");
        description= getArguments().getString("description");
        si= getArguments().getString("si");
        title= getArguments().getString("title");
        type= getArguments().getString("type");
        url= getArguments().getString("url");
        user_name= getArguments().getString("user_name");
        user_id= getArguments().getString("user_id");
        user_image= getArguments().getString("user_image");
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
    }
    static DetailDialogFragment newInstance(StoryData storyData, UserData userData) {
        DetailDialogFragment f = new DetailDialogFragment();
        Bundle args = new Bundle();
        args.putString("id",storyData.getId());
        args.putString("db",storyData.getDb());
        args.putString("description",storyData.getDescription());
        args.putString("si",storyData.getSi());
        args.putString("title",storyData.getTitle());
        args.putString("type",storyData.getType());
        args.putString("url",storyData.getUrl());
        args.putBoolean("is_like",storyData.isLike_flag());
        args.putBoolean("is_following",storyData.is_following());
        args.putInt("likes_count",storyData.getLikes_count());
        args.putInt("comment_count",storyData.getComment_count());
        args.putString("user_name",userData.getUsername());
        args.putString("user_id",userData.getId());
        args.putString("user_image",userData.getImage());
        f.setArguments(args);
        return f;
    }

    @Override
    public void onStart() {
        super.onStart();
        d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if (d != null && d.isShowing()) {
            d.dismiss();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment, container, false);
        TextView storyTittleTV = (TextView) root.findViewById(R.id.titleTV);
        TextView stroyDescTV = (TextView) root.findViewById(R.id.descTV);
        TextView closeTV = (TextView) root.findViewById(R.id.closeTV);
        TextView storyUserName = (TextView) root.findViewById(R.id.usernameTV);
        CircleImageView userImageIV = (CircleImageView) root.findViewById(R.id.userImageIV);
        ImageView storyImageIV = (ImageView) root.findViewById(R.id.storyImageIV);
        final TextView likeCountTV = (TextView) root.findViewById(R.id.likeCountTV);
        TextView commentCountTV = (TextView) root.findViewById(R.id.commentCountTV);
        final TextView followButton = (TextView) root.findViewById(R.id.followButton);
        final ImageView likeButton = (ImageView) root.findViewById(R.id.likeButton);
        ImageView closeButton = (ImageView) root.findViewById(R.id.closeButton);
        ImageView commentButton = (ImageView) root.findViewById(R.id.commentButton);
        storyTittleTV.setText(title);
        if(description.equals(""))
        {
            stroyDescTV.setVisibility(View.GONE);
        }
        else
        {
            stroyDescTV.setText(description);
        }
        storyUserName.setText(user_name);
        likeCountTV.setText(""+likes_count);
        commentCountTV.setText(""+comment_count);
        likeCountTV.setText(String.valueOf(likes_count));
        commentCountTV.setText(String.valueOf(comment_count));
        if (!is_like)
            likeButton.setImageResource(R.drawable.like);
        else
            likeButton.setImageResource(R.drawable.like_filled);
        if (!is_following) {
            followButton.setText("Follow");
            followButton.setTextColor(getActivity().getResources().getColor(R.color.black));
            followButton.setBackgroundResource(R.drawable.follow);
        } else {
            followButton.setText(R.string.unfollow);
            followButton.setTextColor(getActivity().getResources().getColor(R.color.white));
            followButton.setBackgroundResource(R.drawable.un_follow);
        }
        commentButton.setImageResource(R.drawable.comment);
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        int width = p.x;


        Picasso.with(getActivity())
                .load(si)
                .placeholder(R.drawable.ropo)
                .resize(width, 0)
                .error(R.color.colorAccent)
                .into(storyImageIV);


        Picasso.with(getActivity())
                .load(user_image)
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorAccent)
                .into(userImageIV);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!is_like)
                {
                    likes_count++;
                    is_like = true;
                    likeButton.setImageResource(R.drawable.like_filled);
                    likeCountTV.setText(String.valueOf(likes_count));
                }
                else
                {
                    likes_count--;
                    is_like = false;
                    likeButton.setImageResource(R.drawable.like);
                    likeCountTV.setText(String.valueOf(likes_count));
                }

            }
        });
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_following) {
                    followButton.setText("Follow");
                    followButton.setTextColor(getActivity().getResources().getColor(R.color.black));
                    followButton.setBackgroundResource(R.drawable.follow);
                    is_following=false;
                } else {
                    followButton.setText(R.string.unfollow);
                    followButton.setTextColor(getActivity().getResources().getColor(R.color.white));
                    followButton.setBackgroundResource(R.drawable.un_follow);
                    is_following=true;
                }
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        closeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return root;
    }
}
