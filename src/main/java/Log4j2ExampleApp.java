
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Log4j2ExampleApp {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Log4j2ExampleApp started.");
        logger.info("Something to warn");
        logger.info("Something failed.");
        try {
            Files.readAllBytes(Paths.get("/file/does/not/exist"));
        } catch (IOException ioex) {
            logger.info("Error message", ioex);
        }
    }
}