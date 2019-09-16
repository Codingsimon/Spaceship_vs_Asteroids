package app;

public class PointEntry implements Comparable<PointEntry>{
    int points;
    String name;

    PointEntry(int points, String name){
        this.points = points;
        this.name = name;
    }

    @Override
    public int compareTo(PointEntry o) {
        return o.getPoints() - points;
    }

    public int getPoints(){
        return points;
    }

    public String getName(){
        return name;
    }
}
