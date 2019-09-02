package gamefigures;

import app.Vars;
import enums.AsteroidSize;
import enums.AsteroidColor;
import enums.EnemyType;

import java.awt.image.BufferedImage;

public class Enemy extends FlyingObject {

    EnemyType type;
    AsteroidColor color;
    AsteroidSize size;

    public Enemy(EnemyType type, int speedlevel){

        xvel = randomNumber(-speedlevel, speedlevel);
        yvel = randomNumber(-speedlevel, speedlevel);
        if (type == EnemyType.UFO){
            this.type = type;
        }
        System.out.println("Asteroid needs a Size");
    }

    public Enemy(EnemyType type, AsteroidSize size, int speedlevel){
        xvel = randomNumber(-speedlevel, speedlevel);
        yvel = randomNumber(-speedlevel, speedlevel);

        if (type == EnemyType.UFO){
            createUFO();
        }
        if (type == EnemyType.ASTEREOID){
            createAsteroid(size);
        }
        setStartPositionBorder();
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
        return null;
    }

    public int getCenterX(){
        return (int) this.getX() + this.getWidth()/2;
    }

    public int getCenterY(){
        return (int) this.getY() + this.getHeight()/2;
    }

    public int getRadian(){
        return (int)this.getWidth()/2;
    }

    public  void  update(){
        warp();
        posy = 310;
        posx = 250;
//        posx = posx + xvel;
//        posy = posy + yvel;
    }

}
