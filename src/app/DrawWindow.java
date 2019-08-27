package app;
import java.awt.*;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class DrawWindow extends JComponent{
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0,0,Vars.canvas.getWidth(), Vars.canvas.getHeight());

        Graphics2D g2d = (Graphics2D)g;
        g.drawImage(Vars.sp_ship_1, 50, 50, Window.scalfactor(Vars.sp_ship_1.getWidth()),Window.scalfactor(Vars.sp_ship_1.getHeight()), null);

        repaint();
    }
}