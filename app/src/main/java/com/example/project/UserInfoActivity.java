package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.project.Models.Role;
import com.example.project.Models.Token;
import com.example.project.Models.UserInfo;
import com.example.project.databinding.ActivityMainBinding;
import com.example.project.databinding.ActivityUserInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {
    private ActivityUserInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Network.getInstance().getApi().info("Bearer " + Network.getToken()).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.code() != 200){
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.email.setText(response.body().getEmail());
                binding.telegram.setText(response.body().getTelegram());
                binding.roles.setText("Роли\n" + response.body().getRoles().stream().map(u -> u.getValue()).collect(Collectors.joining("\n")));

                boolean isAdminRole = false;
                for (Role role : response.body().getRoles()){
                    if(role.getValue().equals("ADMIN")){
                        isAdminRole = true;
                    }
                }
                if(isAdminRole){
                    binding.inputId.setVisibility(View.VISIBLE);
                    binding.buttonFindUser.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonFindUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Network.getInstance().getApi().userById("Bearer " + Network.getToken(), Integer.parseInt(binding.inputId.getText().toString())).enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        if(response.code() != 200){
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        binding.email.setText(response.body().getEmail());
                        binding.telegram.setText(response.body().getTelegram());
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        bottomSheetBehavior.setPeekHeight(340);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        bottomSheetBehavior.setHideable(false);
    }
}