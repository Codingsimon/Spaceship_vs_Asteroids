package app;
import gamefigures.Enemy;
import gamefigures.FlyingObject;
import gamefigures.Projectile;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class DrawCanvas extends JComponent{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d =(Graphics2D)g;
        AffineTransform oldPos;

        //draw Background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,Vars.canvas.getWidth(), Vars.canvas.getHeight());


        oldPos = g2d.getTransform();
        g2d.translate(-Vars.spaceship.getWidth()/2, -Vars.spaceship.getHeight()/2);
        AffineTransform at = AffineTransform.getTranslateInstance(Vars.spaceship.getX(), Vars.spaceship.getY());
        at.rotate(Math.toRadians(Vars.spaceship.getOrientation()),Vars.spaceship.getWidth()/2, Vars.spaceship.getHeight()/2);
        at.scale(Vars.getScalfactor(), Vars.getScalfactor());
        g2d.drawImage(Vars.sp_ship_1, at, null);
        g2d.setTransform(oldPos);

        ArrayList<Enemy> tempEnemyList = (ArrayList<Enemy>) Vars.enemyList.clone();
        for (Enemy enemy : tempEnemyList){
            oldPos = g2d.getTransform();
            at = AffineTransform.getTranslateInstance(enemy.getX(), enemy.getY());
            at.scale(Vars.getScalfactor(), Vars.getScalfactor());
            g2d.drawImage(enemy.getImage(), at, null);
            g2d.setTransform(oldPos);
        }

        ArrayList<Projectile> temProjectlieList = (ArrayList<Projectile>) Vars.projectileList.clone();
        for (Projectile projectile : temProjectlieList){
            oldPos = g2d.getTransform();
            at = AffineTransform.getTranslateInstance(projectile.getX(), projectile.getY());
            at.rotate(Math.toRadians(projectile.getOrientation()),Vars.spaceship.getWidth()/2, Vars.spaceship.getHeight()/2);
            at.scale(Vars.getScalfactor(), Vars.getScalfactor());
            g2d.drawImage(projectile.getImage(), at, null);
            g2d.setTransform(oldPos);
        }

        g2d.setColor(Color.BLUE);
        g2d.drawRect(0, 0,300,300);

        repaint();
    }
}