package hello;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.fail;

class AdderTest {

    public static final int NUMBER_ONE = 1;
    public static final int NUMBER_TWO = 1;
    public static final int NUMBER_THREE = 2;

    @BeforeAll
    static public void setUpClass() {
        System.out.println("Before all...");
    }

    @AfterAll
    static public void tearDownAll() {
        System.out.println("After all...");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Before each...");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("After each...");
    }

    @Test
    @DisplayName("Test that 1 + 1 = 2")
    public void shouldAddOneAndOneGivingTwo() {
        //Log something...
        System.out.println("YO!");

        Adder target = new Adder();

        int result = target.add(NUMBER_ONE, NUMBER_TWO);

        assertThat(result, is(NUMBER_ONE + NUMBER_TWO));
    }

    @Test
//    @DisplayName("Test that 1 + 2 = 3")
    public void shouldAddOneAndTwoGivingThree() {
        Adder target = new Adder();

        int result = target.add(NUMBER_ONE, NUMBER_THREE);

        assertThat(result, is(NUMBER_ONE + NUMBER_THREE));
    }

/*
    @Test
    @Disabled
    public void shouldBeDisabled() {
        fail("Oops...");
    }

    @Test
    @Disabled
    public void shouldFail() {
        fail("Oops...");
    }
*/
}