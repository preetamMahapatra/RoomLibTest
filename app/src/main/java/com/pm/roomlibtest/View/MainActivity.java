package com.pm.roomlibtest.View;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.databinding.DataBindingUtil;
import com.pm.roomlibtest.DataBinding.Listener;
import com.pm.roomlibtest.R;
import com.pm.roomlibtest.RoomDB.LoginTable;
import com.pm.roomlibtest.ViewModel.LoginViewModel;
import com.pm.roomlibtest.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Listener {

    private LoginViewModel loginViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setClickListener((MainActivity) this);


        loginViewModel = ViewModelProviders.of(MainActivity.this).get(LoginViewModel.class);

        loginViewModel.getGetAllData().observe(this, new Observer<List<LoginTable>>() {
            @SuppressLint("NewApi")
            @Override
            public void onChanged(@Nullable List<LoginTable> data) {

                try {
                    binding.lblEmailAnswer.setText((Objects.requireNonNull(data).get(0).getEmail()));
                    binding.lblPasswordAnswer.setText((Objects.requireNonNull(data.get(0).getPassword())));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {

        String strEmail = binding.txtEmailAddress.getText().toString().trim();
        String strPassword = binding.txtPassword.getText().toString().trim();

        LoginTable data = new LoginTable();

        if (TextUtils.isEmpty(strEmail)) {
            binding.txtEmailAddress.setError("Please Enter Your E-mail Address");
        }
        else if (TextUtils.isEmpty(strPassword)) {
            binding.txtPassword.setError("Please Enter Your Password");
        }
        else {

            data.setEmail(strEmail);
            data.setPassword(strPassword);
            loginViewModel.insert(data);

        }

    }
}
