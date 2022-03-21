package com.skydawn.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletionStage;

/**
 * @author listening
 */
public class PatternsDemo extends AbstractActor {
  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("sys");
    ActorRef p1 = system.actorOf(Props.create(PatternsDemo.class), "p1");
    CompletionStage<Object> stage = Patterns.ask(p1, "jieke", Duration.of(10, ChronoUnit.SECONDS));
    Object result = stage.toCompletableFuture().join();
    System.out.println("result: " + result);
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().match(String.class, this::say).build();
  }

  private void say(String msg) {
    msg = "say " + msg;
    getSender().tell(msg, getSelf());
  }
}
