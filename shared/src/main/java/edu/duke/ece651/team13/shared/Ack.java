package edu.duke.ece651.team13.shared;

import edu.duke.ece651.team13.shared.enums.AckStatusEnum;

import java.io.Serializable;

public class Ack implements Serializable {

    String message;
    AckStatusEnum status;

    public Ack(AckStatusEnum status, String message){
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
