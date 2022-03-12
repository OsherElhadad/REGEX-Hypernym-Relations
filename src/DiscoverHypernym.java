
/*
 * Osher Elhadad
 *
 * This file defines a DiscoverHypernym class.
 */

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

/**
 * This DiscoverHypernym class prints the hypernyms of a hyponym.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class DiscoverHypernym {

    // The folder we get the whole texts from.
    private File folder;

    // The number of arguments we should get.
    private static final int NUMBER_OF_ARGUMENTS = 2;

    // The index of the first argument.
    private static final int FIRST_ARGUMENT = 0;

    // The index of the second argument.
    private static final int SECOND_ARGUMENT = 1;

    /**
     * DiscoverHypernym is the constructor method.
     * it initialize the folder file of text information.
     *
     * @param folderPath is the path for the folder of text information.
     */
    public DiscoverHypernym(String folderPath) {
        this.folder = new File(folderPath);
    }

    /**
     * printHypernymsOfThisHyponym is a method that prints the whole hypernyms of a hyponym.
     *
     * @param hyponym is the hyponym we search for it's hypernyms.
     */
    public void printHypernymsOfThisHyponym(String hyponym) {

        // Gets a map of the whole relations in the corpus.
        CreateHypernymDatabase database = new CreateHypernymDatabase(this.folder.getPath());
        TreeMap<String, TreeMap<String, Integer>> map = database.createMapOfRelations();
        TreeMap<String, Integer> hypernyms = new TreeMap<>();

        /*
         * A loop that find the whole hypernyms of this hyponym,
         * and insert them to a map with the number of times they appeared.
         */
        for (String key : map.keySet()) {
            if (map.get(key).containsKey(hyponym)) {
                hypernyms.put(key, map.get(key).get(hyponym));
            }
        }

        /*
         * If there isn't an hypernyms for this lemma, it prints there isn't and returns.
         */
        if (hypernyms.size() == 0) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        } else {

            /*
             * A loop that print those hypernyms order by their integer value (decent).
             */
            TreeMap<String, Integer> sortedMap = IntegerMapValueComparator.sortMapByValues(hypernyms);
            for (Map.Entry<String, Integer> hypo : sortedMap.entrySet()) {
                System.out.println(hypo.getKey() + ": (" + hypo.getValue() + ")");
            }
        }
    }
    /**
     * This is the main function.
     * prints the all hypernyms of a hyponym.
     *
     * @param args command line arguments
     *             (the first argument is the absolute path to the directory of the corpus,
     *             the second argument is the lemma).
     */
    public static void main(String[] args) {

        /*
         * Checks if the number of arguments isn't 2.
         */
        if (args.length != NUMBER_OF_ARGUMENTS) {
            throw new RuntimeException("there isn't 2 arguments.");
        }
        DiscoverHypernym hypernyms = new DiscoverHypernym(args[FIRST_ARGUMENT]);
        hypernyms.printHypernymsOfThisHyponym(args[SECOND_ARGUMENT]);
    }
}
