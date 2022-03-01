// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a TextFile class.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/**
 * This TextFile class has a file path and can get it's string in one line,
 * or write a map into it.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class TextFile {

    // The path of the text file.
    private String filePath;

    // The minimum hyponyms for a relation to be written in the text file.
    private static final int MIN_HYPONYMS_TO_WRITE = 3;

    /**
     * TextFile is the constructor method.
     * it initialize the path of the text file.
     *
     * @param filePath is the path of the text file.
     */
    public TextFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * getOneLineString is a method that returns the text of the text file in one line(without \n).
     *
     * @return the text of the text file in one line(without \n), or null if there is a problem.
     */
    public String getOneLineString() {
        try {

            // gets the all text in the file to text string.
            String text = new String(Files.readAllBytes(Paths.get(this.filePath)));
            text = text.replace("\n", "");
            return text;
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
            return null;
        }
    }

    /**
     * writeRelationsToFile is a method that gets a map with relations,
     * and write them in the text file- hypernym: hyponym (x1), hyponym (x2)... .
     *
     * @param map the map we write into the text file.
     */
    public void writeRelationsToFile(TreeMap<String, TreeMap<String, Integer>> map) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(this.filePath);

            /*
             * A loop that goes on the whole hypernym in the map,
             * and write every one with it's hyponyms in a line.
             */
            for (String str : map.keySet()) {

                /*
                 * Write only relations that has 3 or more hyponyms.
                 */
                if (map.get(str).size() >= MIN_HYPONYMS_TO_WRITE) {
                    myWriter.write(str + ": ");
                    int count = 0;

                    /*
                     * A loop that goes on the whole hyponyms of this hypernym in a line,
                     * and write the times that relation appeared.
                     */
                    for (Map.Entry<String, Integer> hyponym : map.get(str).entrySet()) {
                        if (count > 0) {
                            myWriter.write(", ");
                        }
                        myWriter.write(hyponym.getKey() + " (" + hyponym.getValue() + ")");
                        count++;
                    }
                    myWriter.write("\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(" Something went wrong while writing !");
        } finally {
            if (myWriter != null) {
                try {
                    myWriter.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
    }
}
