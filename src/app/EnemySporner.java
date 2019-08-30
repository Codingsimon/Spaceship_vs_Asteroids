package app;

import enums.AsteroidSize;
import enums.EnemyType;
import gamefigures.Enemy;

public class EnemySporner {
    private int enemycount = 3;
    private int small = 3;
    private int medium = 3;
    private int large = 3;

    public void levelUp(){
        small += 1;
        medium += 1;
        large += 1;
    }

    public void newLevelSetup(){
        for (int i = 0; i < small; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.SMALL);
            Vars.enemyList.add(enemy);
        }
        for (int i = 0; i < medium; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.MEDIUM);
            Vars.enemyList.add(enemy);
        }
        for (int i = 0; i < large; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, AsteroidSize.LARGE);
            Vars.enemyList.add(enemy);
        }
    }

}
