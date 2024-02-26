package com.example.socialmedia3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmedia3.R;


public class Request_NotificationFragment extends Fragment {


    public Request_NotificationFragment() {
        // Required empty public constructor
    }

    public static Request_NotificationFragment newInstance(String param1, String param2) {
        Request_NotificationFragment fragment = new Request_NotificationFragment();
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
        return inflater.inflate(R.layout.fragment_request__notification, container, false);
    }
}