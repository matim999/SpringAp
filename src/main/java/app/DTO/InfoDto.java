package app.DTO;

public class InfoDto {
    private final String title;
    private final String description;

    public InfoDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
