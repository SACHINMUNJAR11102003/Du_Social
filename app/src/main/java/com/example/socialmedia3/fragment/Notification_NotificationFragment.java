package com.example.socialmedia3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmedia3.Adapter.NotificationAdapter;
import com.example.socialmedia3.Model.NotificationModel;
import com.example.socialmedia3.R;

import java.util.ArrayList;

public class Notification_NotificationFragment extends Fragment {

    ArrayList<NotificationModel> notificationModelArrayList;

    public Notification_NotificationFragment() {
        // Required empty public constructor
    }

    public static Notification_NotificationFragment newInstance(String param1, String param2) {
        Notification_NotificationFragment fragment = new Notification_NotificationFragment();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notification__notification, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView_notification_notificationFragment);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        notificationModelArrayList=new ArrayList<>();
        String htmlText = "<b>Mr. X</b> mentioned you in a comment";
        notificationModelArrayList.add(new NotificationModel(R.drawable.kavya2, "07/01/2024", htmlText));
        notificationModelArrayList.add(new NotificationModel(R.drawable.kavya2, "07/01/2024", htmlText));
        notificationModelArrayList.add(new NotificationModel(R.drawable.kavya2, "07/01/2024", htmlText));

        NotificationAdapter notificationAdapter=new NotificationAdapter(notificationModelArrayList,getContext());
        recyclerView.setAdapter(notificationAdapter);
        return view;
    }
}