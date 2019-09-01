package app;
import gamefigures.Enemy;

import java.awt.*;
import java.awt.geom.AffineTransform;
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


        //draw Ship
//        oldPos = g2d.getTransform();
//        g2d.translate(-shipPixelWidth/2, -shipPixelHeight/2);
//        g2d.translate(Vars.canvas.getWidth()/2,Vars.canvas.getHeight()/2);
//        g2d.rotate(0.5);
//        g2d.drawImage(Vars.getShipImage(), Vars.spaceship.getX(), Vars.spaceship.getY(), shipPixelWidth, shipPixelHeight, null);
//        g2d.setTransform(oldPos);

//        AffineTransform transform = new AffineTransform();
//        transform.rotate(Math.toRadians(45), Vars.spaceship.getX() + shipPixelWidth/2, Vars.spaceship.getY() + shipPixelHeight/2);
//        g2d.fill(transform);


        oldPos = g2d.getTransform();
        g2d.translate(-Vars.spaceship.getWidthScale()/2, -Vars.spaceship.getHeightScale()/2);
        AffineTransform at = AffineTransform.getTranslateInstance(Vars.spaceship.getX(), Vars.spaceship.getY());
        at.rotate(Math.toRadians(Vars.spaceship.getOrientation()),Vars.spaceship.getWidthScale()/2, Vars.spaceship.getHeightScale()/2);
        at.scale(Vars.getScalfactor(), Vars.getScalfactor());
        g2d.drawImage(Vars.sp_ship_1, at, null);
        g2d.setTransform(oldPos);



        for (Enemy enemy : Vars.enemyList){
            oldPos = g2d.getTransform();
            at = AffineTransform.getTranslateInstance(enemy.getX(), enemy.getY());
            at.scale(Vars.getScalfactor(), Vars.getScalfactor());
            g2d.drawImage(enemy.getImage(), at, null);
            g2d.setTransform(oldPos);
        }

        g2d.setColor(Color.BLUE);
        g2d.drawRect(0, 0,300,300);

        repaint();
    }
}