package edu.duke.ece651.team13.client.round;

import edu.duke.ece651.team13.client.BoardTextView;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static edu.duke.ece651.team13.client.enums.RoundMapping.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundFactoryTest {

    @Test
    public void getHandlerTest() {

        RoundFactory roundFactory = new RoundFactory("Red", new BoardTextView(), new BufferedReader(new InputStreamReader(System.in)), System.out);

        assertEquals(InitialRound.class, roundFactory.getRound(INITIAL_ROUND).getClass());
        assertEquals(NormalRound.class, roundFactory.getRound(NORMAL_ROUND).getClass());
        assertEquals(SpectateRound.class, roundFactory.getRound(SPECTATE_ROUND).getClass());
    }
}
