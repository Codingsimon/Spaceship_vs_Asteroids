package app;
import gamefigures.Enemy;
import gamefigures.Explosion;
import gamefigures.FlyingObject;
import gamefigures.Projectile;
import gamefigures.items.Item;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JComponent;


@SuppressWarnings("serial")
public class DrawCanvas extends JComponent{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d =(Graphics2D)g;

        drawBackground(g2d);


        AffineTransform oldPos;

        if (Vars.gameRunning == true) {

            drawSpaceship(g2d);
            drawEnemy(g2d);
            drawItem(g2d);
            drawProjectile(g2d);
            drawExplosion(g2d);
            drawHearts(g2d);
            drawPointCounter(g);

        } else {
            g.setColor(Color.WHITE);
            g.setFont(Vars.pixelfont);
//            drawGame0ver(g);
            drawHighscoreScreen(g);
        }


        repaint();
    }

    private void drawBackground(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,Vars.canvas.getWidth(), Vars.canvas.getHeight());
    }

    private void drawSpaceship(Graphics2D g2d){
        AffineTransform oldPos;
        oldPos = g2d.getTransform();
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
    }

    private void drawEnemy(Graphics2D g2d){
        AffineTransform oldPos;

        ArrayList<Enemy> tempEnemyList = (ArrayList<Enemy>) Vars.enemyList.clone();
        for (Enemy enemy : tempEnemyList) {
            oldPos = g2d.getTransform();

            AffineTransform at = AffineTransform.getTranslateInstance(enemy.getX(), enemy.getY());
            at = AffineTransform.getTranslateInstance(enemy.getX(), enemy.getY());
            at.scale(Vars.getScalfactor(), Vars.getScalfactor());
            g2d.drawImage(enemy.getImage(), at, null);
            g2d.setTransform(oldPos);
        }
    }

    private void drawItem(Graphics2D g2d){
        AffineTransform oldPos;

        ArrayList<Item> tempItemList = (ArrayList<Item>) Vars.itemList.clone();
        for (Item item : tempItemList) {
            oldPos = g2d.getTransform();
            AffineTransform at2 = AffineTransform.getTranslateInstance(item.getX(), item.getY());
            at2.scale(Vars.getScalfactor(), Vars.getScalfactor());
            at2.rotate(Math.toRadians(item.getOrientation()), 1, 1);
            g2d.drawImage(item.getImage(), at2, null);
            g2d.setTransform(oldPos);
        }
    }

    private void drawProjectile(Graphics2D g2d){
        AffineTransform oldPos;
        //draw Projectile
        ArrayList<Projectile> temProjectlieList = (ArrayList<Projectile>) Vars.projectileList.clone();
        for (Projectile projectile : temProjectlieList) {
            oldPos = g2d.getTransform();

            AffineTransform at2 = AffineTransform.getTranslateInstance(projectile.getX(), projectile.getY());
            at2.scale(Vars.getScalfactor(), Vars.getScalfactor());
            at2.rotate(Math.toRadians(projectile.getOrientation()), 1, 1);
            g2d.drawImage(projectile.getImage(), at2, null);
            g2d.setTransform(oldPos);
        }
    }

    private void drawExplosion(Graphics2D g2d){
        AffineTransform oldPos;

        //draw Explosion
        ArrayList<Explosion> tempExplosion = (ArrayList<Explosion>) Vars.explosionList.clone();
        for (Explosion x : tempExplosion) {
            oldPos = g2d.getTransform();
            AffineTransform at2 = AffineTransform.getTranslateInstance(x.getX(), x.getY());
            at2.scale(Vars.getScalfactor(), Vars.getScalfactor());
            g2d.drawImage(x.getImage(), at2, null);
            g2d.setTransform(oldPos);
        }
    }

    private void drawHearts(Graphics2D g2d){
        AffineTransform oldPos;
        for (int i = 0; i < Vars.gameManager.getLives(); i++) {
            oldPos = g2d.getTransform();
            AffineTransform at2 = AffineTransform.getTranslateInstance((Vars.gameWidth - i * 40) - 80, Vars.gameHeight - 90);
            at2.scale(Vars.getScalfactor() - 1, Vars.getScalfactor() - 1);
            g2d.drawImage(Vars.sp_heart, at2, null);
            g2d.setTransform(oldPos);
        }
    }

    private void drawPointCounter(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(Vars.pixelfont);
        g.drawString("" + Vars.gameManager.getFormatedPoints(), 20, 40);
    }

    private void drawGame0ver(Graphics g){
        g.drawString("Game Over", Vars.gameWidth/2 - 55, 300);
        g.drawString("" + Vars.gameManager.getFormatedPoints(), Vars.gameWidth/2, 350);
    }

    private void drawHighscoreScreen(Graphics g){
        ArrayList<PointEntry> highscoreList = Vars.listWriter.getList();
        int counter = 0;

        for(PointEntry entry : highscoreList) {
            Integer points = entry.getPoints();
            String name = entry.getName();
            counter++;

            if (counter >= 10){
                break;
            }

            g.drawString(points + " ............ " + name,200,100 + counter * 50);
        }
    }

}