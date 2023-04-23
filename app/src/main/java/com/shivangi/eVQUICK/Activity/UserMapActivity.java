package com.shivangi.eVQUICK.Activity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivangi.eVQUICK.R;
import com.shivangi.eVQUICK.UserModel;
import com.shivangi.eVQUICK.databinding.ActivityUserMapsBinding;
import com.shivangi.eVQUICK.databinding.NavDrawerLayoutBinding;
import com.shivangi.eVQUICK.databinding.ToolbarLayoutBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserMapActivity extends AppCompatActivity {

    private NavDrawerLayoutBinding navDrawerLayoutBinding;
    private ActivityUserMapsBinding activityUserMapsBinding;
    private ToolbarLayoutBinding toolbarLayoutBinding;
    private FirebaseAuth firebaseAuth;
    private CircleImageView imgHeader;
    private TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navDrawerLayoutBinding = NavDrawerLayoutBinding.inflate(getLayoutInflater());
        setContentView(navDrawerLayoutBinding.getRoot());
        activityUserMapsBinding = navDrawerLayoutBinding.userMapsActivity;
        toolbarLayoutBinding = activityUserMapsBinding.toolbar;

        setSupportActionBar(toolbarLayoutBinding.toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                navDrawerLayoutBinding.navDrawer,
                toolbarLayoutBinding.toolbar,
                R.string.open_navigation_drawer,
                R.string.close_navigation_drawer
        );

        navDrawerLayoutBinding.navDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainer);
        NavigationUI.setupWithNavController(
                navDrawerLayoutBinding.navigationView,
                navController
        );

        View headerLayout = navDrawerLayoutBinding.navigationView.getHeaderView(0);

        imgHeader = headerLayout.findViewById(R.id.imgHeader);
        txtName = headerLayout.findViewById(R.id.txtHeaderName);
        txtEmail = headerLayout.findViewById(R.id.txtHeaderEmail);

        getUserData();


    }

    @Override
    public void onBackPressed() {

        if (navDrawerLayoutBinding.navDrawer.isDrawerOpen(GravityCompat.START))
            navDrawerLayoutBinding.navDrawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void getUserData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                .child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    UserModel userModel = snapshot.getValue(UserModel.class);
                    Glide.with(UserMapActivity.this).load(userModel.getImage()).into(imgHeader);
                    txtName.setText(userModel.getUsername());
                    txtEmail.setText(userModel.getEmail());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}