package com.example.socialmedia3.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.socialmedia3.Model.PostsModel;
import com.example.socialmedia3.Model.SendRequestModel;
import com.example.socialmedia3.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShareSomethingFragment extends Fragment {

    ImageView postingImage_imageview;
    Button postingButton;
    EditText aboutPostingImage;
    String formattedDateTime;

    public ShareSomethingFragment() {
        // Required empty public constructor
    }

    public static ShareSomethingFragment newInstance(String param1, String param2) {
        ShareSomethingFragment fragment = new ShareSomethingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_something, container, false);

        postingImage_imageview = view.findViewById(R.id.input_postingImage_imageview);
        postingButton = view.findViewById(R.id.postingImage_button);
        aboutPostingImage = view.findViewById(R.id.input_aboutPostingImage);

        postingImage_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 10);
            }
        });

        return view;
    }


    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentDateTime = LocalDateTime.now();
        }
        DateTimeFormatter formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return currentDateTime.format(formatter);
        }
        return currentDateTime.toString();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            formattedDateTime = getCurrentDateTime();
            String postingImage = uri.toString();

            postingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (formattedDateTime != null) {
                        FirebaseDatabase.getInstance().getReference()
                                .child("Users")
                                .child(FirebaseAuth.getInstance().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            PostsModel postsModel = snapshot.getValue(PostsModel.class);
                                            String name = postsModel.getName();
                                            String userID = postsModel.getUserID();
                                            String profilePhoto = postsModel.getProfilePhoto();

                                            PostsModel postsModel1 = new PostsModel(
                                                    name,
                                                    postingImage,
                                                    aboutPostingImage.getText().toString(),
                                                    profilePhoto,
                                                    formattedDateTime
                                            );

                                            String postId = FirebaseDatabase.getInstance().getReference()
                                                    .child("Users").child(userID)
                                                    .child("myposts").push().getKey();

                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("Users").child(userID)
                                                    .child("myposts").child(postId).setValue(postsModel1);

                                            PostsModel postsModel2 = new PostsModel(
                                                    name,
                                                    aboutPostingImage.getText().toString(),
                                                    formattedDateTime,
                                                    postingImage,
                                                    profilePhoto,
                                                    userID
                                            );

                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("allPosts").child(postId)
                                                    .setValue(postsModel2);

                                            Toast.makeText(getContext(), "Post uploaded successfully", Toast.LENGTH_SHORT).show();

                                            final StorageReference storageReference=FirebaseStorage.getInstance().getReference()
                                                            .child("allPosts")
                                                                    .child(FirebaseAuth.getInstance().getUid());

                                            storageReference.putFile(uri);

                                        }

                                    }

                                    @Override

                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }

                                    ;
                                });


                        postingImage_imageview.setImageURI(uri);
                    }

                }


            });

        }
    }
}