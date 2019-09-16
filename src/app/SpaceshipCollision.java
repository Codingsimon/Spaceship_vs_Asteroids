package app;

import gamefigures.Enemy;
import gamefigures.Projectile;

import java.util.ArrayList;

public class SpaceshipCollision extends Collision {

    //Main types of Collisions

    public void checkCollisionSpaceshipEnemy(){
        for (Enemy enemy: Vars.enemyList){
            int distancex = Math.abs(enemy.getX() - Vars.spaceship.getX());
            int distancey = Math.abs(enemy.getY() - Vars.spaceship.getY());
            int twoRadians = enemy.getWidth()/2 + Vars.spaceship.getWidth()/2;
            if (super.pythagorean(distancex, distancey) < twoRadians){
                collisionHappenedEnemy(enemy);
            }
        }
    }

    public void checkCollisionProjectileSpaceship(){

        ArrayList<Projectile> tempProjectileList = (ArrayList<Projectile>) Vars.projectileList.clone();

        for (Projectile projectile: tempProjectileList){
            int distancex = Math.abs(projectile.getX() - Vars.spaceship.getCenterX());
            int distancey = Math.abs(projectile.getY() - Vars.spaceship.getCenterY());
            int twoRadians = projectile.getWidth()/2 + Vars.spaceship.getWidth()/2;

            if (pythagorean(distancex, distancey) < twoRadians){
                int warpcounter = projectile.getWarpcounter();

                if (warpcounter >= 2 && projectile.creator.equals(Vars.spaceship)){
                    //do not Delete
                    return;
                } else{
                    collisionHappenedSpaceship(projectile);
                }
            }
        }
    }


    private void collisionHappenedSpaceship(Projectile projectile){
        if (projectile.creator instanceof Enemy){
            //UFO shot spaceship
            Vars.gameManager.checkWhichPointsToSubtrakt((Enemy)projectile.creator);
            Vars.gameManager.reduceLive();
            Vars.projectilesToDelete.add(projectile);
        } else {
            //selfe shot
            Vars.gameManager.reduceLive();
            Vars.gameManager.subtractPointSelfeShot();
            Vars.projectilesToDelete.add(projectile);
        }
    }

    private void collisionHappenedEnemy(Enemy enemy){
        Vars.enemiesToDelete.add(enemy);
        Vars.gameManager.checkWhichPointsToSubtrakt(enemy);
        Vars.gameManager.reduceLive();
    }


    public void update(){
        checkCollisionSpaceshipEnemy();
        checkCollisionProjectileSpaceship();
        deleteRoutine();
    }


}
