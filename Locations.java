public enum Locations {

    WORKSHOP("Мастерская", 0, 0, 3, 3),
    ROOFOFTHEWORKSHOP("На крыше мастерской", 0, 4, 3, 4),
    NEARTHEHOUSE("Около дома", 4, 0, 8, 4),
    PAVILION("Беседка", 9, 0, 11, 2),
    ROOFOFTHEHOUSE("На крыше дома", 4, 5, 8, 5),
    INTHEAIR("Над землёй", 0, 0, 10, 10),
    DEFAULT("Вне этого мира", -1, -1, 11, 11);

    private String title;
    private int x1, y1, x2, y2;

    Locations(String title, int x1, int y1, int x2, int y2) {
        this.title = title;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public String getTitle() {
        return title;
    }

    public int getY1() {
        return y1;
    }

    public int getX1() {
        return x1;
    }

    public int getY2() {
        return y2;
    }

    public int getX2() {
        return x2;
    }

    public enum HigherThenLocations {
        HIGHERTHENWORKSHOP("выше, чем Мастерская", Locations.WORKSHOP.x1, Locations.WORKSHOP.x2),
        HIGHERTHENROOF("Над Крышей дома", Locations.NEARTHEHOUSE.x1, Locations.NEARTHEHOUSE.x2),
        HIGHERTHENPAVILION("выше, чем Беседка", Locations.PAVILION.x1, Locations.PAVILION.x2),
        HIGHEROFDEFAULT("Заполняет ПСЖ за себя и одногруппников", Locations.DEFAULT.x1, Locations.DEFAULT.x2);

        String title;
        private int x1, x2;

        HigherThenLocations(String title, int x1, int x2) {
            this.title = title;
            this.x1 = x1;
            this.x2 = x2;
        }

        int getX2() {
            return x2;
        }

        int getX1() {
            return x1;
        }

        String getTitle() {
            return title;
        }
    }
}
