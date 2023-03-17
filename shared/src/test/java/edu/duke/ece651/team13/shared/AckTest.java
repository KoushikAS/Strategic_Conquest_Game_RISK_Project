package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.Test;

import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.FAIL;
import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AckTest {

    @Test
    public void test_Status() {
        Ack ack = new Ack(SUCCESS, "Success");
        assertEquals(ack.getStatus(), SUCCESS);
        ack = new Ack(FAIL, "Fail");
        assertEquals(ack.getStatus(), FAIL);
    }

    @Test
    public void test_Message() {
        Ack ack = new Ack(SUCCESS, "Success");
        assertEquals(ack.getMessage(), "Success");
    }
}
