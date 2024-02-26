package com.example.socialmedia3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import com.example.socialmedia3.Model.UserModel;
import com.example.socialmedia3.fragment.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StartActivity extends AppCompatActivity {
    Handler handler,handler1,handler3,handler4;

    String institutions,passing_year,profession;

    public String getPassing_year() {
        return passing_year;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setPassing_year(String passing_year) {
        this.passing_year = passing_year;
    }

    public String getInstitutions() {
        return institutions;
    }

    public void setInstitutions(String institutions) {
        this.institutions = institutions;
    }

    private String verificationId;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseCurrentUser;
    FirebaseStorage firebaseStorage;
    Spinner institutionSpinner,passing_yearSpinner,professionSpinner;
    ArrayList<String> arrInstitutions,arrPassing_year,arrProfessions;

    private static final String GMAIL_REGEX_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@gmail.com$";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_start);



        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseCurrentUser=firebaseAuth.getCurrentUser();
        firebaseStorage=FirebaseStorage.getInstance();

        if (firebaseCurrentUser!=null){
            Intent intent=new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
        }

        LinearLayout ll_duLogo=findViewById(R.id.ll_duLogo);
        LinearLayout ll_login=findViewById(R.id.ll_login);
        LinearLayout ll_createAccount=findViewById(R.id.ll_createaccount);

        EditText input_username=findViewById(R.id.input_userName_signUP);
        EditText input_email_login=findViewById(R.id.input_email_login);
        EditText input_password_login=findViewById(R.id.input_password_login);
        EditText input_email_signUP=findViewById(R.id.input_email_signup);
        EditText input_password_signUP=findViewById(R.id.input_password_sign);
        EditText input_course=findViewById(R.id.input_course_sign);
        Button logIN=findViewById(R.id.log_in_button);
        Button signUP=findViewById(R.id.sig_in_button);
        Button submit=findViewById(R.id.submit_button);


        institutionSpinner=findViewById(R.id.institutionSpinner);
        passing_yearSpinner=findViewById(R.id.passing_yearSpiner);
        professionSpinner=findViewById(R.id.professionSpinner);


        ObjectAnimator scaleX = ObjectAnimator.ofFloat(ll_duLogo, View.SCALE_X, 0.001f, 2.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ll_duLogo, View.SCALE_Y, 0.001f, 2.0f);
        AnimatorSet scaleAnimatorSet = new AnimatorSet();
        scaleAnimatorSet.playTogether(scaleX, scaleY);
        scaleAnimatorSet.setDuration(4000);
        scaleAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimatorSet.start();


        handler1=new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                ll_login.setVisibility(View.VISIBLE);
            }
        },8000);
        handler3=new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                ll_duLogo.setVisibility(View.GONE);
            }
        },8000);




        arrProfessions=new ArrayList<>();
        arrProfessions.add("Student");
        arrProfessions.add("Assistant Professor");
        arrProfessions.add("Professor");
        arrProfessions.add("Director");
        arrProfessions.add("Principal");
        arrProfessions.add("Dean");
        arrProfessions.add("Other working Staff");

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrProfessions);
        professionSpinner.setAdapter(adapter);

        arrInstitutions=new ArrayList<>();
        arrInstitutions.add("Cluster Innovation Centre");
        arrInstitutions.add("Sri Ram College of Commerce");
        arrInstitutions.add("Faculty of Arts");
        arrInstitutions.add("Faculty of Technology");

        ArrayAdapter<String> adapter1=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrInstitutions);
        institutionSpinner.setAdapter(adapter1);

        arrPassing_year=new ArrayList<>();
        arrPassing_year.add("2022");
        arrPassing_year.add("2023");
        arrPassing_year.add("2021");
        arrPassing_year.add("2020");

        ArrayAdapter<String> adapter2=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrPassing_year);
        passing_yearSpinner.setAdapter(adapter2);


        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_login.setVisibility(View.GONE);
                ll_createAccount.setVisibility(View.VISIBLE);
            }
        });

        institutionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setInstitutions(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        professionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setProfession(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        passing_yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setPassing_year(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        logIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=input_email_login.getText().toString();
                String password=input_password_login.getText().toString();
                if (TextUtils.isEmpty(password)){
                    input_username.setError("Please Enter Password");
                } else if (TextUtils.isEmpty(email)) {
                    input_email_signUP.setError("Please Enter E Mail");
                }
                else if (!isGmailAddress(email)) {
                    input_email_signUP.setError("Please Enter a valid E Mail");
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent=new Intent(StartActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=input_username.getText().toString();
                String email=input_email_signUP.getText().toString();
                String password=input_password_signUP.getText().toString();
                String institution_user=getInstitutions().toString();
                String passing_year_user=getPassing_year().toString();
                String course=input_course.getText().toString();
                String profession=getProfession().toString();

                if (TextUtils.isEmpty(username)){
                    input_username.setError("Please Enter Name");
                } else if (TextUtils.isEmpty(email)) {
                    input_email_signUP.setError("Please Enter E Mail");
                }
                else if (TextUtils.isEmpty(password)) {
                    input_password_signUP.setError("Please Set Password");
                } else if (TextUtils.isEmpty(course)) {
                    input_course.setError("Please Enter your Course");
                } else if (TextUtils.isEmpty(institution_user)) {
                    Toast.makeText(StartActivity.this, "Please Choose Institution", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(passing_year_user)) {
                    Toast.makeText(StartActivity.this, "Please Choose Admission Year", Toast.LENGTH_SHORT).show();
                } else if (!isGmailAddress(email)) {
                    input_email_signUP.setError("Please Enter a valid E Mail");
                } else if (TextUtils.isEmpty(profession)) {
                    Toast.makeText(StartActivity.this, "Please Choose Profession", Toast.LENGTH_SHORT).show();
                } else {

                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        String id=task.getResult().getUser().getUid();
                                        UserModel userModel=new UserModel(email,password,username,institution_user,passing_year_user,profession,course,id);
                                        firebaseDatabase.getReference().child("Users").child(id).setValue(userModel);
                                        Toast.makeText(StartActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(StartActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(StartActivity.this, "an eror occured", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });


    }
    public static boolean isGmailAddress(String email) {
        Pattern pattern = Pattern.compile(GMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}