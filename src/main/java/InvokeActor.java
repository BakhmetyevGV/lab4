import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.ScriptEngine;

public class InvokeActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(TestMessage.class, msg ->{
                    ScriptEngine scriptEngine = new 
                })
                .build();
    }
}
