/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.teamX.client;

import edu.duke.ece651.teamX.shared.MyName;


public class App {
  public String getMessage() {
    return "Hello from the client for "+ MyName.getName();
  }
  public static void main(String[] args) {
    App a = new App();
    System.out.println(a.getMessage());
  }
}
