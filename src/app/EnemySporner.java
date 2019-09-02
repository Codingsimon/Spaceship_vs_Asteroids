package app;

import enums.AsteroidSize;
import enums.EnemyType;
import gamefigures.Enemy;

public class EnemySporner {
    private int enemycount;
    private int small = 1;
    private int medium = 0;
    private int large = 0;
    private int speedlevel = 10;
    private int[][] levelList = new int[50][4]; //ufo, small, medium, large

    public EnemySporner(){
        countEnemys();
        newLevelSetup();

        int ufoc = 5;
        int smallc = 1;
        int mediumc = 2;
        int largec = 3;

        levelList[0][1] = 1;

        for (int i = 1; i < 50; i++) {
            if (Math.abs((double)i % (double)ufoc) < 0.1){
                levelList[i][0] += 1;
            } else {
                levelList[i][0] += levelList[i - 1][0];
            }

            if (Math.abs((double)i % (double)smallc) < 0.1){
                levelList[i][1] += 1;
            } else {
                levelList[i][1] += levelList[i -1][1];
            }

            if (Math.abs((double)i % (double)mediumc) < 0.1){
                levelList[i][2] += 1;
            } else {
                levelList[i][2] += levelList[i-1][2];
            }

            if (Math.abs((double)i % (double)largec) < 0.1){
                levelList[i][3] += 1;
            } else {
                levelList[i][3] += levelList[i][3];
            }
        }
    }

    public void setLevel(int i){
        for (int j = 0; j < i; j++) {
            levelUp();
        }
        newLevelSetup();
    }
    
    private void levelUp(){
        speedlevel += 10;
        small += 1;
        medium += 1;
        large += 1;
        countEnemys();
    }

    public void newLevelSetup(){
        Vars.enemyList.clear();
        for (int i = 0; i < small; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.SMALL, speedlevel);
            Vars.enemyList.add(enemy);
        }
        for (int i = 0; i < medium; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.MEDIUM, speedlevel);
            Vars.enemyList.add(enemy);
        }
        for (int i = 0; i < large; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.LARGE, speedlevel);
            Vars.enemyList.add(enemy);
        }
    }

    private void countEnemys(){
        enemycount = small + medium + large;
    }

    public int getEnemycount(){
        return enemycount;
    }

    public void reduceEnemycount(){
        enemycount--;
    }
}
