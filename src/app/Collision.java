package app;

import enums.AsteroidColor;
import enums.AsteroidSize;
import enums.EnemyType;
import gamefigures.Enemy;
import gamefigures.Explosion;
import gamefigures.FlyingObject;
import gamefigures.Projectile;

import java.util.ArrayList;

public abstract class Collision {

    public abstract void update();

    public static void deleteRoutine(){
        deleteProjectiles();
        deleteEnemies();
    }

    private static void deleteEnemies(){
        for (Enemy e :  Vars.enemiesToDelete){
            Vars.enemyList.remove(e);
        }
    }

    private static void deleteProjectiles(){
        for (Projectile p : Vars.projectilesToDelete) {
                Vars.projectileList.remove(p);
        }
    }

    double pythagorean(double kath1, double kath2){
        return Math.sqrt(Math.pow(kath1,2) + Math.pow(kath2,2));
    }
}
