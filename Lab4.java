public class Lab4 {
    public static void main(String[] args) {
        World world1 = new World("Бренный мир", 10, 10);
        Human human0 = new Human("Знайка");
        Human human1 = new Human();
        Human human2 = new Human();
        Human human3 = new Human("Винтик");
        MaterialObject waterPipe0 = new MaterialObject("Водосточная труба дома");
        MaterialObject waterPipe1 = new MaterialObject("Водосточная труба мастерской");
        MaterialObject doorStep = new MaterialObject("Порог");
        MaterialObject vane = new MaterialObject("Флюгель");
        Door door = new Door("Дверь");
        Rope rope1 = new Rope("Верёвка", 10);
        Wind wind1 = new Wind("Порыв ветра");
        world1.addToWorld(human0, 4, 0);
        world1.addToWorld(human1, 1, 0);
        world1.addToWorld(human2, 1, 0);
        world1.addToWorld(human3, -1, -1);
        world1.addToWorld(waterPipe0, 8, 0, 8, 5);
        waterPipe0.setCanInteractWithHuman(true);
        world1.addToWorld(waterPipe1, 3, 0, 3, 4);
        waterPipe1.setCanInteractWithHuman(true);
        world1.addToWorld(doorStep, 4, 0, 4, 0);
        doorStep.setCanInteractWithHuman(true);
        world1.addToWorld(vane, 2, 4, 2, 5);
        vane.setCanInteractWithHuman(true);
        vane.setCanInteractWithWind(true);
        world1.addToWorld(door, 1, 0, 1, 1);
        world1.addToWorld(rope1, 0, 0, 0, 0);
        human1.setRope(rope1);
        human2.setRope(rope1);
        world1.addToWorld(wind1, 4, 5, 8, 8);
        System.out.println("-----------------------------------------------------------------------");

        human0.changeAngleOfTheBody(-14);
        human0.pushOnSmth(doorStep, -2, 5);
        human0.catchSmth(vane);
        human0.move(1);
        human0.climb(-4, waterPipe1);
        human0.move(-2);
        human0.useDoor(door);
        human0.beTracked(human1, human2);
        world1.waitSomeMinutes(1);
        human0.useDoor(door);
        human0.move(6);
        human1.move(7);
        human2.move(7);
        human0.jump(2, 0);
        human0.tryToFind(human3, Locations.PAVILION);
        try {
            rope1.pullSmb(human0, rope1);
        } catch (CanNotPull ex) {
            System.out.println(ex.getMessage());
        }
        human0.climb(5, waterPipe0);
        human0.setState(States.WANTTOLOOK);
        human0.printState();
        wind1.blow();
        human0.lookAround();
        human0.isKnow();
    }
}