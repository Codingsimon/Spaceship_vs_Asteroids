package app;

import gamefigures.Enemy;
import gamefigures.FlyingObject;
import gamefigures.Projectile;

import java.util.ArrayList;
import java.util.List;

public abstract class Collision {

    public static void collisionProjectileAstereoid(List<Enemy> list, Projectile selfe){
        for (Enemy e : list) {

            //Vertical inside
            if (e.getX() + e.getWidth()/2 > selfe.getX() && e.getX() + e.getWidth()/2 < selfe.getX() + selfe.getWidth()){
                //Horizontal inside
                if (e.getY() + e.getHeight()/2 > selfe.getY() && e.getY() + e.getHeight() < selfe.getY() + selfe.getHeight()){
                    removeEnemy(e);
                    Vars.projectilesToDelete.add(selfe);
                    addPoints(e);
                    break;
                }
            }

            //--Circle in Edge
            //leftupper
            if (pythagorean(Math.abs(e.getCenterY() - selfe.getLeftUpperCornerY()), Math.abs(e.getCenterX() - selfe.getLeftUpperCornerX())) < e.getRadian()){
                removeEnemy(e);
                Vars.projectilesToDelete.add(selfe);
                addPoints(e);
                break;
            }
            // rightupper
            if (pythagorean(Math.abs(e.getCenterX() - selfe.getRightUpperCornerX()), Math.abs(selfe.getRightUpperCornerY() - e.getCenterY())) < e.getRadian()){
                removeEnemy(e);
                Vars.projectilesToDelete.add(selfe);
                addPoints(e);
                break;
            }
            //lowerright
            if (pythagorean(Math.abs(e.getCenterY()-selfe.getRightLowerCornerY()), Math.abs(e.getCenterX() - selfe.getRightLowerCornerX())) < e.getWidth()/2){
                removeEnemy(e);
                Vars.projectilesToDelete.add(selfe);
                addPoints(e);
                break;
            }
            //lowerleft
            if (pythagorean(Math.abs(e.getCenterY()-selfe.getLeftLowerCornerY()), Math.abs(selfe.getLeftLowerCornerX() - e.getCenterX())) < e.getWidth()/2){
                removeEnemy(e);
                Vars.projectilesToDelete.add(selfe);
                addPoints(e);
                break;
            }
        }
    }

    private static void addPoints(Enemy enemy){
        Vars.points += 100;
        System.out.println(Vars.points);
    }

    private static void upgradeShip(){
        switch (Vars.points){
            case 2000: Vars.spaceship.setUpgradeLevel(2);
            break;
            case 3500: Vars.spaceship.setUpgradeLevel(3);
                break;
            case 5500: Vars.spaceship.setUpgradeLevel(4);
                break;
        }
    }

    public static void update(){
        deleteProjectiles();
        upgradeShip();
    }
    
    private static void deleteProjectiles(){
        for (Projectile p :
                Vars.projectilesToDelete) {
            Vars.projectileList.remove(p);
        }
    }

    private static void removeEnemy(Enemy enemy){
        Vars.enemyList.remove(enemy);
        Vars.sporner.reduceEnemycount();
    }

    static double pythagorean(double kath1, double kath2){
        return Math.sqrt(Math.pow(kath1,2) + Math.pow(kath2,2));
    }
}
