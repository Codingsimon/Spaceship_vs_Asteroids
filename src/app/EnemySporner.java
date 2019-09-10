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
    private static int speedlevel = 1;
    private int[][] levelList = new int[50][4]; //ufo, small, medium, large

    public EnemySporner(){
        setEnemycount();
        newLevelSetup();

        int levelDecreaser = 0;
        for (int i = 1; i < 50; i++) {
            //fill per Level
           if (i % 2 == 0){
               levelDecreaser += 1;
           }
            for (int j = 0; j < i -levelDecreaser; j++) {
                int type = generateRandomInt(levelList[0].length);

                //Stop enemy Type for lower level
                while (type == 0 && i < 5 || type == 2 && i < 3 || type == 3 && i < 4){
                    type = generateRandomInt(levelList[0].length);
                }

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
        System.out.println(Vars.level);
        speedlevel += 1;
        setEnemycount();
        newLevelSetup();
    }

    public void newLevelSetup(){
        Vars.enemyList.clear();

        Enemy enemytest = new Enemy(EnemyType.ASTEREOID, AsteroidSize.SMALL, 1);
        Vars.enemyList.add(enemytest);

//        for (int i = 0; i < levelList[Vars.level][0]; i++) {
//            Enemy enemy = new Enemy(EnemyType.UFO, 1);
//            Vars.enemyList.add(enemy);
//        }
//        for (int i = 0; i < levelList[Vars.level][1]; i++) {
//            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.SMALL, speedlevel);
//            Vars.enemyList.add(enemy);
//        }
//        for (int i = 0; i < levelList[Vars.level][2]; i++) {
//            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.MEDIUM, speedlevel);
//            Vars.enemyList.add(enemy);
//        }
//        for (int i = 0; i < levelList[Vars.level][3]; i++) {
//            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.LARGE, speedlevel);
//            Vars.enemyList.add(enemy);
//        }

    }

    public static int getSpeedlevel(){
        return speedlevel;
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
