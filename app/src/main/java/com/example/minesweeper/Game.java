package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.LinkedList;
import java.util.Queue;

public class Game extends AppCompatActivity {

    private TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        table= findViewById(R.id.table);

        final char[][] arr= new char[8][8];
        GameManager.buildGame(arr, 6);

        StringBuilder br= new StringBuilder();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
                br.append(arr[i][j]+" ");
            br.append("\n");
        }

        Log.e("matrix", br.toString());

        makeChanges(arr);

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
                            GameManager.onClick(arr, ret);
                            makeChanges(arr);
                        }
                    });
                }
            }
        }
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
    }


}