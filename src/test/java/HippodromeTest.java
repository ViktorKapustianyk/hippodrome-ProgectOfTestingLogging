
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HippodromeTest {
    @Test
    @DisplayName("Check if parameter is null")
    void constructor_NullListParamPassed_ThrowIllegalArgumentException() {
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals(IllegalArgumentException.class, actualException.getClass());
    }

    @Test
    @DisplayName("Check Message parameter is null")
    void constructor_NullListParamPassed_Message() {
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", actualException.getMessage());
    }

    @Test
    @DisplayName("Check if parameter is empty list")
    void constructor_EmptyListParamPassed_ThrowIllegalArgumentException() {
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(Collections.emptyList());
                }
        );
        assertEquals(IllegalArgumentException.class, actualException.getClass());
    }

    @Test
    @DisplayName("Check Message if parameter is empty list")
    void constructor_EmptyListParamPassed_Message() {
        Throwable actualException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(Collections.emptyList());
                }
        );
        assertEquals("Horses cannot be empty.", actualException.getMessage());
    }

    @Test
    @DisplayName("Verify list contains same objects & same order.")
    void getHorses_ReturnsListHorsesInOrder() {
        List<Horse> exceptedHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            exceptedHorses.add(i, new Horse("Name" + i , i));
        }
        Hippodrome hippodrome = new Hippodrome(exceptedHorses);
        List<Horse> actualHorses = hippodrome.getHorses();

        assertNotNull(hippodrome.getHorses());

        assertEquals(30, hippodrome.getHorses().size());

        assertEquals(exceptedHorses.get(1).getName(), actualHorses.get(1).getName());
        assertIterableEquals(exceptedHorses, actualHorses);
    }

    @Test
    @DisplayName("Check method move calls on all horses.")
    void move_CallSMoveMethodForAllHorses() {
        List<Horse> mockedHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horseMock = Mockito.mock(Horse.class);
            mockedHorses.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();

        for (Horse horseMock : mockedHorses) {
            Mockito.verify(horseMock, times(1)).move();
        }
    }

    @Test
    @DisplayName("Verify method returns largest distance value.")
    void getWinner_ReturnsCorrectWinner() {
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("First", 3);
        Horse horse2 = new Horse("Second", 2);
        Horse horse3 = new Horse("Third", 1);

        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Horse winner = hippodrome.getWinner();
        double actualDistance = winner.getDistance();

        for (Horse horse : horses) {
            if (horse != winner) {
                double horseDistance = horse.getDistance();
                assertTrue(actualDistance > horseDistance);
            }
        }

    }
}