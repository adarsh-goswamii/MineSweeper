package com.example.minesweeper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.LinkedList;
import java.util.Queue;

public class GameManager
{
    private static char[] choose={'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'M', 'M'};
    public static void buildGame(char[][] arr, int minMines, int maxMines)
    {
        int countMines= 0;
        for(int i=0;i<arr.length;i++)
            for(int j=0;j<arr[0].length;j++)
            {
                arr[i][j]= choose[(int)(Math.random()*10)];
                if(arr[i][j]== 'M')
                    countMines++;
            }

        if(countMines<minMines || countMines>maxMines)
            buildGame(arr, minMines, maxMines);
    }

    public static int onClick(char[][] board, int[] click, int lives)
    {
        if(board[click[0]][click[1]]!= 'E' && board[click[0]][click[1]]!= 'M' ) return lives;
        if(board[click[0]][click[1]]=='M')
        {
            board[click[0]][click[1]]= 'X';
            lives--;
            return lives;
        }

        boolean[][] visited=new boolean[board.length][board[0].length];
        Queue<Integer> q=new LinkedList<Integer>();
        q.add(click[0]);
        q.add(click[1]);
        visited[click[0]][click[1]]= true;

        while(!q.isEmpty())
        {
            int size= q.size();

            for(int ii=0;ii<size/2;ii++)
            {
                int row= q.poll();
                int col= q.poll();

                int count=0;
                for(int i=Math.max(0, row-1);i<= Math.min(board.length-1, row+1);i++)
                {
                    for(int j=Math.max(0, col-1); j<= Math.min(board[0].length-1, col+1);j++)
                    {
                        if(board[i][j]== 'M'|| board[i][j]== 'X')
                            count++;
                    }
                }

                if(board[row][col]== 'X') continue;
                board[row][col]= count==0? 'B': ((char)(count+48));

                if(board[row][col]== 'B')
                {
                    for(int i=Math.max(0, row-1);i<= Math.min(board.length-1, row+1);i++)
                    {
                        for(int j=Math.max(0, col-1); j<= Math.min(board[0].length-1, col+1);j++)
                            if(!visited[i][j])
                            { q.add(i); q.add(j); visited[i][j]= true; }
                    }
                }
            }
        }

        if(board.length== 12)
            GameHard.won= true;
        else if(board.length== 10)
            GameMedium.won= true;
        else
            Game.won= true;
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(board[i][j]== 'E')
                {
                    if(board.length== 12)
                        GameHard.won= false;
                    else if(board.length== 10)
                        GameMedium.won= false;
                    else
                        Game.won= false;
                }

        return lives;
    }
}
