package app.service;

import java.time.LocalDateTime;

public class CurrentTime {
    public static LocalDateTime now = LocalDateTime.now().withNano(0);

    public static void updateTime() {
        now = LocalDateTime.now().withNano(0);
        System.out.println("now()");
    }
}
