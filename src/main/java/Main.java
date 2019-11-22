import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;

public class Main {
    public static void main (String[] args){
        ActorSystem actorSystem = ActorSystem.create("testJS");
        ActorRef rootActor = actorSystem.actorOf(Props.create(RootActor.class), "rootActor");

        Http http = Http.get(actorSystem);

    }
}
