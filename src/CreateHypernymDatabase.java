
/*
 * Osher Elhadad
 *
 * This file defines a CreateHypernymDatabase class.
 */

import java.io.File;
import java.util.TreeMap;

/**
 * This CreateHypernymDatabase class creates a database of hypernyms.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class CreateHypernymDatabase {

    // The folder we get the whole texts from.
    private File folder;

    // The number of arguments we should get.
    private static final int NUMBER_OF_ARGUMENTS = 2;

    // The index of the first argument.
    private static final int FIRST_ARGUMENT = 0;

    // The index of the second argument.
    private static final int SECOND_ARGUMENT = 1;

    /**
     * CreateHypernymDatabase is the constructor method.
     * it initialize the folder file of text information.
     *
     * @param folderPath is the path for the folder of text information.
     */
    public CreateHypernymDatabase(String folderPath) {
        this.folder = new File(folderPath);
    }

    /**
     * createMapOfRelations is a method that creates a map of relations.
     *
     * @return a map of relations from the corpus.
     */
    public TreeMap<String, TreeMap<String, Integer>> createMapOfRelations() {

        // A decorator of the whole patterns of relations.
        SuchAs regex = new SuchAs(new SuchNpAs(new Especially(new Including(
                new WhichIs(new BasicRelation(String.CASE_INSENSITIVE_ORDER))))));

        /*
         * A loop that find the whole relations in the whole files in our corpus,
         * and creates a map of relations.
         */
        for (File file : this.folder.listFiles()) {
            TextFile textFile = new TextFile(file.getPath());
            String text = textFile.getOneLineString();
            regex.addAllRelations(text);
        }
        return regex.getRelations();
    }

    /**
     * createDatabase is a method that creates a database file of relations.
     *
     * @param outputPath the path of the output file we write the whole relations.
     */
    public void createDatabase(String outputPath) {

        // Write the relations in the text file database sorted by decent integer values.
        TreeMap<String, TreeMap<String, Integer>> sortedMap = IntegerMapValueComparator.
                sortMapsOfMapByValues(this.createMapOfRelations());
        TextFile outputFile = new TextFile(outputPath);
        outputFile.writeRelationsToFile(sortedMap);
    }

    /**
     * This is the main function.
     * creates a database of relations (a text file database).
     *
     * @param args command line arguments
     *             (the first argument is the path to the directory of the corpus,
     *             the second argument is the path to the output file).
     */
    public static void main(String[] args) {

        /*
         * Checks if the number of arguments isn't 2.
         */
        if (args.length != NUMBER_OF_ARGUMENTS) {
            throw new RuntimeException("there isn't 2 arguments.");
        }
        CreateHypernymDatabase database = new CreateHypernymDatabase(args[FIRST_ARGUMENT]);
        database.createDatabase(args[SECOND_ARGUMENT]);
    }
}
