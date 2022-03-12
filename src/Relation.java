
/*
 * Osher Elhadad
 *
 * This file defines a Relation interface.
 */

import java.util.TreeMap;

/**
 * This Relation class has method on relations.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public interface Relation {

    /**
     * getRelations is a method from the Relation interface,
     * it return the map of the relations.
     *
     * @return the map of the relations.
     */
    TreeMap<String, TreeMap<String, Integer>> getRelations();

    /**
     * addNewRelation is a method from the Relation interface,
     * it adds a new relation to the map.
     *
     * @param hypernym the hypernym of the relation.
     * @param hyponym the hyponym of the relation.
     */
    void addNewRelation(String hypernym, String hyponym);

    /**
     * doesContainsHypernym is a method from the Relation interface,
     * it return true if this hypernym is in the relations map, and false else.
     *
     * @param hypernym the hypernym of the relation.
     * @return true if this hypernym is in the relations map, and false else.
     */
    Boolean doesContainsHypernym(String hypernym);

    /**
     * doesContainsRelation is a method from the Relation interface,
     * it return true if this relation is in the relations map, and false else.
     *
     * @param hypernym the hypernym of the relation.
     * @param hyponym the hyponym of the relation.
     * @return true if this relation is in the relations map, and false else.
     */
    Boolean doesContainsRelation(String hypernym, String hyponym);

    /**
     * addContainsHypernym is a method from the Relation interface,
     * it adds the hyponym that connected to the hypernym in the map.
     *
     * @param hypernym the hypernym of the relation.
     * @param hyponym the hyponym of the relation.
     */
    void addContainsHypernym(String hypernym, String hyponym);

    /**
     * addContainsRelation is a method from the Relation interface,
     * it increase the the number of this relation in the map.
     *
     * @param hypernym the hypernym of the relation.
     * @param hyponym the hyponym of the relation.
     */
    void addContainsRelation(String hypernym, String hyponym);

    /**
     * addAllRelations is a method from the Relation interface,
     * it adds the all relations in this text.
     *
     * @param text the text we search in.
     */
    void addAllRelations(String text);
}
