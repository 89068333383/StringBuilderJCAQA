import java.util.HashMap;
import java.util.Map;

public class ArrayToMap {
    public static Map<Integer, Integer> arrayToMap(Integer[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.merge(array[i], 1, (oldValue, newValue) -> oldValue + newValue);
        }
        return map;
    }
}
