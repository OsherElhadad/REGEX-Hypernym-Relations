// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a WhichIs class.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This WhichIs class represents the regex pattern of which is,
 * extends the BasicRegex class.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class WhichIs extends BasicRegex {

    // The size of the prefix <np>.
    private static final int PREFIX_SIZE_NP = 4;

    // The size of the suffix </np>.
    private static final int SUFFIX_SIZE_NP = 5;

    /**
     * WhichIs is the constructor method.
     * it initialize the regex string of this class.
     *
     * @param decorator is the relation decorator- to do many regex.
     */
    public WhichIs(Relation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)which is "
                + "((an example|a kind|a class) of )?<np>[^<]+<\\/np>");
    }

    @Override
    public void addAllRelations(String text) {
        Pattern patt = Pattern.compile(super.getReg());
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
            String hypernym = "", hyponym = "";

            /*
             * Gets the first np in the regex pattern that is the hyponym.
             */
            if (matcher2.find()) {
                hyponym = sub.substring(matcher2.start() + PREFIX_SIZE_NP, matcher2.end() - SUFFIX_SIZE_NP);
            }

            /*
             * Gets the second np in the regex pattern that is the hypernym.
             */
            if (matcher2.find()) {
                hypernym = sub.substring(matcher2.start() + PREFIX_SIZE_NP, matcher2.end() - SUFFIX_SIZE_NP);
            }

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
}
