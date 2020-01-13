public interface LookActions {
    void lookAround();

    Human lookIn(Locations where, Human human);

    void beTracked(Human... humans);
}

