package by.parakhonka.reverse.entity;

public enum Role {
    USER("USER");
    String userProfileType;

    private Role(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }

}
