package app.entity;

public enum Mpaa_rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private String rating;

    Mpaa_rating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public static Mpaa_rating fromDbToEntity(String rating) {
        switch (rating) {
            case "G":
                return Mpaa_rating.G;
            case "PG":
                return Mpaa_rating.PG;
            case "PG-13":
                return Mpaa_rating.PG13;
            case "R":
                return Mpaa_rating.R;
            case "NC-17":
                return Mpaa_rating.NC17;
            default:
                throw new IllegalArgumentException("Rating [" + rating
                        + "] not supported.");
        }
    }
}
