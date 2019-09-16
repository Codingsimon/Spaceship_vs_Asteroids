package gamefigures;

import app.Vars;
import enums.AsteroidSize;
import enums.AsteroidColor;
import enums.EnemyType;

import java.awt.image.BufferedImage;

public class Enemy extends FlyingObject{

    EnemyType type;
    AsteroidColor color;
    AsteroidSize size;
    long clock = 0;

    public Enemy(EnemyType type, int speedlevel){
        startPostion();

        clock = System.currentTimeMillis();

        xvel = randomNumber(-speedlevel, speedlevel);
        yvel = randomNumber(-speedlevel, speedlevel);
        if (type == EnemyType.UFO){
            this.type = type;
        }
    }

    public Enemy(EnemyType type, AsteroidSize size, int speedlevel){
        startPostion();

        clock = System.currentTimeMillis();

        xvel = randomNumber(-speedlevel, speedlevel)/6;
        yvel = randomNumber(-speedlevel, speedlevel)/6;

        if (type == EnemyType.UFO){
            createUFO();
        }
        if (type == EnemyType.ASTEREOID){
            createAsteroid(size);
        }
    }


    private void startPostion(){
//        posy = 370;
//        posx = 370;
        posx = randomNumber(0, Vars.gameWidth);

        if (randomNumber(0,1) == 0){
            posy = 30;
        } else {
            posy = Vars.gameHeight;
        }
    }

    private void createUFO(){
        this.type = EnemyType.UFO;
    }

    private void createAsteroid(AsteroidSize size){
        this.type = EnemyType.ASTEREOID;
        this.size = size;
        int random = (int)(Math.random() * 3);
        switch (random){
            case 0 : this.color = AsteroidColor.BROWN;
                break;
            case 1 : this.color = AsteroidColor.RED;
                break;
            case 2 : this.color = AsteroidColor.GREEN;
                break;
        }
}

    public BufferedImage getImage(){
        if (type == EnemyType.ASTEREOID){
            switch (color){
                case BROWN : switch (size){
                    case LARGE : return Vars.sp_asteroid_brown_big;
                    case MEDIUM: return Vars.sp_asteroid_brown_medium;
                    case SMALL: return Vars.sp_asteroid_brown_small;
                }
                case GREEN : switch (size){
                    case LARGE : return Vars.sp_asteroid_green_big;
                    case MEDIUM: return Vars.sp_asteroid_green_medium;
                    case SMALL: return Vars.sp_asteroid_green_small;
                }
                case RED : switch (size){
                    case LARGE : return Vars.sp_asteroid_red_big;
                    case MEDIUM: return Vars.sp_asteroid_red_medium;
                    case SMALL: return Vars.sp_asteroid_red_small;
                }
            }
        } else {
            return Vars.sp_ufo;
        }

        return null;
    }

    public int getOrientation(){
        return 0;
    }

    public  void  update(){
        warp();
//        posx = 200;
//        posy = 300;

        posx = posx + xvel;
        posy = posy + yvel;
        if (this.type == EnemyType.UFO){
            if (((System.currentTimeMillis() - clock) / 1000) > 3){
                clock = System.currentTimeMillis();
                fire();
            }
        }
    }

    private void fire() {
        //fire function
        Projectile projectile = new Projectile(this);
        Vars.projectileList.add(projectile);
    }

    public EnemyType getType(){
        return type;
    }

    public AsteroidSize getSize(){
        return size;
    }

    public AsteroidColor getColor(){
        return color;
    }

    public void setColor(AsteroidColor color){
        this.color = color;
    }
}
