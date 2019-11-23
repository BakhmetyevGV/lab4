import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {

    public Map<String, ArrayList<TestResult>>  storage = new HashMap<>();


    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, msg ->{
                    if(storage.get(msg.packageId) == null){
                        storage.put(msg.packageId, new ArrayList<>());
                    }
                    storage.get(msg.packageId).add(msg.)

                });
    }
}
