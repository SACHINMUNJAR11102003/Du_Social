package com.example.socialmedia3.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.socialmedia3.Adapter.GetRequestAdapter;
import com.example.socialmedia3.Adapter.SendRequestAdapter;
import com.example.socialmedia3.Model.SendRequestModel;
import com.example.socialmedia3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MakeNewConnectionFragment extends Fragment {
    ArrayList<SendRequestModel> allUsersArraylist=new ArrayList<>();
    ArrayList<SendRequestModel> allConnectionArraylist=new ArrayList<>();


    public ArrayList<SendRequestModel> getAllUsersArraylist() {
        return allUsersArraylist;
    }

    public ArrayList<SendRequestModel> getAllConnectionArraylist() {
        return allConnectionArraylist;
    }

    public void setAllConnectionArraylist(SendRequestModel sendRequestModel) {
        this.allConnectionArraylist.add(sendRequestModel);
    }

    public void setAllUsersArraylist(SendRequestModel sendRequestModel) {
        this.allUsersArraylist.add(sendRequestModel);
    }

    RecyclerView getRequestRecyclerview,sendRequestRecyclerview;

    public MakeNewConnectionFragment() {
        // Required empty public constructor
    }

    public static MakeNewConnectionFragment newInstance(String param1, String param2) {
        MakeNewConnectionFragment fragment = new MakeNewConnectionFragment();
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
        View view=inflater.inflate(R.layout.fragment_make_new_connection, container, false);
        getRequestRecyclerview=view.findViewById(R.id.getRequest_connection_recyclerveiw);
        sendRequestRecyclerview=view.findViewById(R.id.sendRequest_connection_recyclerveiw);


        LinearLayoutManager linearLayoutManager_get=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        getRequestRecyclerview.setLayoutManager(linearLayoutManager_get);

        LinearLayoutManager linearLayoutManager_send=new LinearLayoutManager(getContext());
        sendRequestRecyclerview.setLayoutManager(linearLayoutManager_send);


        FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getUid())
                .child("connections").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot connectionSnapshot:snapshot.getChildren()){
                                SendRequestModel sendRequestModel=connectionSnapshot.getValue(SendRequestModel.class);
                                setAllConnectionArraylist(sendRequestModel);
                            }

                            GetRequestAdapter getRequestAdapter=new GetRequestAdapter(getContext(),allConnectionArraylist);
                            getRequestRecyclerview.setAdapter(getRequestAdapter);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot userSnapshot:snapshot.getChildren()){
                        SendRequestModel sendRequestModel=userSnapshot.getValue(SendRequestModel.class);
                        setAllUsersArraylist(sendRequestModel);

                    }
                    SendRequestAdapter sendRequestAdapter=new SendRequestAdapter(getContext(),getAllUsersArraylist());
                    sendRequestRecyclerview.setAdapter(sendRequestAdapter);

                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "failed 2", Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }
}