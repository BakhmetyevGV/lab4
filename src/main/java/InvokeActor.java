import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class InvokeActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(TestMessage.class, msg ->{
                    ScriptEngine engine = new
                            ScriptEngineManager().getEngineByName("nashorn");
                })
                .build();
    }
}
