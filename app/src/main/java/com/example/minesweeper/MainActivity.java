package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageButton;

public class MainActivity extends AppCompatActivity {

    private NeumorphImageButton mail, github, linkedin;
    private NeumorphButton easy, medium, hard;
    private TextView easywin, easyloss, mediumwin, mediumloss, hardwin, hardloss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Connect.class);
                intent.putExtra("url", "url");
                startActivity(intent);
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Connect.class);
                intent.putExtra("url", "https://github.com/Adarsh-Goswamii");
                startActivity(intent);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Connect.class);
                intent.putExtra("url", "https://www.linkedin.com/in/adarsh-goswami/");
                startActivity(intent);
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Game.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        mail= findViewById(R.id.image_gmail);
        github= findViewById(R.id.image_github);
        linkedin= findViewById(R.id.image_linkedin);

        easy= findViewById(R.id.btn_easy_play);
        medium= findViewById(R.id.btn_medium_play);
        hard= findViewById(R.id.btn_hard_play);

        easywin= findViewById(R.id.text_easy_win);
        easyloss= findViewById(R.id.text_easy_loss);
        mediumwin= findViewById(R.id.text_medium_loss);
        mediumloss= findViewById(R.id.text_medium_win);
        hardwin= findViewById(R.id.text_hard_win);
        hardloss= findViewById(R.id.text_hard_loss);
    }


}