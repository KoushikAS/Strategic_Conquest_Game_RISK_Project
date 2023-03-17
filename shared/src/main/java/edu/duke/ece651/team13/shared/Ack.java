package edu.duke.ece651.team13.shared;

import edu.duke.ece651.team13.shared.enums.AckStatusEnum;

public class Ack {

    String message;
    AckStatusEnum status;

    Ack(AckStatusEnum status, String message ){
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public AckStatusEnum getStatus(){
        return status;
    }
}
