
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


class HomeWorkApp6Test {
    private HomeWorkApp6 homeWorkApp6;

    @BeforeEach
    public void init() {
        homeWorkApp6 = new HomeWorkApp6();
    }

    @ParameterizedTest
    @MethodSource("dataForArrayCheckForFour")
    void testCheckArrayForFour(int[] array, int[] arrayResult) {
        Assertions.assertThrows(RuntimeException.class, () -> Assertions.assertArrayEquals(arrayResult, homeWorkApp6.checkArrayForFour(array) ));
    }

    @ParameterizedTest
    @MethodSource("dataForArrayCheckForFour2")
    void testCheckArrayForFour2(int[] array, int[] arrayResult) {
         Assertions.assertArrayEquals(arrayResult, homeWorkApp6.checkArrayForFour(array) );
    }

    @ParameterizedTest
    @MethodSource("dataForArrayCheckForOneOrFour")
    void testCheckArrayForOneOrFour(int[] array, boolean result) {
        Assertions.assertEquals(result, homeWorkApp6.checkArrayForOneOrFour(array));
    }

    public static Stream<Arguments> dataForArrayCheckForFour() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] { 1, 2, 3, 5 }, new int[] {}));
        return out.stream();
    }

    public static Stream<Arguments> dataForArrayCheckForFour2() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] { 4, 3, 2, 1 }, new int[] {3, 2, 1}));
        out.add(Arguments.arguments(new int[] { 2, 4, 3, 2 }, new int[] {3, 2}));
        out.add(Arguments.arguments(new int[] { 1, 2, 3, 4 }, new int[] {}));
        return out.stream();
    }

    public static Stream<Arguments> dataForArrayCheckForOneOrFour() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] { 1, 4, 1 }, true));
        out.add(Arguments.arguments(new int[] { 1, 2, 4 }, true));
        out.add(Arguments.arguments(new int[] { 1, 2, 3 }, false));
        out.add(Arguments.arguments(new int[] { 4, 0, 5 }, false));
        return out.stream();
    }

}