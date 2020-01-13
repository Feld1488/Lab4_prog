class Door extends MaterialObject {
    private boolean isOpened = false;

    Door(String name) {
        super(name);
        super.setCanInteractWithHuman(true);
    }

    void setOpened(boolean opened) {
        System.out.print(this.getName());
        if (opened) System.out.println(" открыта");
        else System.out.println(" закрыта");
        isOpened = opened;
    }

    boolean getIsOpened() {
        return isOpened;
    }
}
