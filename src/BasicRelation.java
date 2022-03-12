
/*
 * Osher Elhadad
 *
 * This file defines a BasicRelation class.
 */

import java.util.Comparator;
import java.util.TreeMap;

/**
 * This BasicRelation class has a treeMap that represents the relations,
 * implements the Relation interface.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class BasicRelation implements Relation {

    // The number we adds to the counter of the relation.
    private static final int ADD_ONE_RELATION = 1;

    // Represents the relations.
    private TreeMap<String, TreeMap<String, Integer>> map;

    /**
     * BasicRelation is the constructor method.
     * it initialize the map with a comparator we get.
     *
     * @param relationsComparator the comparator of the map of the relations.
     */
    public BasicRelation(Comparator<String> relationsComparator) {
        this.map = new TreeMap<>(relationsComparator);
    }

    @Override
    public TreeMap<String, TreeMap<String, Integer>> getRelations() {
        return this.map;
    }

    @Override
    public void addNewRelation(String hypernym, String hyponym) {
        this.map.put(hypernym, new TreeMap<>());
        this.map.get(hypernym).put(hyponym, ADD_ONE_RELATION);
    }

    @Override
    public Boolean doesContainsHypernym(String hypernym) {
        return this.map.containsKey(hypernym);
    }

    @Override
    public Boolean doesContainsRelation(String hypernym, String hyponym) {
        return this.map.get(hypernym).containsKey(hyponym);
    }

    @Override
    public void addContainsHypernym(String hypernym, String hyponym) {
        this.map.get(hypernym).put(hyponym, ADD_ONE_RELATION);
    }

    @Override
    public void addContainsRelation(String hypernym, String hyponym) {
        this.map.get(hypernym).put(hyponym, this.map.get(hypernym).get(hyponym) + ADD_ONE_RELATION);
    }

    @Override
    public void addAllRelations(String text) {

    }
}
