package com.depex.eatasmuch.user.screens;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.depex.eatasmuch.user.GlideApp;
import com.depex.eatasmuch.user.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity {


    @BindView(R.id.profile_pic)
    ImageView profilePic;

    @BindView(R.id.myprofile_camera)
    ImageView profileEditPic;

    //@BindView(R.id.)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("My Profile");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfileActivity.super.onBackPressed();
            }
        });
        String imageUrl="";
        GlideApp.with(this).load(imageUrl).placeholder(R.drawable.avatar_img).circleCrop().into(profilePic);

    }

}
