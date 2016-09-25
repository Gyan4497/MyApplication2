package com.example.piyush.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "MainActivity";
    private SignInButton login;
    private TextView  name;
    public static final String ARG_FROM_MAIN = "arg";
    /* RequestCode for resolutions involving sign-in */
    private static final int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        login = (SignInButton)findViewById(R.id.sign_in_button);
        name = (TextView) findViewById(R.id.name);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount account=result.getSignInAccount();
            name.setText(account.getDisplayName()+'\n' + account.getEmail()+'\n'+account.getId()+'\n' + account.getPhotoUrl());

            ArrayList<String> details= new ArrayList<String>();
            details.add(account.getDisplayName());
            details.add(account.getEmail());

            Intent UserDetailIntent = new Intent(this,Profile_Details.class);
            UserDetailIntent.putExtra("arg",details);
            startActivity(UserDetailIntent);
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
