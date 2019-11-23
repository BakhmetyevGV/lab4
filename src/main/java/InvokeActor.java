import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class InvokeActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .build();
    }
}
