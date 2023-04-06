//package edu.duke.ece651.team13.client.round;
//
//import edu.duke.ece651.team13.client.BoardTextView;
//import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
//import org.junit.jupiter.api.Test;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintStream;
//import java.io.StringReader;
//import java.util.ArrayList;
//
//import static edu.duke.ece651.team13.client.MockDataUtil.getInitalisedV1Map24MapRO;
//import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class InitialRoundTest {
//
//    private GameRound createGameRound(String playerName, String inputData, OutputStream bytes) {
//        BufferedReader input = new BufferedReader(new StringReader(inputData));
//        PrintStream output = new PrintStream(bytes, true);
//        return new InitialRound(playerName, new BoardTextView(), input, output);
//    }
//
//
//
//    @Test
//    void test_IntialRound() throws IOException {
//        InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("IntialRound-output.txt");
//        String expectedOutputString = new String(expectedStream.readAllBytes());
//
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        String input = "M\nRottweiler\nPoodle\n10\nM\nRottweiler\nDachshund\n20\nD\n";
//        GameRound round = createGameRound("Red", input, bytes);
//
//        ArrayList<PlayerOrderInput> orders = round.executeRound(getInitalisedV1Map24MapRO());
//
//        ArrayList<PlayerOrderInput> expectedOrders = new ArrayList<>();
//        expectedOrders.add(new PlayerOrderInput(MOVE, "Rottweiler", "Poodle", 10));
//        expectedOrders.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 20));
//        for (int i = 0; i < orders.size(); i++) {
//            assertEquals(expectedOrders.get(i), orders.get(i));
//        }
//
//        assertEquals(expectedOutputString + "\n", bytes.toString().replace("\r", ""));
//    }
//
//
//    @Test
//    void test_InvalidOrderException() throws IOException {
//        InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("InvalidOrderTypeRound-output.txt");
//        String expectedOutputString = new String(expectedStream.readAllBytes());
//
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        String input = "A\nX\nM\nRottweiler\nDachshund\n20\nD\n";
//        GameRound round = createGameRound("Red", input, bytes);
//
//        ArrayList<PlayerOrderInput> orders = round.executeRound(getInitalisedV1Map24MapRO());
//
//        ArrayList<PlayerOrderInput> expectedOrders = new ArrayList<>();
//        expectedOrders.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 20));
//        for (int i = 0; i < orders.size(); i++) {
//            assertEquals(expectedOrders.get(i), orders.get(i));
//        }
//
//        assertEquals(expectedOutputString + "\n", bytes.toString().replace("\r", ""));
//    }
//
//    @Test
//    void test_InvalidUnitException() throws IOException {
//        InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("InvalidUnitNumberRound-output.txt");
//        String expectedOutputString = new String(expectedStream.readAllBytes());
//
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        String input = "M\nRottweiler\nDachshund\nx\nM\nRottweiler\nDachshund\n20\nD\n";
//        GameRound round = createGameRound("Red", input, bytes);
//
//        ArrayList<PlayerOrderInput> orders = round.executeRound(getInitalisedV1Map24MapRO());
//
//        ArrayList<PlayerOrderInput> expectedOrders = new ArrayList<>();
//        expectedOrders.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 20));
//        for (int i = 0; i < orders.size(); i++) {
//            assertEquals(expectedOrders.get(i), orders.get(i));
//        }
//
//        assertEquals(expectedOutputString + "\n", bytes.toString().replace("\r", ""));
//    }
//}