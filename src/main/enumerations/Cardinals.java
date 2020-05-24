package main.enumerations;

public enum Cardinals {
    N("North"), S("South"), E("East"), W("West");
    private String description;

    Cardinals(String desc) {
        description = desc;
    }

    public String getDescription() {
        return this.description;
    }
}
