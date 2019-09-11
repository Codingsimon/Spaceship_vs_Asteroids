package gamefigures.items;

import app.Vars;

import java.awt.image.BufferedImage;

public class Shield extends Item{
    public String type = "shield";
    double posx;
    double posy;
    double xvel;
    double yvel;

    public Shield(){
        //TODO get destroying projectiles posx/posy - mb even inherit from projectile?
        posx = 0;
        posy = 0;
        xvel = 0;
        yvel = 0;
    }

    @Override
    public int getOrientation(){
        return 0;
    }

    @Override
    public void update() {
        posx += xvel;
        posy -= xvel;
        warp();
    }

    @Override
    public BufferedImage getImage() {
        return Vars.sp_item_shield;
    }
}

