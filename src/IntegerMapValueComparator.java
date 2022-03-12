
/*
 * Osher Elhadad
 *
 * This file defines a IntegerMapValueComparator class.
 */

import java.util.Comparator;
import java.util.TreeMap;

/**
 * This IntegerMapValueComparator class has a map of string keys and integer values,
 * implements the Comparator interface.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class IntegerMapValueComparator implements Comparator<String> {

    // The map we use to compare and sort.
    private TreeMap<String, Integer> map;

    /**
     * IntegerMapValueComparator is the constructor method.
     * it initialize the map of this comparator.
     *
     * @param map is the map we use to compare and sort.
     */
    public IntegerMapValueComparator(TreeMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public int compare(String s1, String s2) {
        if (s1.equals(s2) && this.map.get(s1).equals(this.map.get(s2))) {
            return 0;
        }
        if (this.map.get(s1) >= this.map.get(s2)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * sortMapsOfMapByValues is a method that gets a map of maps,
     * and return a copy of this map while sort every map in it by it's integer values.
     *
     * @param map the map we want to copy and sort.
     * @return a copy of this map while sort every map in it by it's integer values.
     */
    public static TreeMap<String, TreeMap<String, Integer>> sortMapsOfMapByValues(
            TreeMap<String, TreeMap<String, Integer>> map) {
        TreeMap<String, TreeMap<String, Integer>> newMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (String key : map.keySet()) {
            newMap.put(key, sortMapByValues(map.get(key)));
        }
        return newMap;
    }

    /**
     * sortMapByValues is a method that gets a map of string keys and integer values,
     * and return a copy of this map while sort it by it's integer values.
     *
     * @param map the map we want to copy and sort.
     * @return a copy of this map while sort it by it's integer values.
     */
    public static TreeMap<String, Integer> sortMapByValues(TreeMap<String, Integer> map) {
        IntegerMapValueComparator comparator = new IntegerMapValueComparator(map);
        TreeMap<String, Integer> map1 = new TreeMap<>(comparator);
        map1.putAll(map);
        return map1;
    }
}
