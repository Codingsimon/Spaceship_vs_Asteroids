package gamefigures;

import app.Vars;
import enums.AsteroidSize;
import enums.AsteroidColor;
import enums.EnemyType;

import java.awt.image.BufferedImage;

public class Enemy {
    public int posx = 300;
    public int posy = 300;
    public int speedx = 1;
    public int speedy = -1;
    EnemyType type;
    AsteroidColor color;
    AsteroidSize size;

    public Enemy(EnemyType type){
        if (type == EnemyType.UFO){
            this.type = type;
        }
        System.out.println("Asteroid needs a Size");
    }

    public Enemy(EnemyType type, AsteroidSize size){
        if (type == EnemyType.UFO){
            createUFO();
        }
        if (type == EnemyType.ASTEREOID){
            createAsteroid(size);
        }
        setPosition();
    }

    private void setPosition(){
        this.posx = (int)(Math.random() * Vars.canvas.getWidth());
        this.posy = (int)(Math.random() * Vars.canvas.getHeight());
    }

    private void createUFO(){
        this.type = EnemyType.UFO;
    }

    private void createAsteroid(AsteroidSize size){
        this.type = EnemyType.ASTEREOID;
        this.size = size;
        int random = (int)(Math.random() * 3);
        System.out.println(random);
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
                case LARGE : return Vars.asteroid_brown_big;
                case MEDIUM: return Vars.asteroid_brown_medium;
                case SMALL: return Vars.asteroid_brown_small;
            }
            case GREEN : switch (size){
                case LARGE : return Vars.asteroid_green_big;
                case MEDIUM: return Vars.asteroid_green_medium;
                case SMALL: return Vars.asteroid_green_small;
            }
            case RED : switch (size){
                case LARGE : return Vars.asteroid_red_big;
                case MEDIUM: return Vars.asteroid_red_medium;
                case SMALL: return Vars.asteroid_red_small;
            }
        }
        return null;
    }

    public int getX(){
        return posx;
    }
    public int getY(){
        return posy;
    }

    public  void  update(){
        warp();
        posx = posx + speedx;
        posy = posy + speedy;
    }

    public void warp(){
        if (posx > Vars.canvas.getWidth()){
            posx = 0;
        }
        if (posx < 0){
            posx = Vars.canvas.getWidth();
        }
        if (posy > Vars.canvas.getHeight()){
            posy = 0;
        }
        if (posy < 0){
            posy = Vars.canvas.getHeight();
        }
    }
}
