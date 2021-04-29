package co.dapi.types;

import java.util.Optional;

public class IdentityAddress {
    private final String flat;
    private final String building;
    private final String full;
    private final String area;
    private final String poBox;
    private final String city;
    private final String state;
    private final String country;

    public IdentityAddress(String flat, String building, String full, String area, String poBox, String city, String state, String country) {
        this.flat = flat;
        this.building = building;
        this.full = full;
        this.area = area;
        this.poBox = poBox;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Optional<String> getFlat() {
        return Optional.ofNullable(flat);
    }

    public Optional<String> getBuilding() {
        return Optional.ofNullable(building);
    }

    public Optional<String> getFull() {
        return Optional.ofNullable(full);
    }

    public Optional<String> getArea() {
        return Optional.ofNullable(area);
    }

    public Optional<String> getPoBox() {
        return Optional.ofNullable(poBox);
    }

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }

    public Optional<String> getState() {
        return Optional.ofNullable(state);
    }

    public Optional<String> getCountry() {
        return Optional.ofNullable(country);
    }
}
