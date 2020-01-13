class Wind extends WorldObject {
    Wind(String name) {
        super(name);
    }

    void blow() {
        boolean blowed = false;
        for (int j = 0; j < World.getHCnt(); j++) {
            if (World.humArray[j].getCoordinates()[0] >= this.getCoordinates()[0] && World.humArray[j].getCoordinates()[1] >= this.getCoordinates()[1] && World.humArray[j].getCoordinates()[0] <= this.getCoordinates()[2] && World.humArray[j].getCoordinates()[1] <= this.getCoordinates()[3]) {
                blowed = true;
                Locations location = World.humArray[j].getLocation();
                World.humArray[j].setCoordinates(this.getCoordinates()[2] + 1, World.humArray[j].getCoordinates()[1]);
                World.humArray[j].setLocation();
                World.humArray[j].setState(States.DEFAULT);
                if (location != World.humArray[j].getLocation()) {
                    System.out.println(this.getName() + " сдул " + World.humArray[j].getName() + " c " + location.getTitle() + " и понёс в " + World.humArray[j].getLocation().getTitle());
                } else
                    System.out.println(this.getName() + " несёт " + World.humArray[j].getName() + " по локации " + World.humArray[j].getLocation().getTitle());
            }
        }
        if (!blowed) {
            System.out.println("К сожалению, " + this.getName() + " в этот раз никого не сдул...");

        }
    }
}
