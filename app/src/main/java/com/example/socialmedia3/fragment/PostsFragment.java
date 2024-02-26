package com.example.socialmedia3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.socialmedia3.Adapter.PostsAdapter;
import com.example.socialmedia3.Model.PostsModel;
import com.example.socialmedia3.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PostsFragment extends Fragment {
    ArrayList<PostsModel> postsModelArrayList;
    public PostsFragment() {
        // Required empty public constructor
    }

    public static PostsFragment newInstance(String param1, String param2) {
        PostsFragment fragment = new PostsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        postsModelArrayList=new ArrayList<>();


        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_posts, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView_postsFragment);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);



        PostsAdapter postsAdapter=new PostsAdapter(postsModelArrayList,getContext());
        recyclerView.setAdapter(postsAdapter);

        return view;
    }
}