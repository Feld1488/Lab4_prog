class World {
    private String name;
    private static int sizex;
    private static int sizey;
    static Human[] humArray = new Human[10];
    static MaterialObject[] materialObjectArray = new MaterialObject[10];
    static Rope[] ropeArray = new Rope[10];
    static Wind[] windArray = new Wind[10];
    private static int Hcnt = 0;
    private static int Mcnt = 0;
    private static int Rcnt = 0;
    private static int Wcnt = 0;

    World(String name, int sizex, int sizey) {
        this.name = name;
        World.sizex = sizex;
        World.sizey = sizey;
    }

    void addToWorld(Human human, int x, int y) {
        humArray[Hcnt] = human;
        Hcnt++;
        try {
            if ((x < 0 || x > getSizex() || y < 0 || y > sizey) && (x != -1 || y != -1))
                throw new OutOfTheWorldException(human.getName() + " не может появится вне этого мира. ");
            human.setCoordinates(x, y);
            human.setLocation();

        } catch (OutOfTheWorldException ex) {
            human.setCoordinates(4, 0);
            human.setLocation();
            System.out.println(ex.getMessage() + human.getName() + " перенесён в локацию " + human.getLocation().getTitle());

        }
        System.out.println("Коротышка " + human.getName() + " появился в " + name + " в локации " + human.getLocation().getTitle());
    }

    void addToWorld(MaterialObject materialObject, int x1, int y1, int x2, int y2) {
        try {
            if (x1 < 0 || x1 > getSizex() || y1 < 0 || y1 > sizey || x2 < 0 || x2 > getSizex() || y2 < 0 || y2 > sizey)
                throw new OutOfTheWorldException(materialObject.getName() + " не может появится вне этого мира. ");
            materialObject.setCoordinates(x1, y1, x2, y2);
            materialObject.setLocation();
            System.out.println(materialObject.getName() + " появился в " + name + " в локации " + materialObject.getLocation().getTitle());
            materialObjectArray[Mcnt] = materialObject;
            Mcnt++;
        } catch (OutOfTheWorldException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void addToWorld(Rope rope, int x1, int y1, int x2, int y2) {
        try {
            if (x1 < 0 || x1 > getSizex() || y1 < 0 || y1 > sizey || x2 < 0 || x2 > getSizex() || y2 < 0 || y2 > sizey)
                throw new OutOfTheWorldException(rope.getName() + " не может появится вне этого мира. ");
            ropeArray[Rcnt] = rope;
            Rcnt++;
            rope.setCoordinates(x1, y1, x2, y2);
            rope.setLocation();
            System.out.println(rope.getName() + " появился в " + name + " в локации " + rope.getLocation().getTitle());
        } catch (OutOfTheWorldException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void addToWorld(Wind wind, int x1, int y1, int x2, int y2) {
        try {
            if (x1 < 0 || x1 > getSizex() || y1 > sizey || y1 < 0 || x2 < 0 || x2 > getSizex() || y2 < 0 || y2 > sizey)
                throw new OutOfTheWorldException(wind.getName() + " не может появится вне этого мира. ");
            windArray[Wcnt] = wind;
            Wcnt++;
            wind.setCoordinates(x1, y1, x2, y2);
            wind.setLocation();
            System.out.println(wind.getName() + " появился в " + name + " в локации " + wind.getLocation().getTitle());
            for (int k = 0; k < Mcnt; k++) {
                if (materialObjectArray[k].getCanInteractWithWind()) {
                    if (x1 < x2) System.out.println(materialObjectArray[k].getName() + " повернулся направо");
                    if (x1 > x2) System.out.println(materialObjectArray[k].getName() + " повернулся налево");
                }
            }
        } catch (OutOfTheWorldException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void waitSomeMinutes(int time) {
        System.out.println("Спустя " + time + " минуту...");
    }

    static int getHCnt() {
        return Hcnt;
    }

    static int getMcnt() {
        return Mcnt;
    }

    static int getRcnt() {
        return Rcnt;
    }

    static int getWcnt() {
        return Wcnt;
    }

    static int getSizex() {
        return sizex;
    }

    static int getSizey() {
        return sizey;
    }

}
