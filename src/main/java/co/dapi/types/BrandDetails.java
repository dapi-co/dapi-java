package co.dapi.types;

public class BrandDetails {
    private final String logo;
    private final String name;

    public BrandDetails(String logo, String name) {
        this.logo = logo;
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }
}
