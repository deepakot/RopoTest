package com.deepak.roposotest;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.deepak.roposotest.Adapters.StoryListAdapter;
import com.deepak.roposotest.Models.StoryData;
import com.deepak.roposotest.Models.UserData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements StoryListAdapter.MyOnclickListener,DetailDialogFragment.DetailOnclickListener{
    private RecyclerView recyclerView;
    private StoryListAdapter sAdapter;
    private static ArrayList<StoryData> storyList=new ArrayList<>();
    private static ArrayList<UserData> userList=new ArrayList<>();
    private TextView followButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        String jsonString = readJsonFromLocal();
        Type userType = new TypeToken<ArrayList<UserData>>() {}.getType();
        Type storyType = new TypeToken<ArrayList<StoryData>>() {}.getType();
        storyList = new Gson().fromJson(jsonString, storyType);
        userList = new Gson().fromJson(jsonString, userType);
        int size=storyList.size();
        for(int i=size-1;i>=0;i--)
        {
            if(i<2)
            {
                storyList.remove(i);
            }
            else{
                userList.remove(i);
            }
        }
        Log.d("asd log",""+storyList.size());
        Log.d("asd log",""+userList.size());

        sAdapter = new StoryListAdapter(storyList,userList,MainActivity.this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sAdapter);
    }

    @Override
    public void changeImage(String id,Boolean doFollow) {
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

        for(int i=0;i<storyList.size();i++)
        {
            if(storyList.get(i).getDb().equals(id)){
                storyList.get(i).setIs_following(doFollow);
            }
        }
        if(recyclerView.getChildAt(firstVisibleItem)!=null&&storyList.get(firstVisibleItem).getDb().equals(id))
        {
            followButton=(TextView) recyclerView.getChildAt(firstVisibleItem).findViewById(R.id.followButton);
            if (!storyList.get(firstVisibleItem).is_following()) {
                followButton.setText("Follow");
                followButton.setTextColor(getResources().getColor(R.color.black));
                followButton.setBackgroundResource(R.drawable.follow);
            } else {
                followButton.setText(R.string.unfollow);
                followButton.setTextColor(getResources().getColor(R.color.white));
                followButton.setBackgroundResource(R.drawable.un_follow);
            }
        }
        if(recyclerView.getChildAt(lastVisibleItem)!=null&&storyList.get(lastVisibleItem).getDb().equals(id))
        {
            followButton=(TextView) recyclerView.getChildAt(lastVisibleItem).findViewById(R.id.followButton);
            if (!storyList.get(lastVisibleItem).is_following()) {
                followButton.setText("Follow");
                followButton.setTextColor(getResources().getColor(R.color.black));
                followButton.setBackgroundResource(R.drawable.follow);
            } else {
                followButton.setText(R.string.unfollow);
                followButton.setTextColor(getResources().getColor(R.color.white));
                followButton.setBackgroundResource(R.drawable.un_follow);
            }
        }
    }
    @Override
    public void openDialog(StoryData storyData,UserData userData){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        DialogFragment newFragment = DetailDialogFragment.newInstance(storyData,userData);
        newFragment.show(ft, "dialog");
    }
    public String readJsonFromLocal() {
        String outJson = null;
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            outJson = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return outJson;
    }


    @Override
    public void changeImage1(String year1, Boolean dofollow) {
        changeImage(year1,dofollow);
    }
}
