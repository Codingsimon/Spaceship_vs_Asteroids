package gamefigures;

import app.Vars;
import enums.AsteroidSize;
import enums.AsteroidColor;
import enums.EnemyType;

import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy {
    private double posx = 1;
    private double posy = 1;
    private double speedx = 0.1;
    private double speedy = 0.1;

    EnemyType type;
    AsteroidColor color;
    AsteroidSize size;

    public Enemy(EnemyType type, int speedlevel){
        speedx = randomNumber(-speedlevel, speedlevel);
        speedy = randomNumber(-speedlevel, speedlevel);
        if (type == EnemyType.UFO){
            this.type = type;
        }
        System.out.println("Asteroid needs a Size");
    }

    public Enemy(EnemyType type, AsteroidSize size, int speedlevel){
        speedx = randomNumber(-speedlevel, speedlevel);
        speedy = randomNumber(-speedlevel, speedlevel);

//        System.out.println(speedx);

        if (type == EnemyType.UFO){
            createUFO();
        }
        if (type == EnemyType.ASTEREOID){
            createAsteroid(size);
        }
        setPosition();
    }

    public double randomNumber(int min, int max){
        double speed = ThreadLocalRandom.current().nextInt(min, max + 1) / 10;
        if (speed == 0){
            return randomNumber(min,max);
        }
        return speed;
    }

    private void setPosition(){
//        this.posy = 300;
//        this.posx = 10;
        this.posx = Math.random() * Vars.gameWidth;
        this.posy = Math.random() * Vars.gameHeight;
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

    public int getWidth(){
        return (int)((double)getImage().getWidth() * Vars.getScalfactor());
    }

    public int getHeight(){
        return (int)((double)getImage().getHeight() * Vars.getScalfactor());
    }

    public int getX(){
        return (int)posx;
    }
    public int getY(){
        return (int)posy;
    }

    public  void  update(){
        warp();
        posx = posx + speedx;
        posy = posy + speedy;
    }

    public void warp(){
        if (posx > Vars.gameWidth){
            posx = 0 - getWidth();
        }
        if (posx < 0 - getWidth()){
            posx = Vars.gameWidth;
        }
        if (posy > Vars.gameHeight){
            posy = 0 - getHeight();
        }
        if (posy < 0 - getHeight()){
            posy = Vars.gameHeight;
        }
    }
}
