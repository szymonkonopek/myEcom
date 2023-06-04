package pl.konopek.playground;

import org.junit.jupiter.api.Test;

public class BaseTestSchemaTest {

    @Test
    void testIt() {
        assert true == true;
    }

    @Test
    void testIt2() {
        String myName = "Jakub";
        String output =  String.format("Hello %s", myName);

        assert output.equals("Hello Jakub");
    }

    @Test
    void baseSchema() {
        //Arrange // Given  //  Input
        //Act     // When   //  interaction
        //Assert  // Then   //  Output
    }
}
