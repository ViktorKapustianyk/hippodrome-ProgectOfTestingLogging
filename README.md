# Project "Hippodrome"

The "Hippodrome" project is a console application that simulates horse racing on a hippodrome.
# Testing

The "Hippodrome" project includes an extensive set of automated tests to verify the correct operation of the `Horse`, `Hippodrome` and `Main` classes.
to make sure it works correctly. The test results will be displayed in the console. To run the tests, run the following command: mvn test

## Horse class

### Constructor

- **Test for null in parameters**: Checks that when passing `null` as the first parameter to the constructor of the `Horse` class, an `IllegalArgumentException` will be thrown. The `assertThrows` method is used for this check.

- **Null Message Test**: Tests that the exception is thrown when passing `null` to the constructor contains the message "Name cannot be null.".

- **Test for empty string in parameters**: Checks that passing an empty or whitespace string as the first parameter will throw an `IllegalArgumentException` with the appropriate message.

- **Test for negative speed and distance**: Checks that if negative values for the second and third parameters of the constructor are passed, an `IllegalArgumentException` exception will be thrown with the corresponding messages.

### Methods

- **Method `getName`**: Checks that the method returns the string that was passed as the first parameter to the constructor.

- **Method `getSpeed`**: Checks that the method returns the number that was passed as the second parameter to the constructor.

- **Method `getDistance`**: Checks that the method returns the number that was passed as the third parameter to the constructor.

- **Method `move`**: Checks that the method calls the `getRandomDouble` method with parameters 0.2 and 0.9. Use `MockedStatic` and `verify` method for this check.

## Hippodrome class

### Constructor

- **Test for null in parameters**: Checks that when passing `null` to the constructor of the `Hippodrome` class, an `IllegalArgumentException` exception will be thrown.

- **Null Message Test**: Tests that the exception is thrown when passing `null` to the constructor contain the message "Horses cannot be null.".

- **Test for an empty list in parameters**: Checks that when passing an empty list to the constructor, an `IllegalArgumentException` exception will be thrown with the message "Horses cannot be empty.".

### Methods

- **Method `getHorses`**: Verifies that the method returns a list containing the same objects in the same order as the list passed to the constructor.

- **Method `move`**: Verifies that the method calls the `move` method on all horses, using horse mocks and the `verify` method.

- **Method `getWinner`**: Checks that the method returns the knight with the largest `distance` value.

## Class Main

### Method `main`

- **Execution Time Test**: Checks that the `main` method runs within 22 seconds using the `Timeout` annotation. This test should be disabled after writing using the `Disabled` annotation.

# Logging

The Hippodrome project uses the Log4j2 library for logging. Logs are written to the `hippodrome.log` file, which is located in the `logs` folder in the project root. Log files are rotated according to Log4j2 settings.
