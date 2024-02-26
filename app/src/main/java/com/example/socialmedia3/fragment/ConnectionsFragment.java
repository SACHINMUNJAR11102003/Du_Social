package com.example.socialmedia3.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.socialmedia3.Adapter.ProfileConnectionAdapter;
import com.example.socialmedia3.Model.ConnectionsModel;
import com.example.socialmedia3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConnectionsFragment extends Fragment {
    ArrayList<ConnectionsModel> connectionsModelArrayList=new ArrayList<>();
    RecyclerView recyclerView;

    public ArrayList<ConnectionsModel> getConnectionsModelArrayList() {
        return connectionsModelArrayList;
    }

    public void setConnectionsModelArrayList(ConnectionsModel connectionsModelArrayList) {
        this.connectionsModelArrayList.add(connectionsModelArrayList);
    }

    public ConnectionsFragment() {
        // Required empty public constructor
    }

    public static ConnectionsFragment newInstance(String param1, String param2) {
        ConnectionsFragment fragment = new ConnectionsFragment();
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
        View view=inflater.inflate(R.layout.fragment_connections, container, false);
        recyclerView=view.findViewById(R.id.recyclerView_connectionFragment);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getUid())
                .child("connections")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                ConnectionsModel connectionsModel=dataSnapshot.getValue(ConnectionsModel.class);
                                setConnectionsModelArrayList(connectionsModel);
                            }

                            ProfileConnectionAdapter profileConnectionAdapter=new ProfileConnectionAdapter(getContext(),connectionsModelArrayList);
                            recyclerView.setAdapter(profileConnectionAdapter);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        AutoCompleteTextView autoCompleteTextView=view.findViewById(R.id.autoCompleteTextview_connectionsFragment);
        ArrayAdapter<ConnectionsModel> arrayAdapter=new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,connectionsModelArrayList);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);

        return view;
    }
}