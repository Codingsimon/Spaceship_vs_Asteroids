package app;

import enums.EnemyType;
import gamefigures.Enemy;

import java.text.DecimalFormat;
import java.util.Locale;

public class GameManager {

    private int points;
    private int relativePoints;
    private int lives;
    private int upgradeLevel = 1;

    public GameManager(){
        points = 0;
        relativePoints = 0;

        switch (upgradeLevel){
            case 1: lives = 2;
            break;
            case 2: lives = 4;
            break;
            case 3: lives = 6;
            break;
            case 4: lives = 8;
        }
    }

    private void addPoint(int newPoints){
        this.points += newPoints;
        relativePoints += newPoints;
    }

    private void subtractPoint(int points){
        this.points -= points;
    }

    public void add2Lives(){
        lives += 2;
    }

    public void reduceLive(){
        lives -= 1;
    }

    public int getLives(){
        return lives;
    }

    public int getUpgradeleve(){
        return upgradeLevel;
    }

    public int getPoints(){
        return points;
    }

    public void checkWhichPointsToAdd(Enemy enemy){
        if (enemy.getType() == EnemyType.ASTEREOID){
            switch (enemy.getSize()){
                case LARGE : addPoint(5500);
                    break;
                case MEDIUM: addPoint(3200);
                    break;
                case SMALL: addPoint(455);
                    break;
            }
        } else if (enemy.getType() == EnemyType.UFO){
            addPoint(11500);
        }
    }

    public void checkWhichPointsToSubtrakt(Enemy enemy){
        if (enemy.getType() == EnemyType.ASTEREOID){
            switch (enemy.getSize()){
                case LARGE : subtractPoint(2_000);
                    break;
                case MEDIUM: subtractPoint(200);
                    break;
                case SMALL: subtractPoint(100);
                    break;
            }
        } else if (enemy.getType() == EnemyType.UFO){
            subtractPoint(3000);
        }
    }

    public void subtractPointSelfeShot(){
        points -= 200;
    }

    void checkForDowngrade(){
        if (lives <= 6 && upgradeLevel >= 4){
            upgradeLevel = 3;
            relativePoints = 0;
            return;
        }
        if (lives <= 4 && upgradeLevel >= 3){
            upgradeLevel = 2;
            relativePoints = 0;
            return;
        }
        if (lives <= 2 && upgradeLevel >= 2){
            upgradeLevel = 1;
            relativePoints = 0;
            return;
        }
    }

    void checkForUpgrad(){
        if (relativePoints >= 17_000 && upgradeLevel == 3){
            upgradeLevel = 4;
            add2Lives();
            relativePoints = 0;
            return;
        }
        if (relativePoints >= 13_000 && upgradeLevel == 2){
            upgradeLevel = 3;
            add2Lives();
            relativePoints = 0;
            return;
        }
        if (relativePoints >= 10_000 && upgradeLevel == 1){
            upgradeLevel = 2;
            add2Lives();
            relativePoints = 0;
            return;
        }
    }

    public void update(){
        checkForUpgrad();
        checkForDowngrade();

        //check if lifes are 0
        if (lives <= 0 || Vars.gameRunning == false){
            gameOver();
        }
    }

    private void gameOver(){
        Vars.gameRunning = false;
}

    public String getFormatedPoints(){
        DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance(Locale.GERMAN);
        df.applyPattern( "#,###,##0" );
        return df.format(points);
    }

}
