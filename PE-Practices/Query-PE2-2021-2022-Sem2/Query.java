/**
 * CS2030S PE1 Question 2
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.Predicate;
import java.util.stream.*;

class Query {

    public static <T,S> Stream<Map.Entry<T, S>> getFilteredByKey(Map<T, S> table, Predicate<T> p) {
        return table.entrySet().stream()
          .filter(entry -> p.test(entry.getKey()));
    }

    public static Stream<Integer> getIdsFromName(Map<String, List<Integer>> table, String name) {
        return table.entrySet().stream()
          .filter(entry -> entry.getKey() == name)
          .map(entry -> entry.getValue().stream())
          .flatMap(entry -> entry);
    }

    // stream integer cannot be converted to boolean
 
    public static Stream<Double> getCostsFromIDs(Map<Integer, Double> table, Stream<Integer> s) {
        /*
        return table.entrySet().stream()
          .map(entry -> s.filter(intInteger -> intInteger == entry.getKey()))
          .flatMap(entry -> entry);
        */
       
        return s.map(intEntry -> table.entrySet().stream().filter(entry -> entry.getKey() == intEntry))
                 .flatMap(entry -> entry)
                 .map(entry -> entry.getValue());       
    }
    
    public static Stream<String> allCustomerCosts(Map<String, List<Integer>> customerTable, Map<Integer, Double> salesTable) {
        /*
    	return customerTable.entrySet().stream()
          .map(entry -> entry.getValue().stream())
          .flatMap(entry -> entry) // stream of integers
          .map(entry -> salesTable.entrySet().stream().filter(salesEntry -> salesEntry.getKey() == entry))
          .flatMap(entry -> entry)
          .map(entry -> "" + entry.getValue() + "");       
        */

        return customerTable.entrySet().stream()
                 .flatMap(entry -> getCostsFromIDs(salesTable, getIdsFromName(customerTable, entry.getKey()))
                     .map(cost -> String.format(entry.getKey() + ": " + cost)));
    }

    public static Stream<String> totaledCustomerCosts(Map<String, List<Integer>> customerTable, Map<Integer, Double> salesTable) {
        return customerTable.entrySet().stream()
                 .map(entry -> entry.getKey() + ": " + getCostsFromIDs(salesTable, getIdsFromName(customerTable, entry.getKey()))
                 .reduce(0.0, (x,y) -> x + y));

    }
}






