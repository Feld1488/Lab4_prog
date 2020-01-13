import java.util.Objects;

public class Human implements LookActions, MovingActions {
    private String name;
    private States state;
    private Locations location = Locations.DEFAULT;
    private int x, y;
    private boolean hasRope = false;
    private static int noNameNumber = 1;
    private Rope rope;
    private int angleOfTheBody = 90;
    private boolean faceTurnOnRight = true;
    private PartsOfTheBody[] partsOfTheBodies = new PartsOfTheBody[4];

    Human(String name) {
        this.name = name;
        state = States.DEFAULT;
        PartsOfTheBody hand1 = this.new PartsOfTheBody("Правая рука");
        partsOfTheBodies[0] = hand1;
        PartsOfTheBody hand2 = this.new PartsOfTheBody("Левая рука");
        partsOfTheBodies[1] = hand2;
        PartsOfTheBody leg1 = this.new PartsOfTheBody("Правая нога");
        partsOfTheBodies[2] = leg1;
        PartsOfTheBody leg2 = this.new PartsOfTheBody("Левая нога");
        partsOfTheBodies[3] = leg2;

    }

    Human() {
        this.name = "Безымянный коротышка №" + noNameNumber;
        state = States.DEFAULT;
        ;
        noNameNumber++;
        PartsOfTheBody hand1 = this.new PartsOfTheBody("Правая рука");
        partsOfTheBodies[0] = hand1;
        PartsOfTheBody hand2 = this.new PartsOfTheBody("Левая рука");
        partsOfTheBodies[1] = hand2;
        PartsOfTheBody leg1 = this.new PartsOfTheBody("Правая нога");
        partsOfTheBodies[2] = leg1;
        PartsOfTheBody leg2 = this.new PartsOfTheBody("Левая нога");
        partsOfTheBodies[3] = leg2;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", location=" + location +
                ", x=" + x +
                ", y=" + y +
                ", hasRope=" + hasRope +
                ", rope=" + rope +
                ", angleOfTheBody=" + angleOfTheBody +
                ", faceTurnOnRight=" + faceTurnOnRight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        if (this.hashCode() != human.hashCode()) return false;
        return toString().equals(human.toString());
    }

    void changeAngleOfTheBody(int angleOfTheBody) {
        this.angleOfTheBody = this.angleOfTheBody + angleOfTheBody;
        if (this.angleOfTheBody % 180 == 0)
            System.out.println("Коротышка " + this.getName() + "находится в горизонатальном положении ");
        else if (this.angleOfTheBody % 360 == 270)
            System.out.println("Коротышка " + this.getName() + " повис вверх ногами");
        else if (this.angleOfTheBody % 360 == 90)
            System.out.println("Коротышка " + this.getName() + " принял нормальное положение");
        else if (this.angleOfTheBody % 360 > 90 && this.angleOfTheBody % 360 < 270) {
            if (!faceTurnOnRight)
                System.out.println("Коротышка " + this.getName() + " наклоннился вперёд на " + Math.abs(((this.angleOfTheBody - 90) % 180)) + " градусов");
            else
                System.out.println("Коротышка " + this.getName() + " наклоннился назад на " + Math.abs(((this.angleOfTheBody - 90) % 180)) + " градусов");
        } else if (this.angleOfTheBody % 360 < 90 || this.angleOfTheBody % 360 > 270) {
            if (!faceTurnOnRight)
                System.out.println("Коротышка " + this.getName() + " наклоннился назад на " + Math.abs(((this.angleOfTheBody - 90) % 180)) + " градусов");
            else
                System.out.println("Коротышка " + this.getName() + " наклоннился вперёд на " + Math.abs(((this.angleOfTheBody - 90) % 180)) + " градусов");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, location, x, y, hasRope);
    }

    void setRope(Rope rope) {
        this.hasRope = true;
        this.rope = rope;
        System.out.println("Теперь коротышка " + name + " может пользоваться " + rope.getName());
    }

    boolean getCanPull(Rope rope, Human human) {
        if (this.hasRope && this.rope.equals(rope)) {
            return (Math.pow((human.getCoordinates()[0] - getCoordinates()[0]), 2) + Math.pow((human.getCoordinates()[1] - getCoordinates()[1]), 2)) <= Math.pow(rope.getLength(), 2);
        }
        return false;
    }

    void tryToFind(Human human, Locations location) {
        Human founded = this.lookIn(location, human);
        if (founded != null) {
            this.setState(States.FIND_SMB);
            System.out.println("Коротышка " + this.getName() + " " + this.getState().getTitle() + " " + founded.getName());
        } else System.out.println("Коротышки " + human.getName() + " там нет");
        state = States.DEFAULT;
    }

    void isKnow() {
        Human[] humans = new Human[100];
        int i = 0;
        for (int j = 0; j < World.getHCnt(); j++) {
            for (int k = 0; k < World.getRcnt(); k++) {
                if (World.humArray[j].getCanPull(World.ropeArray[k], this) && World.humArray != null && World.ropeArray[k] != null) {
                    humans[i] = World.humArray[j];
                    i++;
                }
            }
        }
        if (humans[0] != null) {
            state = States.DONTFRIGHT;
            System.out.print(this.getName() + " " + state.getTitle());
            state = States.KNOW;
            System.out.print(", потому что " + state.getTitle());
            System.out.print("коротышки ");
            for (int s = 0; s < i; s++) {
                System.out.print(humans[s].getName() + " ");
            }
            System.out.println("в любой момент могут притянуть " + this.getName() + " к " + humans[0].getLocation().getTitle());
            state = States.DEFAULT;
        } else if (location == Locations.INTHEAIR)
            System.out.println(this.getName() + " испугался, потому что " + state.getTitle() + " и коротышки не могут его притянуть");
        else
            System.out.println(this.getName() + " не испугался, потому что находится на твёрдой поверхности " + getLocation().getTitle());
        state = States.DEFAULT;
    }

    void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int[] getCoordinates() {
        int[] c = new int[2];
        c[0] = x;
        c[1] = y;
        return c;
    }

    String getName() {
        return name;
    }

    private States getState() {
        return state;
    }

    void printState() {
        System.out.println(this.getName() + " " + state.getTitle());
    }

    void setState(States state) {
        this.state = state;
    }

    void setLocation() {
        if (getCoordinates()[0] >= Locations.NEARTHEHOUSE.getX1() && getCoordinates()[0] <= Locations.NEARTHEHOUSE.getX2() && getCoordinates()[1] >= Locations.NEARTHEHOUSE.getY1() && getCoordinates()[1] <= Locations.NEARTHEHOUSE.getY2()) {
            location = Locations.NEARTHEHOUSE;
        } else if (getCoordinates()[0] >= Locations.PAVILION.getX1() && getCoordinates()[0] <= Locations.PAVILION.getX2() && getCoordinates()[1] >= Locations.PAVILION.getY1() && getCoordinates()[1] <= Locations.PAVILION.getY2()) {
            location = Locations.PAVILION;
        } else if (getCoordinates()[0] >= Locations.ROOFOFTHEHOUSE.getX1() && getCoordinates()[0] <= Locations.ROOFOFTHEHOUSE.getX2() && getCoordinates()[1] >= Locations.ROOFOFTHEHOUSE.getY1() && getCoordinates()[1] <= Locations.ROOFOFTHEHOUSE.getY2()) {
            location = Locations.ROOFOFTHEHOUSE;
        } else if (getCoordinates()[0] >= Locations.WORKSHOP.getX1() && getCoordinates()[0] <= Locations.WORKSHOP.getX2() && getCoordinates()[1] >= Locations.WORKSHOP.getY1() && getCoordinates()[1] <= Locations.WORKSHOP.getY2()) {
            location = Locations.WORKSHOP;
        } else if (getCoordinates()[0] >= Locations.ROOFOFTHEWORKSHOP.getX1() && getCoordinates()[0] <= Locations.ROOFOFTHEWORKSHOP.getX2() && getCoordinates()[1] >= Locations.ROOFOFTHEWORKSHOP.getY1() && getCoordinates()[1] <= Locations.ROOFOFTHEWORKSHOP.getY2()) {
            location = Locations.ROOFOFTHEWORKSHOP;
        } else if (getCoordinates()[0] == -1 && getCoordinates()[1] == -1) {
            location = Locations.DEFAULT;
        } else {
            location = Locations.INTHEAIR;
        }
    }

    Locations getLocation() {
        return location;
    }

    private Locations.HigherThenLocations getHigherThenLocations() {
        if (getCoordinates()[0] >= Locations.NEARTHEHOUSE.getX1() && getCoordinates()[0] <= Locations.NEARTHEHOUSE.getX2() && !(getCoordinates()[1] >= Locations.NEARTHEHOUSE.getY1() && getCoordinates()[1] <= Locations.NEARTHEHOUSE.getY2())) {
            return Locations.HigherThenLocations.HIGHERTHENROOF;
        } else if (getCoordinates()[0] >= Locations.PAVILION.getX1() && getCoordinates()[0] <= Locations.PAVILION.getX2() && !(getCoordinates()[1] >= Locations.PAVILION.getY1() && getCoordinates()[1] <= Locations.PAVILION.getY2())) {
            return Locations.HigherThenLocations.HIGHERTHENPAVILION;
        } else if (getCoordinates()[0] >= Locations.WORKSHOP.getX1() && getCoordinates()[0] <= Locations.WORKSHOP.getX2() && !(getCoordinates()[1] >= Locations.WORKSHOP.getY1() && getCoordinates()[1] <= Locations.WORKSHOP.getY2())) {
            return Locations.HigherThenLocations.HIGHERTHENWORKSHOP;
        } else {
            return Locations.HigherThenLocations.HIGHEROFDEFAULT;
        }
    }

    public Rope getRope() {
        return rope;
    }

    @Override
    public void lookAround() {
        if (location != Locations.INTHEAIR) {
            System.out.println("Коротышка " + this.getName() + " огляделся по сторонам");
            for (int i = 0; i < World.getHCnt(); i++) {
                if (World.humArray[i].getLocation() == this.getLocation()) {
                    if (World.humArray[i] != this) {
                        this.setState(States.FIND_SMB);
                        System.out.println("Коротышка " + this.getName() + " " + this.getState().getTitle() + " " + World.humArray[i].getName() + " в локации " + this.getLocation().getTitle());
                    }
                }
            }
        }
    }

    @Override
    public Human lookIn(Locations where, Human human) {
        System.out.println("Коротышка " + this.getName() + " заглянул в " + where.getTitle());
        for (int i = 0; i < World.getHCnt(); i++) {
            if (World.humArray[i].getLocation() == where) {
                if (World.humArray[i].equals(human)) {
                    return World.humArray[i];
                }
            }
        }
        return null;
    }

    @Override
    public void beTracked(Human... humans) {
        System.out.print("Коротышки ");
        for (Human human : humans) {
            human.setCoordinates(getCoordinates()[0], getCoordinates()[1]);
            human.setLocation();
            System.out.print(human.getName() + ", ");
        }
        System.out.println("следят за действиями " + getName() + " в локации " + this.getLocation().getTitle());

    }

    @Override
    public void jump(int x, int y) {
        faceTurnOnRight = (x > 0);
        Locations location = this.getLocation();
        setCoordinates(getCoordinates()[0] + x, getCoordinates()[1] + y);
        setLocation();
        if (location == this.getLocation() || this.location == Locations.INTHEAIR) {
            InteractWithLocationsHelper interactWithLocationsHelper = new InteractWithLocationsHelper() {
                @Override
                public Locations direction(Human human) {
                    if (x > 0) {
                        for (int i = human.getCoordinates()[0] - x + 1; i < World.getSizex(); i++) {
                            if (i == Locations.NEARTHEHOUSE.getX1()) return Locations.NEARTHEHOUSE;
                            else if (i == Locations.PAVILION.getX1()) return Locations.PAVILION;
                            else if (i == Locations.ROOFOFTHEHOUSE.getX1()) return Locations.ROOFOFTHEHOUSE;
                            else if (i == Locations.WORKSHOP.getX1()) return Locations.WORKSHOP;
                        }
                    } else {
                        for (int i = human.getCoordinates()[0] - x - 1; i > 0; i--) {
                            if (i == Locations.NEARTHEHOUSE.getX2()) return Locations.NEARTHEHOUSE;
                            else if (i == Locations.PAVILION.getX2()) return Locations.PAVILION;
                            else if (i == Locations.ROOFOFTHEHOUSE.getX2()) return Locations.ROOFOFTHEHOUSE;
                            else if (i == Locations.WORKSHOP.getX2()) return Locations.WORKSHOP;
                        }
                    }
                    return Locations.INTHEAIR;
                }
            };
            if (location == this.getLocation()) {
                System.out.println("Коротышка " + this.name + " прыгает по локации " + getLocation().getTitle() + " в направлении " + interactWithLocationsHelper.direction(this).getTitle());
            } else if (this.location == Locations.INTHEAIR)
                System.out.println("Коротышка " + this.name + " полетел в направлении " + interactWithLocationsHelper.direction(this).getTitle() + ", которая находится неподалёку от " + interactWithLocationsHelper.nearLocation(interactWithLocationsHelper.direction(this)).getTitle());
            System.out.println("Коротышка " + this.name + " не рассчитал толчка и теперь находится " + this.getHigherThenLocations().getTitle());
        } else
            System.out.println("Коротышка " + this.name + " одним прыжком достиг локации " + getLocation().getTitle());
    }

    @Override
    public void move(int x) {
        faceTurnOnRight = (x > 0);
        InteractWithLocationsHelper interactWithLocationsHelper = new InteractWithLocationsHelper() {
            @Override
            public Locations direction(Human human) {
                if (x > 0) {
                    for (int i = x + 1; i < World.getSizex(); i++) {
                        if (i == Locations.NEARTHEHOUSE.getX1()) return Locations.NEARTHEHOUSE;
                        else if (i == Locations.PAVILION.getX1()) return Locations.PAVILION;
                        else if (i == Locations.ROOFOFTHEHOUSE.getX1()) return Locations.ROOFOFTHEHOUSE;
                        else if (i == Locations.WORKSHOP.getX1()) return Locations.WORKSHOP;
                    }
                } else {
                    for (int i = x - 1; i > 0; i--) {
                        if (i == Locations.NEARTHEHOUSE.getX2()) return Locations.NEARTHEHOUSE;
                        else if (i == Locations.PAVILION.getX2()) return Locations.PAVILION;
                        else if (i == Locations.ROOFOFTHEHOUSE.getX2()) return Locations.ROOFOFTHEHOUSE;
                        else if (i == Locations.WORKSHOP.getX2()) return Locations.WORKSHOP;
                    }
                }
                return Locations.INTHEAIR;
            }
        };
        Locations location = this.getLocation();
        setCoordinates(getCoordinates()[0] + x, getCoordinates()[1]);
        setLocation();
        if (location == this.getLocation()) {
            if (interactWithLocationsHelper.direction(this) != Locations.INTHEAIR) {
                System.out.println("Коротышка " + this.name + " перемещается по локации " + getLocation().getTitle() + " в сторону " + interactWithLocationsHelper.direction(this).getTitle());
            } else
                System.out.println("Коротышка " + this.name + " перемещается по локации " + getLocation().getTitle());
        } else
            System.out.println("Коротышка " + this.name + " переместился из локации " + location.getTitle() + " в локацию " + getLocation().getTitle());
    }

    @Override
    public void climb(int y, MaterialObject materialObject) {
        if (materialObject.getCanInteractWithHuman()) {
            Locations location = this.getLocation();
            if (y > 0) {
                if (materialObject.getCoordinates()[0] == this.getCoordinates()[0]) {
                    if (materialObject.getCoordinates()[3] >= this.getCoordinates()[1] + y) {
                        setCoordinates(getCoordinates()[0], getCoordinates()[1] + y);
                        setLocation();
                        if (location == this.getLocation()) {
                            System.out.println("Коротышка " + this.name + " вскарабкался по " + materialObject.getName() + ", но всё ещё находится в локации " + getLocation().getTitle());
                        } else
                            System.out.println("Коротышка " + this.name + " вскарабклася по " + materialObject.getName() + " в локацию " + getLocation().getTitle());
                    } else
                        System.out.println("Длины " + materialObject.getName() + " не хватает, чтобы подняться так высоко...");
                } else
                    System.out.println("Коротышка " + this.name + " не смог найти " + materialObject.getName() + " поблизости...");
            } else {
                if (materialObject.getCoordinates()[0] == this.getCoordinates()[0]) {
                    if (materialObject.getCoordinates()[1] <= this.getCoordinates()[1] + y) {
                        setCoordinates(getCoordinates()[0], getCoordinates()[1] + y);
                        setLocation();
                        if (location == this.getLocation()) {
                            System.out.println("Коротышка " + this.name + " спустился по " + materialObject.getName() + ", но всё ещё находится в локации " + getLocation().getTitle());
                        } else
                            System.out.println("Коротышка " + this.name + " спустился по " + materialObject.getName() + " в локацию " + getLocation().getTitle());
                    }
                } else
                    System.out.println("Длины " + materialObject.getName() + " не хватает, чтобы спуститься на такую высоту...");
            }
        }
    }

    @Override
    public void pushOnSmth(MaterialObject materialObject, int x, int y) {
        if (materialObject.getCanInteractWithHuman() && getCoordinates()[0] >= materialObject.getCoordinates()[0] && getCoordinates()[1] >= materialObject.getCoordinates()[1] && getCoordinates()[0] <= materialObject.getCoordinates()[2] && getCoordinates()[1] <= materialObject.getCoordinates()[3])
            partsOfTheBodies[2].pushOnU(materialObject, x, y, partsOfTheBodies[2], partsOfTheBodies[3]);
        else System.out.println(name + " не может найти " + materialObject.getName() + ", чтобы оттолкнуться");
    }

    @Override
    public void catchSmth(MaterialObject materialObject) {
        if (materialObject.getCanInteractWithHuman() && getCoordinates()[0] >= materialObject.getCoordinates()[0] && getCoordinates()[1] >= materialObject.getCoordinates()[1] && getCoordinates()[0] <= materialObject.getCoordinates()[2] && getCoordinates()[1] <= materialObject.getCoordinates()[3])
            partsOfTheBodies[0].catchU(materialObject, partsOfTheBodies[0]);
        else System.out.println(name + " не может найти " + materialObject.getName() + ", чтобы схватиться");
    }

    @Override
    public void useDoor(Door door) {
        class BehindTheDoor {
            private String name;

            private BehindTheDoor(String name) {
                this.name = name;
            }

            private Human[] humans = new Human[10];
            private int Hcnt = 0;

            private void addToBehindTheDoor(Human human) {
                System.out.println("Коротышка " + human.getName() + " проник в " + this.name);
                humans[Hcnt] = human;
                Hcnt++;
            }

            private void removeAtBehindTheDoor(Human human) {
                System.out.println("Коротышка " + human.getName() + " выглянул из " + this.name);
                for (int i = 0; i < Hcnt; i++) {
                    if (humans[i].equals(human)) humans[i] = null;
                }
            }
        }
        BehindTheDoor behindTheDoor = new BehindTheDoor(this.getLocation().getTitle());
        if (!door.getIsOpened()) {
            door.setOpened(!door.getIsOpened());
            behindTheDoor.addToBehindTheDoor(this);
        } else {
            behindTheDoor.removeAtBehindTheDoor(this);
            door.setOpened(!door.getIsOpened());
        }
    }

    class PartsOfTheBody {
        private String name;

        PartsOfTheBody(String name) {
            this.name = name;
        }

        void catchU(MaterialObject materialObject, PartsOfTheBody... partsOfTheBodies) {
            System.out.print("Коротышка " + getName() + " схватился ");
            for (PartsOfTheBody each : partsOfTheBodies) {
                if (each != null) System.out.print(each.name + " ");
            }
            System.out.println("за " + materialObject.getName());
            climb(materialObject.getCoordinates()[1] - materialObject.getCoordinates()[3], materialObject);
        }

        void pushOnU(MaterialObject materialObject, int x, int y, PartsOfTheBody... partsOfTheBodies) {
            System.out.print("Коротышка " + getName() + " оттолкнулся ");
            for (PartsOfTheBody each : partsOfTheBodies) {
                if (each != null) System.out.print(each.name + " ");
            }
            System.out.println("от " + materialObject.getName());
            jump(x, y);
        }
    }
}
