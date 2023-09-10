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

    @Test
    @DisplayName("Check first parameter is null")
    void checkNameIsNull() {
        String name = null;
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, 1);
                }
        );
        assertEquals(IllegalArgumentException.class, actualException.getClass());
    }

    @Test
    @DisplayName("Check message if parameter is null")
    void checkMessageFromException() {
        String name = null;
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, 1);
                }
        );
        assertEquals("Name cannot be null.", actualException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", ""})
    @DisplayName("Check Exception if first parameter is empty string")
    void checkEmptyString(String name) {
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, 1);
                }
        );
        assertEquals(IllegalArgumentException.class, actualException.getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", ""})
    @DisplayName("Check Message if first parameter is empty string")
    void checkMessageEmptyString(String name) {
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, 1);
                }
        );
        assertEquals("Name cannot be blank.", actualException.getMessage());
    }

    @Test
    @DisplayName("Check Exception if second parameter is negative")
    void checkExceptionNegativeSecondParameter() {
        double speed = -1.5;
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", speed);
                }
        );
        assertEquals(IllegalArgumentException.class, actualException.getClass());
    }

    @Test
    @DisplayName("Check Message if second parameter is negative")
    void checkMessageNegativeSecondParameter() {
        double speed = -1.5;
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", speed);
                }
        );
        assertEquals("Speed cannot be negative.", actualException.getMessage());
    }

    @Test
    @DisplayName("Check Exception if third parameter is negative")
    void checkExceptionNegativeThirdParameter() {
        double distance = -1.5;
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", 1, distance);
                }
        );
        assertEquals(IllegalArgumentException.class, actualException.getClass());
    }

    @Test
    @DisplayName("Check Message if third parameter is negative")
    void checkMessageNegativeThirdParameter() {
        double distance = -1.5;
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", 1, distance);
                }
        );
        assertEquals("Distance cannot be negative.", actualException.getMessage());
    }

    @Test
    @DisplayName("Check first parameter in constructor")
    void getName() {
        String expectedName = "name";
        String actual = horseWithTwoParams.getName();
        assertEquals(expectedName, actual, "The return value is not a String");
    }

    @Test
    @DisplayName("Check second parameter in constructor")
    void getSpeed() {
        double expectedSpeed = 1.5;
        double actual = horseWithTwoParams.getSpeed();
        assertEquals(expectedSpeed, actual, "The return value is not the expected speed");
    }

    @Test
    @DisplayName("Check third parameter in ThreeParams constructor")
    void testGetDistanceWithThreeParameters() {
        double expectedDistance = 21.5;
        double actualDistance = horseWithThreeParams.getDistance();
        assertEquals(expectedDistance, actualDistance, "The return value is not the expected distance");
    }

    @Test
    @DisplayName("Check third parameter in TwoParams constructor")
    void testGetDistanceWithTwoParameters() {
        double expectedDistance = 0.0;
        double actualDistance = horseWithTwoParams.getDistance();
        assertEquals(expectedDistance, actualDistance, "The return value is not zero");
    }

    @Test
    @DisplayName("Check method getRandomDouble inside move method")
    void testGetRandomDoubleInsideMove() {
        double min = 0.2;
        double max = 0.9;

        try (MockedStatic<Horse> mathMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseWithTwoParams.move();
            mathMockedStatic.verify(() -> Horse.getRandomDouble(min, max), times(1));
        }
    }
    @ParameterizedTest
    @CsvSource({"0.5, 2","1.0, 3.0"})
    @DisplayName("Check method assigns distance correct value")
    void testValueDistance(double mockedRandomValue, double expectedDistance) {
        double min = 0.2;
        double max = 0.9;
        double distance = 1;
        double speed = 2;

        try (MockedStatic<Horse> mathMockedStatic = Mockito.mockStatic(Horse.class)) {
            mathMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(mockedRandomValue);

            Horse horse = new Horse("name", speed, distance);
            horse.move();

            double actualDistance = horse.getDistance();

            assertEquals(expectedDistance, actualDistance);
        }
    }
}