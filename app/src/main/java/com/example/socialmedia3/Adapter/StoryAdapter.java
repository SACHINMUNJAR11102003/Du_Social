package com.example.socialmedia3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia3.Model.StoryModel;
import com.example.socialmedia3.R;


import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.Viewholder>{

    ArrayList<StoryModel> storyModelArrayList;
    Context context;

    public StoryAdapter(ArrayList<StoryModel> storyModelArrayList, Context context) {
        this.storyModelArrayList = storyModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.story_view,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        StoryModel storyModel=storyModelArrayList.get(position);
        holder.profileImage.setImageResource(storyModel.getProfileImage());
        holder.userName.setText(storyModel.getUserName());
        holder.storyImage.setImageResource(storyModel.getStoryImage());
    }

    @Override
    public int getItemCount() {
        return storyModelArrayList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView profileImage,storyImage;
        TextView userName;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            profileImage=itemView.findViewById(R.id.storyProfileImage);
            storyImage=itemView.findViewById(R.id.storyImage);
            userName=itemView.findViewById(R.id.storyUsername);
        }
    }
}