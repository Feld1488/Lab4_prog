public interface InteractWithLocationsHelper {
    Locations direction(Human human);

    default Locations nearLocation(Locations locations) {
        if (locations == Locations.WORKSHOP) return Locations.NEARTHEHOUSE;
        if (locations == Locations.NEARTHEHOUSE) return Locations.WORKSHOP;
        if (locations == Locations.PAVILION) return Locations.NEARTHEHOUSE;
        if (locations == Locations.ROOFOFTHEHOUSE) return Locations.NEARTHEHOUSE;
        return null;
    }
}
