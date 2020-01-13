import java.util.Objects;

public class Rope extends MaterialObject implements RopeActions {
    private int length;

    Rope(String name, int length) {
        super(name);
        this.length = Math.max(length, 0);
        super.setCanInteractWithHuman(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rope rope = (Rope) o;
        if (this.hashCode() != rope.hashCode()) return false;
        return toString().equals(rope.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLength());
    }

    @Override
    public String toString() {
        return super.toString() + "; -> Rope {length=" + length + "}";
    }

    public void pullSmb(Human human, Rope rope) throws CanNotPull {
        Human[] humans = new Human[100];
        int i = 0;
        for (int j = 0; j < World.getHCnt(); j++) {
            if (World.humArray[j].getCanPull(rope, human)) {
                humans[i] = World.humArray[j];
                if (i != 0 && (humans[i].getCoordinates()[0] != humans[0].getCoordinates()[0] || humans[i].getCoordinates()[1] != humans[0].getCoordinates()[1])) {
                    humans[i].setCoordinates(humans[0].getCoordinates()[0], humans[0].getCoordinates()[1]);
                    System.out.println("Коротышка " + humans[i].getName() + " подошёл к коротышке " + humans[0].getName() + ", чтобы потянуть за верёвку");
                }
                i++;
            }
        }
        if (humans[0] != null) {
            rope.setCoordinates(humans[0].getCoordinates()[0], humans[0].getCoordinates()[1], human.getCoordinates()[0], human.getCoordinates()[1]);
            rope.setCoordinates(humans[0].getCoordinates()[0], humans[0].getCoordinates()[1], humans[0].getCoordinates()[0], humans[0].getCoordinates()[1]);
            human.setCoordinates(rope.getCoordinates()[2], rope.getCoordinates()[3]);
            human.setLocation();
            System.out.print("Коротышки ");
            for (int j = 0; j < i; j++) {
                System.out.print(humans[j].getName() + " ");
            }
            System.out.println("притянули " + human.getName() + " к " + human.getLocation().getTitle());
        } else
            throw new CanNotPull("Никто не может притянуть " + human.getName() + " с помощью " + rope.getName() + "...");
    }

    int getLength() {
        return length;
    }
}
