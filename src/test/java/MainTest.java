import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Timeout(22)
    @DisplayName("Check time method works")
    void mainTimeTest() throws Exception {
            Main.main(new String[]{});
    }
}