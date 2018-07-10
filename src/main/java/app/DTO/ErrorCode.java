package app.DTO;

public enum ErrorCode {
    COUNTRY_NOT_FOUND_USING_ID(0),
    COUNTRY_NOT_FOUND_USING_COUNTRY_NAME(1),
    COUNTRY_DELETE_COUNTRY_NOT_FOUND(2),
    COUNTRY_ADD_COUNTRY_WITH_NAME_ALREADY_EXISTS(3),
    COUNTRY_UPDATE_COUNTRY_WITH_ID_AND_NAME_ALREADY_EXISTS(4),
    CITY_NOT_FOUND_USING_ID(5),
    CITY_NOT_FOUND_USING_CITY_NAME_OR_COUNTRY_NAME(6),
    CITY_UPDATE_CITY_WITH_GIVEN_ID_NOT_FOUND(7),
    CITY_UPDATE_COUNTRY_WITH_GIVEN_ID_NOT_FOUND(8),
    CITY_UPDATE_CITY_WITH_GIVEN_ID_AND_COUNTRY_WITH_GIVEN_ID_NOT_FOUND(9),
    CITY_UPDATE_COUNTRY_WITH_GIVEN_NAME_NOT_FOUND(10),
    CITY_UPDATE_CITY_WITH_GIVEN_COUNTRY_ALREADY_EXISTS(11),
    CITY_DELETE_CITY_NOT_FOUND_USING_ID(12),
    CITY_ADD_CITY_WITH_NAME_ALREADY_EXISTS(13),
    COUNTRY_UPDATE_COUNTRY_WITH_NAME_ALREADY_EXISTS(14),
    CITY_UPDATE_CITY_ID_ALREADY_HAS_THE_SAME_COUNTRY_ID(15),
    CITY_UPDATE_CITY_WITH_GIVEN_NAME_ALREADY_EXISTS(16),
    DIFFERENT(999);

    private int code;
    ErrorCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
