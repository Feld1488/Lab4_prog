abstract class WorldObject {
    private String name;
    protected int x1, y1, x2, y2;
    private Locations location = Locations.DEFAULT;

    public WorldObject(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rope {" + "name='" + name +
                "', location=" + location +
                ", x1=" + x1 + ", y1=" + y1 +
                ", x2=" + x2 + ", y2=" + y2 + "}";
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int[] getCoordinates() {
        int[] c = new int[4];
        c[0] = this.x1;
        c[1] = this.y1;
        c[2] = this.x2;
        c[3] = this.y2;
        return c;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation() {
        if (getCoordinates()[0] <= Locations.NEARTHEHOUSE.getX2() && getCoordinates()[0] >= Locations.NEARTHEHOUSE.getX1() && getCoordinates()[1] >= Locations.NEARTHEHOUSE.getY1() && getCoordinates()[1] <= Locations.NEARTHEHOUSE.getY2()) {
            location = Locations.NEARTHEHOUSE;
        } else if (getCoordinates()[0] <= Locations.ROOFOFTHEHOUSE.getX2() && getCoordinates()[0] >= Locations.ROOFOFTHEHOUSE.getX1() && getCoordinates()[1] >= Locations.ROOFOFTHEHOUSE.getY1() && getCoordinates()[1] <= Locations.ROOFOFTHEHOUSE.getY2()) {
            location = Locations.ROOFOFTHEHOUSE;
        } else if (getCoordinates()[0] >= Locations.WORKSHOP.getX1() && getCoordinates()[0] <= Locations.WORKSHOP.getX2() && getCoordinates()[1] >= Locations.WORKSHOP.getY1() && getCoordinates()[1] <= Locations.WORKSHOP.getY2()) {
            location = Locations.WORKSHOP;
        } else if (getCoordinates()[0] >= Locations.PAVILION.getX1() && getCoordinates()[0] <= Locations.PAVILION.getX2() && getCoordinates()[1] >= Locations.PAVILION.getY1() && getCoordinates()[1] <= Locations.PAVILION.getY2()) {
            location = Locations.PAVILION;
        } else if (getCoordinates()[0] >= Locations.ROOFOFTHEWORKSHOP.getX1() && getCoordinates()[0] <= Locations.ROOFOFTHEWORKSHOP.getX2() && getCoordinates()[1] >= Locations.ROOFOFTHEWORKSHOP.getY1() && getCoordinates()[1] <= Locations.ROOFOFTHEWORKSHOP.getY2()) {
            location = Locations.ROOFOFTHEWORKSHOP;
        } else location = Locations.INTHEAIR;
    }
}
