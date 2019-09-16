package app;

import enums.AsteroidColor;
import enums.AsteroidSize;
import enums.EnemyType;
import gamefigures.Enemy;
import gamefigures.Explosion;
import gamefigures.Projectile;

import java.util.ArrayList;

public class EnemyCollision extends Collision {

    public void checkCollisionProjectileEnemy(Projectile projectile){
        ArrayList<Enemy> tempEnemyList = (ArrayList<Enemy>) Vars.enemyList.clone();

        for (Enemy enemy: tempEnemyList){
            int distancex = Math.abs(enemy.getCenterX() - projectile.getX());
            int distancey = Math.abs(enemy.getCenterY() - projectile.getY());
            int twoRadians = enemy.getWidth()/2 + projectile.getWidth()/2;

            if (super.pythagorean(distancex, distancey) < twoRadians){
                collisionHappend(enemy, projectile);
            }
        }
    }


    //Check which Collision Event to call
    private void collisionHappend(Enemy enemy, Projectile projectile){
        if (enemy.getType() == EnemyType.ASTEREOID){
            collisionProjectileAsteroid(enemy, projectile);
        }
        if (enemy.getType() == EnemyType.UFO){
            collisionProjectileUFO(enemy, projectile);
        }
    }

    //Collision Events
    private void collisionProjectileAsteroid(Enemy enemy, Projectile projectile){
        Vars.explosionList.add(new Explosion(enemy.getCenterX() - enemy.getWidth()/4, enemy.getCenterY()- enemy.getHeight()/4));
        Vars.gameManager.checkWhichPointsToAdd(enemy);
        splitAstereoid(enemy);
        Vars.projectilesToDelete.add(projectile);
        Vars.enemiesToDelete.add(enemy);
    }

    private void collisionProjectileUFO(Enemy enemy, Projectile projectile){
        int warpcounter = projectile.getWarpcounter();

        if (warpcounter >= 2 && projectile.creator.equals(enemy)){
            //do not Delete
            return;
        } else{
            Vars.explosionList.add(new Explosion(enemy.getCenterX(), enemy.getCenterY()));
            Vars.gameManager.checkWhichPointsToAdd(enemy);
            Vars.projectilesToDelete.add(projectile);
            Vars.enemiesToDelete.add(enemy);
        }
    }
    //Collision Events End

    //Big Astereoid splits
    private void splitAstereoid(Enemy enemy){
        if (enemy.getType() == EnemyType.ASTEREOID){
            switch (enemy.getColor()){
                case BROWN : switch (enemy.getSize()){
                    case LARGE : createNewAsteroid(enemy, AsteroidSize.MEDIUM, AsteroidColor.BROWN);
                        break;
                    case MEDIUM: createNewAsteroid(enemy, AsteroidSize.SMALL, AsteroidColor.BROWN);
                        break;
                }
                case GREEN : switch (enemy.getSize()){
                    case LARGE : createNewAsteroid(enemy, AsteroidSize.MEDIUM, AsteroidColor.GREEN);
                        break;
                    case MEDIUM: createNewAsteroid(enemy, AsteroidSize.SMALL, AsteroidColor.GREEN);
                        break;
                }
                case RED : switch (enemy.getSize()){
                    case LARGE : createNewAsteroid(enemy, AsteroidSize.MEDIUM, AsteroidColor.RED);
                        break;
                    case MEDIUM: createNewAsteroid(enemy, AsteroidSize.SMALL, AsteroidColor.RED);
                        break;
                }
            }
        }
    }


    private static void createNewAsteroid(Enemy posObject, AsteroidSize size, AsteroidColor color){
        for (int i = 0; i < 1; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, size, EnemySporner.getSpeedlevel());
            enemy.setColor(color);
            enemy.setPos(posObject.getX(), posObject.getY());
            Vars.enemyList.add(enemy);
        }
    }

     public void update(){
        for (Projectile projectile : Vars.projectileList) {
            checkCollisionProjectileEnemy(projectile);
        }
        deleteRoutine();
    }

}
