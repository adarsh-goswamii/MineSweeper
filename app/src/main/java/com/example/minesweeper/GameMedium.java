package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class GameMedium extends AppCompatActivity {

    private TableLayout table;
    private int lives;
    private ImageView emoji;
    private ImageButton reset;
    private TextView lives_remaining;
    private RelativeLayout result, relTable;
    private LottieAnimationView result_animation;
    public char[][] arr= new char[8][8];
    public static boolean won= false;
    private TextView result_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_medium);

        table= findViewById(R.id.table);
        relTable= findViewById(R.id.relative_table);
        emoji= findViewById(R.id.emoji);
        reset= findViewById(R.id.btn_restart);
        lives_remaining= findViewById(R.id.text_count_lives);
        result= findViewById(R.id.result);
        result_animation= findViewById(R.id.lottie_result);
        result_text= findViewById(R.id.text_loose_win);

        createGame();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createGame();
            }
        });

        final TableLayout layout= table;
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);

            if (child instanceof TableRow) {
                TableRow row = (TableRow) child;

                for (int x = 0; x < row.getChildCount(); x++) {
                    ImageView view = (ImageView)row.getChildAt(x);

                    final int[] ret= new int[]{i, x};
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lives= GameManager.onClick(arr, ret, lives);
                            makeChanges(arr);
                        }
                    });
                }
            }
        }
    }

    public void createGame()
    {
        result.setVisibility(View.GONE);
        relTable.setVisibility(View.VISIBLE);
        arr= new char[10][10];
        lives= 3;
        won= false;
        GameManager.buildGame(arr, 8, 10);
        makeChanges(arr);
    }

    public void makeChanges(char[][] arr)
    {
        for (int i = 0; i < table.getChildCount(); i++) {
            View child = table.getChildAt(i);

            if (child instanceof TableRow) {
                TableRow row = (TableRow) child;

                for (int x = 0; x < row.getChildCount(); x++) {
                    ImageView view = (ImageView)row.getChildAt(x);
                    Log.e("i", i+" "+x);
                    if(arr[i][x]== '1')
                        view.setImageResource(R.drawable.one);
                    else if(arr[i][x]== '2')
                        view.setImageResource(R.drawable.two);
                    else if(arr[i][x]== '3')
                        view.setImageResource(R.drawable.three);
                    else if(arr[i][x]== '4')
                        view.setImageResource(R.drawable.four);
                    else if(arr[i][x]== '5')
                        view.setImageResource(R.drawable.five);
                    else if(arr[i][x]== '6')
                        view.setImageResource(R.drawable.six);
                    else if(arr[i][x]== '7')
                        view.setImageResource(R.drawable.seven);
                    else if(arr[i][x]== '8')
                        view.setImageResource(R.drawable.eight);
                    else if(arr[i][x]== 'M' || arr[i][x]== 'E')
                        view.setImageResource(R.drawable.unrevealed);
                    else if(arr[i][x]== 'X')
                    {
                        view.setImageResource(R.drawable.bomb);
                        view.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                    else if(arr[i][x]== 'B')
                        view.setImageResource(R.drawable.blank_revealed);
                    else if(arr[i][x]== 'F')
                        view.setImageResource(R.drawable.flagged);
                    else if(arr[i][x]== 'Z')
                    {
                        view.setImageResource(R.drawable.bomb);
                        view.setBackgroundColor(getResources().getColor(R.color.grey));
                    }
                }
            }
        }

        if(lives== 3)
        {
            lives_remaining.setText("3");
            emoji.setImageResource(R.drawable.lives1);
        }
        else if (lives== 2)
        {
            lives_remaining.setText("2");
            emoji.setImageResource(R.drawable.lives2);
        }
        else if(lives== 1)
        {
            lives_remaining.setText("1");
            emoji.setImageResource(R.drawable.lives3);
        }
        else
        {
            lives_remaining.setText("0");
            result.setVisibility(View.VISIBLE);
            relTable.setVisibility(View.GONE);
            result_animation.setAnimation(R.raw.loose);
            result_text.setText("YOU LOOSE!");
            MainActivity.mloss++;
            MainActivity.mediumloss.setText(MainActivity.mloss+" LOSS");
        }

        if(won)
        {
            result.setVisibility(View.VISIBLE);
            relTable.setVisibility(View.GONE);
            result_animation.setAnimation(R.raw.win);
            result_text.setText("YOU WON!");
            MainActivity.mwin++;
            MainActivity.mediumwin.setText(MainActivity.mwin+" WINS");
        }
    }
}