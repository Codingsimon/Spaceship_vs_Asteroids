package app;

import enums.AsteroidColor;
import enums.AsteroidSize;
import enums.EnemyType;
import gamefigures.Enemy;
import gamefigures.Projectile;

public abstract class Collision {

    public static void collisionProjectileAstereoid( Projectile selfe){
        for (Enemy e : Vars.enemyList) {

            //Vertical inside
            if (e.getX() + e.getWidth()/2 > selfe.getX() && e.getX() + e.getWidth()/2 < selfe.getX() + selfe.getWidth()){
                //Horizontal inside
                if (e.getY() + e.getHeight()/2 > selfe.getY() && e.getY() + e.getHeight() < selfe.getY() + selfe.getHeight()){
                    breakEnemy(e);
                    Vars.projectilesToDelete.add(selfe);
                    addPoints(e);
                    break;
                }
            }

            //--Circle in Edge
            //leftupper
            if (pythagorean(Math.abs(e.getCenterY() - selfe.getLeftUpperCornerY()), Math.abs(e.getCenterX() - selfe.getLeftUpperCornerX())) < e.getRadian()){
                breakEnemy(e);
                Vars.projectilesToDelete.add(selfe);
                addPoints(e);
                break;
            }
            // rightupper
            if (pythagorean(Math.abs(e.getCenterX() - selfe.getRightUpperCornerX()), Math.abs(selfe.getRightUpperCornerY() - e.getCenterY())) < e.getRadian()){
                breakEnemy(e);
                Vars.projectilesToDelete.add(selfe);
                addPoints(e);
                break;
            }
            //lowerright
            if (pythagorean(Math.abs(e.getCenterY()-selfe.getRightLowerCornerY()), Math.abs(e.getCenterX() - selfe.getRightLowerCornerX())) < e.getWidth()/2){
                breakEnemy(e);
                Vars.projectilesToDelete.add(selfe);
                addPoints(e);
                break;
            }
            //lowerleft
            if (pythagorean(Math.abs(e.getCenterY()-selfe.getLeftLowerCornerY()), Math.abs(selfe.getLeftLowerCornerX() - e.getCenterX())) < e.getWidth()/2){
                breakEnemy(e);
                Vars.projectilesToDelete.add(selfe);
                addPoints(e);
                break;
            }
        }
    }

    public static void collisionSpaceshipEnemy(){
        for (Enemy e : Vars.enemyList) {
            //--Circle center inside of rectangel

            //Vertical inside
            if (e.getX() + e.getWidth()/2 > Vars.spaceship.getX() && e.getX() + e.getWidth()/2 < Vars.spaceship.getX() + Vars.spaceship.getWidth()){
                //Horizontal inside
                if (e.getY() + e.getHeight()/2 > Vars.spaceship.getY() && e.getY() + e.getHeight() < Vars.spaceship.getY() + Vars.spaceship.getHeight()){
                    subtractPoints(e);
                    Vars.enemyList.remove(e);
                    break;
                }
            }

            //--Circle in Edge
            //leftupper
            if (pythagorean(Math.abs(e.getCenterY() - Vars.spaceship.getLeftUpperCornerY()), Math.abs(e.getCenterX() - Vars.spaceship.getLeftUpperCornerX())) < e.getRadian()){
                subtractPoints(e);
                Vars.enemyList.remove(e);
                break;
            }
            // rightupper
            if (pythagorean(Math.abs(e.getCenterX() - Vars.spaceship.getRightUpperCornerX()), Math.abs(Vars.spaceship.getRightUpperCornerY() - e.getCenterY())) < e.getRadian()){
                subtractPoints(e);
                Vars.enemyList.remove(e);
                break;
            }
            //lowerright
            if (pythagorean(Math.abs(e.getCenterY()-Vars.spaceship.getRightLowerCornerY()), Math.abs(e.getCenterX() - Vars.spaceship.getRightLowerCornerX())) < e.getWidth()/2){
                subtractPoints(e);
                Vars.enemyList.remove(e);
                break;
            }
            //lowerleft
            if (pythagorean(Math.abs(e.getCenterY()-Vars.spaceship.getLeftLowerCornerY()), Math.abs(Vars.spaceship.getLeftLowerCornerX() - e.getCenterX())) < e.getWidth()/2){
                subtractPoints(e);
                Vars.enemyList.remove(e);
                break;
            }
        }
    }

    private static void addPoints(Enemy enemy){
        if (enemy.getType() == EnemyType.ASTEREOID){
                switch (enemy.getSize()){
                    case LARGE : Vars.points += 5500;
                    Vars.relativePoints += 5500;
                    break;
                    case MEDIUM: Vars.points += 3200;
                        Vars.relativePoints += 3200;
                    break;
                    case SMALL: Vars.points += 455;
                        Vars.relativePoints += 455;
                    break;
            }
        } else if (enemy.getType() == EnemyType.UFO){
            Vars.points += 11500;
        }
    }

    private static void subtractPoints(Enemy enemy){
        if (Vars.spaceship.getUpgradeLevel() == 1){
            Vars.gameRunning = false;
            return;
        }
        Vars.spaceship.setUpgradeLevel(Vars.spaceship.getUpgradeLevel() - 1);
        if (enemy.getType() == EnemyType.ASTEREOID){
            switch (enemy.getSize()){
                case LARGE : Vars.points -= 10000 * 3;
                    break;
                case MEDIUM: Vars.points -= 3000 * 3;
                    break;
                case SMALL: Vars.points -= 1000 * 3;
                    break;
            }
        } else if (enemy.getType() == EnemyType.UFO){
            Vars.points -= 11500 * 2;
        }
    }

    private static void upgradeShip(){
        if (Vars.relativePoints > 10000 && Vars.spaceship.getUpgradeLevel() == 3){
            Vars.spaceship.setUpgradeLevel(4);
            Vars.relativePoints = 0;
            return;
        }
        if (Vars.relativePoints > 3000 && Vars.spaceship.getUpgradeLevel() == 2) {
            Vars.spaceship.setUpgradeLevel(3);
            Vars.relativePoints = 0;
            return;
        }
        if (Vars.relativePoints > 1000 && Vars.spaceship.getUpgradeLevel() == 1) {
            Vars.spaceship.setUpgradeLevel(2);
            Vars.relativePoints = 0;
            return;
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

    private static void breakEnemy(Enemy enemy){
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

        Vars.enemyList.remove(enemy);
        Vars.sporner.reduceEnemycount();
    }


    private static void createNewAsteroid(Enemy posObject, AsteroidSize size, AsteroidColor color){
        for (int i = 0; i < 1; i++) {
            Enemy enemy = new Enemy(EnemyType.ASTEREOID, size, EnemySporner.getSpeedlevel());
            enemy.setColor(color);
            enemy.setPos(posObject.getX(), posObject.getY());
            Vars.enemyList.add(enemy);
        }
    }

    static double pythagorean(double kath1, double kath2){
        return Math.sqrt(Math.pow(kath1,2) + Math.pow(kath2,2));
    }
}
