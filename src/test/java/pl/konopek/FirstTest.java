package pl.konopek;

import org.junit.jupiter.api.Test;

public class FirstTest {
    @Test
    void testIt(){
        assert true == true;
    }

    @Test
    void testIt2(){
        String kukuryku = "Szymon";
        String kogut = "Hello " + kukuryku;

        System.out.println(kogut);

        assert kogut.equals("Hello Szymon");
    }

    @Test
    void baseSchema(){
        //////////////////////////////////////
        // Arrange // Given  // Input       //
        // Act     // When   // Interaction //
        // Asset   // Then   // Output      //
        //////////////////////////////////////

    }
}
