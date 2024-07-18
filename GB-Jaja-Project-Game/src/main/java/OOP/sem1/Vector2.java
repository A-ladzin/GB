package OOP.sem1;

public class Vector2 {

    int posX;
    int posY;

    public Vector2(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public float calculateDistance(Vector2 posEnemy) {
        double distance = Math.sqrt(Math.pow(posEnemy.posY - posY, 2) + Math.pow(posEnemy.posX - posX, 2));
        return (float) distance;
    }

    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }

    public void setX(int x){posX = x;}
    public void setY(int y){posY = y;}

    @Override
    public String toString() {
        return "["+posX+":"+posY+"]";
    }
}
