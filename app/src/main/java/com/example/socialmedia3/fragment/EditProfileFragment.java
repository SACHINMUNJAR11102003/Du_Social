package com.example.socialmedia3.fragment;

import static com.example.socialmedia3.fragment.ProfileFragment.coverImage_profileFragment;
import static com.example.socialmedia3.fragment.ProfileFragment.profileImage_profileFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.socialmedia3.Model.UserModel;
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
import com.squareup.picasso.Picasso;

public class EditProfileFragment extends Fragment {

    FirebaseStorage firebaseStorage;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    Button edit_profileImage,edit_coverImage;
    ImageButton edit_name,edit_profession,edit_academicStatus,changePassword;
    EditText input_name,input_profession,input_academicStatus,input_currentPassword,input_newPassword;
    public EditProfileFragment() {
        // Required empty public constructor
    }
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {











        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        edit_profileImage=view.findViewById(R.id.edit_profilePhoto_editprofileFragment);
        edit_coverImage=view.findViewById(R.id.edit_coverPhoto_editprofileFragment);
        edit_name=view.findViewById(R.id.input_editName_button_profilrFragment);
        edit_profession=view.findViewById(R.id.input_editProfession_button_profilrFragment);
        edit_academicStatus=view.findViewById(R.id.input_editAcademic_status_button_profilrFragment);
        input_name=view.findViewById(R.id.input_editName_edtext_profilrFragment);
        input_profession=view.findViewById(R.id.input_profession_edtext_profilrFragment);
        input_academicStatus=view.findViewById(R.id.input_academicstatus_edtext_profilrFragment);
        input_currentPassword=view.findViewById(R.id.input_currentPassword_edtext_profilrFragment);
        input_newPassword=view.findViewById(R.id.input_newPassword_edtext_profilrFragment);
        changePassword=view.findViewById(R.id.change__button_newPassword_profilrFragment);


        edit_coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,11);
            }
        });

        edit_profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,12);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==11){
            if (data.getData()!=null){
                Uri uri=data.getData();

                final StorageReference storageReference=firebaseStorage.getReference().child("cover_photo")
                        .child(FirebaseAuth.getInstance().getUid());
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Cover Photo Changed", Toast.LENGTH_SHORT).show();
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                firebaseDatabase.getReference()
                                        .child("Users")
                                        .child(FirebaseAuth.getInstance().getUid())
                                        .child("coverPhoto")
                                        .setValue(uri.toString());
                                Toast.makeText(getContext(), "Achieved", Toast.LENGTH_SHORT).show();
                            }
                        });
                        coverImage_profileFragment.setImageURI(uri);
                    }
                });

            }
        } else if (requestCode==12) {
            if (data.getData()!=null){
                Uri uri=data.getData();

                final StorageReference storageReference=firebaseStorage.getReference().child("profile_photo")
                        .child(FirebaseAuth.getInstance().getUid());
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Profile Photo Changed", Toast.LENGTH_SHORT).show();
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).
                                        child("profilePhoto").setValue(uri.toString());

                                profileImage_profileFragment.setImageURI(uri);
                            }
                        });
                    }
                });

            }
        }
    }
}