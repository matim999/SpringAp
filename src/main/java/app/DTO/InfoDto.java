package app.DTO;

public class InfoDto {
    private final String title;
    private final String description;
    private final String profile;

    public InfoDto(String title, String description, String profile) {
        this.title = title;
        this.description = description;
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProfile() {
        return profile;
    }
}
