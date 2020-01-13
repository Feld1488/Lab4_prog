public interface MovingActions {
    void jump(int x, int y);

    void move(int x);

    void climb(int y, MaterialObject materialObject);

    void pushOnSmth(MaterialObject materialObject, int x, int y);

    void catchSmth(MaterialObject materialObject);

    void useDoor(Door door);
}

