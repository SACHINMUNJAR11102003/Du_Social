package com.example.socialmedia3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia3.Model.PostsModel;
import com.example.socialmedia3.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.Viewholder>{

    public ArrayList<PostsModel> postsModelArrayList;
    Context context;

    public PostsAdapter(ArrayList<PostsModel> postsModelArrayList, Context context) {
        this.postsModelArrayList = postsModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.posts_view,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        PostsModel postsModel=postsModelArrayList.get(position);
        Picasso.get()
                        .load(postsModel.getProfilePhoto())
                .placeholder(R.drawable.baseline_account_circle_24)
                .into(holder.profileImage);
        Picasso.get()
                        .load(postsModel.getPostingImage())
                                .placeholder(R.drawable.baseline_image_24)
                                        .into(holder.postImage);

        holder.userName.setText(postsModel.getName());
        holder.about.setText(postsModel.getAbout());
        //holder.comments.setText(String.valueOf(postsModel.getComments()+" comments"));
        //holder.likes.setText(String.valueOf(postsModel.getLikes())+" likes");
        holder.date.setText(postsModel.getDate());

    }

    @Override
    public int getItemCount() {
        return postsModelArrayList.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView profileImage,postImage;
        TextView userName,likes,comments,about,date;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            profileImage=itemView.findViewById(R.id.profileImage_homeFragment);
            postImage=itemView.findViewById(R.id.postImage);
            userName=itemView.findViewById(R.id.userName_homeFragment);
            likes=itemView.findViewById(R.id.post_likes);
            comments=itemView.findViewById(R.id.post_comments);
            about=itemView.findViewById(R.id.post_about);
            date=itemView.findViewById(R.id.post_date);
        }
    }
}
