// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a SuchAs class.
 */

/**
 * This SuchAs class represents the regex pattern of such as,
 * extends the BasicRegex class.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class SuchAs extends BasicRegex {

    /**
     * SuchAs is the constructor method.
     * it initialize the regex string of this class.
     *
     * @param decorator is the relation decorator- to do many regex.
     */
    public SuchAs(Relation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)such as <np>[^<]+<\\/np>(( |, | , | ,)<np>[^<]+<\\/np>)*"
                + "(( |, | , | ,)(and |or |)<np>[^<]+<\\/np>)?");
    }
}
