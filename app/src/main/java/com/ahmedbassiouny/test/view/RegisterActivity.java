package com.ahmedbassiouny.test.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ahmedbassiouny.test.R;
import com.ahmedbassiouny.test.view_model.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel viewModel;
    private TextInputEditText etName, etJobTitle, etAge;
    private Spinner spGender;
    private ProgressBar progressBar;
    private Button btnSave,btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        views();
        handleClick();
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        setObservers();


    }

    private void handleClick() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setUser(etName.getText().toString(), etJobTitle.getText().toString(), etAge.getText().toString(), spGender.getSelectedItemPosition());
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,SearchActivity.class));
            }
        });
    }

    private void views() {
        etName = findViewById(R.id.etName);
        etJobTitle = findViewById(R.id.etJobTitle);
        etAge = findViewById(R.id.etAge);
        spGender = findViewById(R.id.spGender);
        progressBar = findViewById(R.id.progressBar);
        btnSave = findViewById(R.id.btnSave);
        btnSearch = findViewById(R.id.btnSearch);
    }

    private void setObservers() {
        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                btnSave.setEnabled(!aBoolean);
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    // hide
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        viewModel.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.success.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long along) {
                Toast.makeText(RegisterActivity.this, getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(RegisterActivity.this,SearchActivity.class);
                intent.putExtra("data",along);
                startActivity(intent);
            }
        });
    }
}
