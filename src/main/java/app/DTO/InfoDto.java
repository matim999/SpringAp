package app.DTO;

import lombok.Data;

public @Data class InfoDto {
    private String title;
    private String description;
    private String profile;

    public InfoDto(String title, String description, String profile) {
        this.title = title;
        this.description = description;
        this.profile = profile;
    }
}
