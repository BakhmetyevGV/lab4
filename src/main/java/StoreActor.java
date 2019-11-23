import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class StoreActor extends AbstractActor {

    public Map<String, ArrayList<TestResult>> 


    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, msg ->{

                });
    }
}
