package app;

import enums.AsteroidSize;
import enums.EnemyType;
import gamefigures.Enemy;

import java.util.Random;

public class EnemySporner {
    private int enemycount;
    private int small = 1;
    private int medium = 0;
    private int large = 0;
    private int speedlevel = 1;
    private int[][] levelList = new int[50][4]; //ufo, small, medium, large

    public EnemySporner(){
        setEnemycount();
        newLevelSetup();

        for (int i = 1; i < 50; i++) {
            for (int j = 0; j < i; j++) {
                int type = generateRandomInt(levelList[0].length);
                levelList[i][type] += 1;
            }
        }
    }

    public static int generateRandomInt(int max){
        Random random = new Random();
        return random.nextInt(max);

    }
    
    public void levelUp(){
        Vars.level += 1;
        speedlevel += 1;
        setEnemycount();
        newLevelSetup();
    }

    public void newLevelSetup(){
        Vars.enemyList.clear();
        for (int i = 0; i < levelList[Vars.level][0]; i++) {
            Enemy enemy = new Enemy(EnemyType.UFO, 1);
            Vars.enemyList.add(enemy);
        }
        for (int i = 0; i < levelList[Vars.level][1]; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.SMALL, speedlevel);
            Vars.enemyList.add(enemy);
        }
        for (int i = 0; i < levelList[Vars.level][2]; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.MEDIUM, speedlevel);
            Vars.enemyList.add(enemy);
        }
        for (int i = 0; i < levelList[Vars.level][3]; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.LARGE, speedlevel);
            Vars.enemyList.add(enemy);
        }
    }

    private void setEnemycount(){
        enemycount = Vars.level;
    }

    public int getEnemycount(){
        return enemycount;
    }

    public void reduceEnemycount(){
        enemycount--;
    }
}
