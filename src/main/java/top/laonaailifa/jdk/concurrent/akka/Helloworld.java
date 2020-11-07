package top.laonaailifa.jdk.concurrent.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Helloworld {

    static class ActorDemo extends AbstractActor {

        public ActorDemo() {
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder().match(String.class,s -> {
                System.out.println(s);
            }).build();
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");

        ActorRef actorDemo = system.actorOf(Props.create(ActorDemo.class), "actorDemo");
        actorDemo.tell("123",ActorRef.noSender());
    }
}
