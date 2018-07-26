package app;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class Markers {
    public static Marker loginMarker = MarkerFactory.getMarker("loginMarker");
    public static Marker rentalMarker = MarkerFactory.getMarker("rentalMarker");
    public static Marker returnMarker = MarkerFactory.getMarker("returnMarker");
    public static Marker authorityServerErrorMarker = MarkerFactory.getMarker("authorityServerErrorMarker");
    public static Marker exceptionMerker = MarkerFactory.getMarker("exceptionMarker");
}
