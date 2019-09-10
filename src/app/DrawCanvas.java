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

import static app.Collision.*;

@SuppressWarnings("serial")
public class DrawCanvas extends JComponent{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d =(Graphics2D)g;
        AffineTransform oldPos;

        //draw Background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,Vars.canvas.getWidth(), Vars.canvas.getHeight());

        if (Vars.gameRunning == true) {

            //draw Spaceship
            oldPos = g2d.getTransform();
            g2d.setColor(Color.BLUE);
            g2d.drawOval(Vars.spaceship.getX(),Vars.spaceship.getY(),Vars.spaceship.getWidth(), Vars.spaceship.getHeight());

//            g2d.translate(2, 2);
            AffineTransform at = AffineTransform.getTranslateInstance(Vars.spaceship.getX(), Vars.spaceship.getY());
            at.rotate(Math.toRadians(Vars.spaceship.getOrientation()), Vars.spaceship.getWidth() / 2, Vars.spaceship.getHeight() / 2);
            at.scale(Vars.getScalfactor(), Vars.getScalfactor());
            g2d.drawImage(Vars.spaceship.getImage(), at, null);
            if (Vars.wListener.getUpState() == true || Vars.firecounter > 0){
                Vars.firecounter -= 1;
                at.translate(6, 22);
                g2d.drawImage(Vars.sp_fire, at, null);
            }
            g2d.setTransform(oldPos);

            //draw Enemys
            ArrayList<Enemy> tempEnemyList = (ArrayList<Enemy>) Vars.enemyList.clone();
            for (Enemy enemy : tempEnemyList) {
                oldPos = g2d.getTransform();

                g2d.setColor(Color.BLUE);
                g2d.drawOval(enemy.getX(),enemy.getY(), enemy.getWidth(), enemy.getHeight());

                at = AffineTransform.getTranslateInstance(enemy.getX(), enemy.getY());
                at.scale(Vars.getScalfactor(), Vars.getScalfactor());
                g2d.drawImage(enemy.getImage(), at, null);
                g2d.setTransform(oldPos);
            }


            //draw Projectile
            ArrayList<Projectile> temProjectlieList = (ArrayList<Projectile>) Vars.projectileList.clone();
            for (Projectile projectile : temProjectlieList) {
                oldPos = g2d.getTransform();

                if (Collision.twoRadians > pythagorean(distancex, distancey)){
                    g2d.setColor(Color.RED);
                } else {
                    g2d.setColor(Color.BLUE);
                }

                g2d.drawRect(projectile.getX(), projectile.getY(), -distancex, -distancey);


                AffineTransform at2 = AffineTransform.getTranslateInstance(projectile.getX(), projectile.getY());
//                at.translate(-5, 0);
                at2.scale(Vars.getScalfactor(), Vars.getScalfactor());
                at2.rotate(Math.toRadians(projectile.getOrientation()), 1, 1);
                g2d.drawImage(projectile.getImage(), at2, null);
                g2d.setTransform(oldPos);
            }


            //draw Pointcounter
            g.setColor(Color.WHITE);
            g.setFont(Vars.pixelfont);
            g.drawString("" + Vars.getFormatedPoints(), 20, 40);

        } else {
            g.setColor(Color.WHITE);
            g.setFont(Vars.pixelfont);
            g.drawString("Game Over", Vars.gameWidth/2 - 55, 300);
            g.drawString("" + Vars.getFormatedPoints(), Vars.gameWidth/2, 350);
        }


        repaint();
    }
}