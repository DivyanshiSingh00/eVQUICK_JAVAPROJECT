
package com.shivangi.eVQUICK.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.shivangi.eVQUICK.R;

public class MainActivity extends AppCompatActivity {
    private Button mAdmin, mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdmin = (Button) findViewById(R.id.Admin);
        mUser = (Button) findViewById(R.id.User);

        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, UserLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }
}


