package com.example.socialmedia3.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialmedia3.Model.SendRequestModel;
import com.example.socialmedia3.Model.UserModel;
import com.example.socialmedia3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ProfileFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    TextView profession,institution,course,admissionYear;

    public static ImageView coverImage_profileFragment,profileImage_profileFragment;

    TextView editProfile_textview;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {







        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        Button connections=view.findViewById(R.id.connections_profileFragment);
        Button posts=view.findViewById(R.id.posts_profileFragment);
        TextView userName=view.findViewById(R.id.username_profileFragment);
        profession=view.findViewById(R.id.profession_profileFragment);
        institution=view.findViewById(R.id.institution_profileFragment);
        course=view.findViewById(R.id.course_profileFragment);
        admissionYear=view.findViewById(R.id.admissionYear_profileFragment);
        profileImage_profileFragment=view.findViewById(R.id.profilePicture_profileFragment);
        coverImage_profileFragment=view.findViewById(R.id.coverPhoto_profileFragment);
        editProfile_textview=view.findViewById(R.id.editProfile_profileFragment);






        firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            UserModel userModel=snapshot.getValue(UserModel.class);
                            Picasso.get()
                                    .load(userModel.getCoverPhoto())
                                    .placeholder(R.drawable.baseline_image_24)
                                    .into(coverImage_profileFragment);
                            Picasso.get()
                                    .load(userModel.getProfilePhoto())
                                    .placeholder(R.drawable.baseline_account_circle_24)
                                    .into(profileImage_profileFragment);
                            userName.setText(userModel.getName());
                            course.setText(userModel.getCourse());
                            admissionYear.setText("  "+userModel.getPassing_year());
                            profession.setText(userModel.getProfession());
                            institution.setText(userModel.getInstitution());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });






        editProfile_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new EditProfileFragment());
            }
        });


        connections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new ConnectionsFragment());
            }
        });

        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new PostsFragment());
            }
        });

        return view;
    }


    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_ProfileFragment,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}