package com.ahmedbassiouny.test.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ahmedbassiouny.test.R;
import com.ahmedbassiouny.test.model.model.User;
import com.ahmedbassiouny.test.view_model.SearchViewModel;

public class SearchActivity extends AppCompatActivity {

    // views
    private TextView tvId,tvName, tvJobTitle, tvAge, tvGender;
    private ProgressBar progressBar;
    private Button btnSearch;
    private EditText etUserId;


    // variable
    private SearchViewModel viewModel;
    private String[] genderType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        views();
        handleClick();
        genderType = getResources().getStringArray(R.array.gender);
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        setObservers();

        long id =  getIntent().getLongExtra("data",0);
        if (id > 0) {
            etUserId.setText(String.valueOf(id));
        }

    }

    private void setObservers() {
        viewModel.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(SearchActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                btnSearch.setEnabled(!aBoolean);
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        viewModel.success.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                tvId.setText(String.format("%s : %s",getString(R.string.id),user.getId()));
                tvName.setText(String.format("%s : %s",getString(R.string.name),user.getName()));
                tvJobTitle.setText(String.format("%s : %s",getString(R.string.job_title),user.getJobTitle()));
                tvAge.setText(String.format("%s : %s",getString(R.string.age),user.getAge()));
                tvGender.setText(String.format("%s : %s",getString(R.string.gender),genderType[user.getGender()]));
            }
        });
    }

    private void views() {
        tvId = findViewById(R.id.tvId);
        tvName = findViewById(R.id.tvName);
        tvJobTitle = findViewById(R.id.tvJobTitle);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        progressBar = findViewById(R.id.progressBar);
        btnSearch = findViewById(R.id.btnSearch);
        etUserId = findViewById(R.id.etUserId);
    }

    void handleClick() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getUser(etUserId.getText().toString());
            }
        });
    }
}
