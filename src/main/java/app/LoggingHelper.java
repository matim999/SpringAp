package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggingHelper {

    private static Logger logger =
            LoggerFactory.getLogger(LoggingHelper.class);

    public static String getJsonRepresentation(Object object) {
        ObjectWriter writer = new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter();
        String representation = "JSON representation of ["
                + object.getClass().getSimpleName()
                + "] object:\n";
        try {
            representation += writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Creating JSON representation for an object failed");
        }
        return representation;
    }
}

