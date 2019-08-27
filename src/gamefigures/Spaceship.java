package gamefigures;

public class Spaceship{
    int posx = 200;
    int posy = 200;
    int velocity = 0;
    public int goooooo = 23;

    public Spaceship(){
        System.out.println("ggggooogil");
    }

    public void update(long deltaTime){
        posx = (int)((long)posx + (long)velocity * deltaTime);
    }
}