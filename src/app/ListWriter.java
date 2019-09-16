package app;

import java.io.*;
import java.util.*;

public class ListWriter {

    ArrayList<PointEntry> highscoreList;
    File file;

    public ListWriter(){

        try {
                file = new File("Spaceship_vs_Asteroids_HighscoreList.txt");

                if (!file.exists()) {
                    file.createNewFile();
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Cant Create Highscorelist File");
            }

        createHightscoreList();
        }

        public void writeNewEntry(String name, int points){
            try {

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(name + ";" + points);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
                fileWriter.close();
                System.out.println("wrote");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Cant write in Highscore File");
            }
        }

        public void createHightscoreList(){
            highscoreList = new ArrayList<PointEntry>();

            try {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line = bufferedReader.readLine();

                while(line != null){
                    String[] splitLine = line.split(";");
                    String pointLine = splitLine[1];
                    int points = Integer.parseInt(pointLine);
                    String name = splitLine[0];

                    PointEntry newEntry = new PointEntry(points, name);
                    highscoreList.add(newEntry);

                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                Collections.sort(highscoreList);

            } catch (IOException e){
                e.printStackTrace();
            }
        }

        public ArrayList<PointEntry> getList(){
            createHightscoreList();
            return highscoreList;
        }
    }
