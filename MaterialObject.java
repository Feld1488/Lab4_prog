public class MaterialObject extends WorldObject {
    private boolean canInteractWithHuman = false;
    private boolean canInteractWithWind = false;
    private boolean canBeOpened = false;

    MaterialObject(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString() + "; -> MaterialObject {canInteractWithHuman=" + canInteractWithHuman + ", canInteractWithWind=" + canInteractWithWind + ", canBeOpened=" + canBeOpened + "}";
    }

    void setCanInteractWithHuman(boolean canInteractWithHuman) {
        this.canInteractWithHuman = canInteractWithHuman;
    }

    boolean getCanInteractWithHuman() {
        return canInteractWithHuman;
    }

    void setCanInteractWithWind(boolean canInteractWithWind) {
        this.canInteractWithWind = canInteractWithWind;
    }

    boolean getCanInteractWithWind() {
        return canInteractWithWind;
    }

    public void setCanBeOpened(boolean canBeOpened) {
        this.canBeOpened = canBeOpened;
    }

    public boolean getCanBeOpened() {
        return canBeOpened;
    }
}
