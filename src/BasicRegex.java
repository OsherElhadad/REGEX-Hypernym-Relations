// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a BasicRegex class.
 */

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This BasicRegex class has a relation decorator and the regex string,
 * implements the Relation interface.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class BasicRegex implements Relation {

    // The relation decorator- to do many regex.
    private Relation decorator;

    // The regex string- may change in the future.
    private String reg;

    // The size of the prefix <np>.
    private static final int PREFIX_SIZE_NP = 4;

    // The size of the suffix </np>.
    private static final int SUFFIX_SIZE_NP = 5;

    /**
     * BasicRegex is the constructor method.
     * it initialize the map with a comparator we get.
     *
     * @param decorator is the relation decorator- to do many regex.
     * @param reg is the regex string- may change in the future.
     */
    public BasicRegex(Relation decorator, String reg) {
        this.decorator = decorator;
        this.reg = reg;
    }

    /**
     * getReg is a method that returns the string of the regex of this regex class.
     *
     * @return the string of the regex of this regex class.
     */
    public String getReg() {
        return this.reg;
    }

    @Override
    public TreeMap<String, TreeMap<String, Integer>> getRelations() {
        return this.decorator.getRelations();
    }

    @Override
    public void addNewRelation(String hypernym, String hyponym) {
        this.decorator.addNewRelation(hypernym, hyponym);
    }

    @Override
    public Boolean doesContainsHypernym(String hypernym) {
        return this.decorator.doesContainsHypernym(hypernym);
    }

    @Override
    public Boolean doesContainsRelation(String hypernym, String hyponym) {
        return this.decorator.doesContainsRelation(hypernym, hyponym);
    }

    @Override
    public void addContainsHypernym(String hypernym, String hyponym) {
        this.decorator.addContainsHypernym(hypernym, hyponym);
    }

    @Override
    public void addContainsRelation(String hypernym, String hyponym) {
        this.decorator.addContainsRelation(hypernym, hyponym);
    }

    @Override
    public void addAllRelations(String text) {
        Pattern patt = Pattern.compile(this.reg);
        Matcher matcher = patt.matcher(text);

        /*
         * A loop that find the whole expressions that fits the pattern of the relation,
         * and separates the hypernym and it's hyponyms.
         */
        while (matcher.find()) {
            String np = "<np>[^<]+<\\/np>";
            String sub = text.substring(matcher.start(), matcher.end());
            Pattern patt2 = Pattern.compile(np);
            Matcher matcher2 = patt2.matcher(sub);
            String hypernym = "";

            /*
             * Gets the first np in the regex pattern that is the hypernym.
             */
            if (matcher2.find()) {
                hypernym = sub.substring(matcher2.start() + PREFIX_SIZE_NP, matcher2.end() - SUFFIX_SIZE_NP);
            }

            /*
             * A loop that find the whole hyponym of this hypernym,
             * and adds them to the relation.
             */
            while (matcher2.find()) {
                String hyponym = sub.substring(matcher2.start() + PREFIX_SIZE_NP, matcher2.end() - SUFFIX_SIZE_NP);

                /*
                 * Checks if this hypernym has not already inserted that it inserted as a new relation,
                 * or insert it as an exist one.
                 */
                if (this.doesContainsHypernym(hypernym)) {

                    /*
                     * if the relation exists than it increase it's counter.
                     */
                    if (this.doesContainsRelation(hypernym, hyponym)) {
                        this.addContainsRelation(hypernym, hyponym);
                    } else {
                        this.addContainsHypernym(hypernym, hyponym);
                    }
                } else {
                    this.addNewRelation(hypernym, hyponym);
                }
            }
        }

        // Adds the all regex patterns of the whole decorators.
        this.decorator.addAllRelations(text);
    }
}
