package com.example.socialmedia3.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.socialmedia3.Adapter.PostsAdapter;
import com.example.socialmedia3.Adapter.StoryAdapter;
import com.example.socialmedia3.Model.PostsModel;
import com.example.socialmedia3.Model.StoryModel;
import com.example.socialmedia3.Model.UserModel;
import com.example.socialmedia3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    ImageView user_profileImage;
    Button share_something_new;

    ArrayList<PostsModel> postsModelArrayList;
    ArrayList<StoryModel> storyModelArrayList;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ArrayList<PostsModel> getPostsModelArrayList() {
        return postsModelArrayList;
    }

    public void setPostsModelArrayList(PostsModel postsModel) {
        this.postsModelArrayList.add(postsModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int likes=0;
        int comments=0;

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        share_something_new=view.findViewById(R.id.share_something_new);
        user_profileImage=view.findViewById(R.id.userProfileImage_homeFragment);
        RecyclerView postRecyclerView=view.findViewById(R.id.postRecyclerview);
        RecyclerView storyRecyclerView=view.findViewById(R.id.storyRecyclerView);


        firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            UserModel userModel=snapshot.getValue(UserModel.class);
                            Picasso.get()
                                    .load(userModel.getProfilePhoto())
                                    .placeholder(R.drawable.baseline_account_circle_24)
                                    .into(user_profileImage);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });








        share_something_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment2(new ShareSomethingFragment());
            }
        });







        storyModelArrayList=new ArrayList<>();
        storyModelArrayList.add(new StoryModel(R.drawable.kavya2, R.drawable.kavya2,"Princess"));
        storyModelArrayList.add(new StoryModel(R.drawable.mom,R.drawable.mom,"Mom"));
        storyModelArrayList.add(new StoryModel(R.drawable.pops,R.drawable.pops,"Pops"));
        storyModelArrayList.add(new StoryModel(R.drawable.dadu,R.drawable.dadu,"Dadu"));
        storyModelArrayList.add(new StoryModel(R.drawable.devender,R.drawable.devender,"Big B"));
        storyModelArrayList.add(new StoryModel(R.drawable.suman,R.drawable.suman,"Bua Ji"));
        storyModelArrayList.add(new StoryModel(R.drawable.neetu,R.drawable.neetu,"Sister"));
        storyModelArrayList.add(new StoryModel(R.drawable.anita,R.drawable.anita,"Bua Ji"));
        storyModelArrayList.add(new StoryModel(R.drawable.sachin,R.drawable.sachin,"Sachin Gujjar"));

        LinearLayoutManager storyLinearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        storyRecyclerView.setLayoutManager(storyLinearLayoutManager);

        StoryAdapter storyAdapter=new StoryAdapter(storyModelArrayList,getContext());
        storyRecyclerView.setAdapter(storyAdapter);


        postsModelArrayList=new ArrayList<>();



        LinearLayoutManager postLinearLayoutManager=new LinearLayoutManager(getContext());
        postRecyclerView.setLayoutManager(postLinearLayoutManager);

        FirebaseDatabase.getInstance().getReference()
                .child("allPosts")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                PostsModel postsModel=dataSnapshot.getValue(PostsModel.class);
                                setPostsModelArrayList(postsModel);
                            }

                            PostsAdapter postsAdapter=new PostsAdapter(postsModelArrayList,getContext());
                            postRecyclerView.setAdapter(postsAdapter);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        return view;
    }


    public void loadFragment2(Fragment fragment){
        FragmentManager fragmentManager=getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMainActivity,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}