package com.example.vaibhav.hypershopping;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hypertrack.lib.HyperTrack;
import com.hypertrack.lib.callbacks.HyperTrackCallback;
import com.hypertrack.lib.models.ErrorResponse;
import com.hypertrack.lib.models.SuccessResponse;
import com.hypertrack.lib.models.User;
import com.hypertrack.lib.models.UserParams;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText user, phone, lookup;

        user = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
        lookup = (EditText) findViewById(R.id.editText3);
        Button submit = (Button) findViewById(R.id.button2);
        HyperTrack.initialize(this, "pk_3f3c8ec252a417a10707f6457bb7018601f89036");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserParams userParams = new UserParams().setName(String.valueOf(user))
                        .setPhone(phone.getText().toString())
                        .setLookupId(phone.getText().toString());

// This API will create a new user only if none exists already for the given lookup_id
                HyperTrack.getOrCreateUser(userParams, new HyperTrackCallback() {

                    @Override
                    public void onSuccess(@NonNull SuccessResponse response) {
                        if (response.getResponseObject() != null) {
                            User user = (User) response.getResponseObject();
                            // Handle user_id, if needed
                            String userId = user.getId();

                            Toast.makeText(LoginActivity.this, "User created successfully with UserId: " + userId, Toast.LENGTH_SHORT).show();
                            onUserLoginSuccess();
                        }
                    }

                    @Override
                    public void onError(@NonNull ErrorResponse errorResponse) {
                        // Handle createUser error here
                        Toast.makeText(LoginActivity.this, errorResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }

    /**
     * Call this method when user has successfully logged in
     */
    private void onUserLoginSuccess() {
        HyperTrack.startTracking(new HyperTrackCallback() {
            @Override
            public void onSuccess(@NonNull SuccessResponse successResponse) {
                // Hide Login Button loader
                //submit.setVisibility(View.GONE);

                Toast.makeText(LoginActivity.this, R.string.login_success_msg,
                        Toast.LENGTH_SHORT).show();

                // Start User Session by starting MainActivity
                Intent mainActivityIntent = new Intent(
                        LoginActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainActivityIntent);
                finish();
            }

            @Override
            public void onError(@NonNull ErrorResponse errorResponse) {
                // Hide Login Button loader
                //loginBtnLoader.setVisibility(View.GONE);

                Toast.makeText(LoginActivity.this, R.string.login_error_msg
                                + " " + errorResponse.getErrorMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
