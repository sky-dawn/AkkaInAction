package com.skydawn.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;

/**
 * @author listening
 */
public class HelloWorld extends AbstractActor {

  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("sys");
    ActorRef ref = system.actorOf(Props.create(HelloWorld.class), "hello");
    ref.tell("akka", ActorRef.noSender());
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(String.class, this::output)
        .matchAny(this::other)
        .build();
  }

  private void output(String msg) {
    System.out.println("receive msg: " + msg);
  }

  private void other(Object msg) {
    System.out.println("other msg: " + msg.toString());
  }

}
