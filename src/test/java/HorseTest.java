import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HorseTest {
    private Horse horseWithThreeParams;
    private Horse horseWithTwoParams;

    @BeforeEach
    void setUp() {
        String expectedName = "name";
        double expectedSpeed = 1.5;
        double expectedDistance = 21.5;

        horseWithThreeParams = new Horse(expectedName, expectedSpeed, expectedDistance);
        horseWithTwoParams = new Horse(expectedName, expectedSpeed);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Check the first parameter in the constructor")
    void getName() {
        String expectedName = "name";
        String actual = horseWithTwoParams.getName();
        assertEquals(expectedName, actual, "The return value is not a String");
    }

    @Test
    @DisplayName("Check the second parameter in the constructor")
    void getSpeed() {
        double expectedSpeed = 1.5;
        double actual = horseWithTwoParams.getSpeed();
        assertEquals(expectedSpeed, actual, "The return value is not the expected speed");
    }

    @Test
    @DisplayName("Check the third parameter in ThreeParams constructor")
    void testGetDistanceWithThreeParameters() {
        double expectedDistance = 21.5;
        double actualDistance = horseWithThreeParams.getDistance();
        assertEquals(expectedDistance, actualDistance, "The return value is not the expected distance");
    }

    @Test
    @DisplayName("Check the third parameter in TwoParams constructor")
    void testGetDistanceWithTwoParameters() {
        double expectedDistance = 0.0;
        double actualDistance = horseWithTwoParams.getDistance();
        assertEquals(expectedDistance, actualDistance, "The return value is not zero");
    }

    @Test
    @DisplayName("Check method getRandomDouble inside move method with parameters 0.2 and 0.9")
    void testGetRandomDoubleInsideMove() {
        double min = 0.2;
        double max = 0.9;

        try (MockedStatic<Horse> mathMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseWithTwoParams.move();
            mathMockedStatic.verify(() -> Horse.getRandomDouble(min, max), times(1));
        }
    }
    @ParameterizedTest
    @CsvSource({"1.0, 3.0, 2.5"})
    @DisplayName("Check that the method assigns distance a correct value")
    void testValueDistance(double distance, double speed, double expectedDistance) {
        double min = 0.2;
        double max = 0.9;

        try (MockedStatic<Horse> mathMockedStatic = Mockito.mockStatic(Horse.class)) {
            double mockedRandomValue = 0.5;
            mathMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(mockedRandomValue);

            Horse horse = new Horse("name", speed, distance);
            horse.move();

            double actualDistance = horse.getDistance();

            assertEquals(expectedDistance, actualDistance);
        }
    }
}