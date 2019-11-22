import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class AirportTime{

    static final int DEPARTURE_AIRPORT_ID = 11;
    private static final int ARRIVAL_AIRPORT_ID = 14;
    private static final int DELAY = 18;
    private static final int CANCEL = 19;
    private static final int AIRPORT_CODE = 0;
    private static final int AIRPORT_NAME = 1;

    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightsTextFile =  sc.textFile("Flights.csv");
        JavaRDD<String[]> flights = flightsTextFile
                .map(FlightParser::parse)
                .filter(e -> e.length != 0);

        JavaRDD<String> airportsTextFile =  sc.textFile("Airports.csv");
        JavaRDD<String[]> airports = airportsTextFile
                .map(AirportParser::parse)
                .filter(e -> e.length != 0);

        JavaPairRDD<Tuple2<String, String>, FlightSerializable> flightPairs = flights
                .mapToPair( e -> {
                    FlightData flightData = new FlightData(e[DELAY], e[CANCEL]);
                    return new Tuple2<>(
                        new Tuple2<>(e[DEPARTURE_AIRPORT_ID], e[ARRIVAL_AIRPORT_ID]),
                        new FlightSerializable(flightData)
                    );
                });

        JavaPairRDD<Tuple2<String, String>, FlightSerializable> flightSerializable = flightPairs
                .reduceByKey(FlightSerializable::reduce);

        JavaPairRDD<String, String> airportPairs = airports
                .mapToPair( e -> new Tuple2<>(e[AIRPORT_CODE], e[AIRPORT_NAME]));

        Map<String, String> airportMap = airportPairs.collectAsMap();

        final Broadcast<Map<String, String>> airportsBroadcasted =
                sc.broadcast(airportMap);

        JavaRDD<String> res = flightSerializable
                .map(
                        e  ->
                                e._1._1 + " " + airportsBroadcasted.value().get(e._1._1)
                                + ","
                                + e._1._2 + " " + airportsBroadcasted.value().get(e._1._2)
                                + ","
                                + e._2.maxDelay
                                + ","
                                + (double) e._2.delaysCount /  (double) e._2.flightsCount * 100
                                + ","
                                + (double) e._2.cancelCount /  (double) e._2.flightsCount * 100
                );
        res.saveAsTextFile("output");
    }
}