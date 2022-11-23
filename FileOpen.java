import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class FileOpen {

    public static ArrayList<ArrayList<Character>> board = new ArrayList<>();
    public static Pair<Integer, Integer> pacman;
    public static ArrayList <Pair<Integer, Integer>> ghosts = new ArrayList<>();
    public static ArrayList <Pair<Integer, Integer>> exits = new ArrayList<>();


    public static void main(String[] args) {

        int row = 0;
        Scanner sc = openFile("C:/Users/ranni/Downloads/pacman_a.screen");
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            board.add(new ArrayList<>());

            for (int i = 0; i < line.length(); i++) {
                board.get(row).add(line.charAt(i));
            }
            row++;
        }

        pacman = pacmanPos(board);
        ghostsPos(board, ghosts);
        exits(board, exits);
/*        System.out.println(pacman);
        System.out.println(ghosts);
        System.out.println(exits);*/

        System.out.println(minDistance(pacman.getKey(),pacman.getValue(), exits.get(0)));

    }


    public static int minDistance(int x, int y, Pair<Integer, Integer> exit){
        Qitem source = new Qitem(0,0,0);
        // To keep track of visited QItems. Marking
        // blocked cells as visited.
        int rows = board.size();
        int cols = board.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        //bool** visited = new bool* [rows];
//        for (int i = 0; i < rows; i++)
//        {
//            visited[i] = new bool[cols];
//        }

        // initializing source location
        source.setRow(y);
        source.setCol(x);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <cols; j++)
            {
                if (board.get(i).get(j) == '#'|| board.get(i).get(j) == '.')
                    visited[i][j] = true;
                else
                    visited[i][j] = false;
            }
        }

        // applying BFS on matrix cells starting from source
        Queue<Qitem> q = new ArrayBlockingQueue<>(1000000);
        q.add(source);
        visited[source.getRow()][source.getCol()] = true;
        while (!q.isEmpty()) {
            Qitem p = q.poll();
            //q.pop();

            // Destination found;
            // _board.get(i).get(j)
//            if (board.get(p.row).get(p.col) == 'A') - Zimi

            if(p.row == exit.getKey() && p.col == exit.getValue())
            {
                return p.dist;
            }

            // moving up
            if (p.row - 1 > 0 &&
                    visited[p.row - 1][p.col] == false) {
                q.add(new Qitem(p.row - 1, p.col, p.dist + 1));
                visited[p.row - 1][p.col] = true;
            }

            // moving down
            if (p.row + 1 < rows-1 &&
                    visited[p.row + 1][p.col] == false) {
                q.add( new Qitem(p.row + 1, p.col, p.dist + 1));
                visited[p.row + 1][p.col] = true;
            }

            // moving left
            if (p.col - 1 > 0 &&
                    visited[p.row][p.col - 1] == false) {
                q.add(new Qitem(p.row, p.col - 1, p.dist + 1));
                visited[p.row][p.col - 1] = true;
            }

            // moving right
            if (p.col + 1 < cols-1 &&
                    visited[p.row][p.col + 1] == false) {
                q.add( new Qitem(p.row, p.col + 1, p.dist + 1));
                visited[p.row][p.col + 1] = true;
            }
        }
        return -1;
    }
    public static Pair<Integer, Integer> pacmanPos(ArrayList<ArrayList<Character>> board){
        Pair<Integer, Integer> pacman = null;

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j).equals('A')){
                    pacman = new Pair<>(i,j);
                    return pacman;
                }
            }
        }
        return null;
    }

    public static void ghostsPos(ArrayList<ArrayList<Character>> board,
                                                   ArrayList<Pair<Integer, Integer>> ghosts){

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j).equals('M')){
                    ghosts.add(new Pair<>(i,j));
                }
            }
        }
    }

    public static void exits(ArrayList<ArrayList<Character>> board,
                             ArrayList<Pair<Integer, Integer>> exits){

        int amountOfCharsInRow = board.get(0).size();
        int amountOfCharsInCol = board.size();

        //rows
        for (int i = 0; i < amountOfCharsInRow; i++) {
            if(board.get(0).get(i).equals('.')){
                exits.add(new Pair<>(0, i));
            }

            if(board.get(amountOfCharsInCol - 1).get(i).equals('.')){
                exits.add(new Pair<>(amountOfCharsInCol - 1, i));
            }
        }

        //cols
        for (int i = 0; i < amountOfCharsInCol; i++) {

            if(board.get(i).get(0).equals('.')){
                exits.add(new Pair<>(i, 0));
            }

            if(board.get(i).get(amountOfCharsInRow - 1).equals('.')){
                exits.add(new Pair<>(i, amountOfCharsInRow - 1));
            }

        }






    }




    static Scanner openFile(String name){
        File file = new File(name);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("file can't open.");
        }
/*
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }*/
        return scanner;
    }
}
