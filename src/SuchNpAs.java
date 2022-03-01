// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a SuchNpAs class.
 */

/**
 * This SuchNpAs class represents the regex pattern of such NP as,
 * extends the BasicRegex class.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class SuchNpAs extends BasicRegex {

    /**
     * SuchNpAs is the constructor method.
     * it initialize the regex string of this class.
     *
     * @param decorator is the relation decorator- to do many regex.
     */
    public SuchNpAs(Relation decorator) {
        super(decorator, "such <np>[^<]+<\\/np> as <np>[^<]+<\\/np>(( |, | , | ,)<np>[^<]+<\\/np>)*"
                + "(( |, | , | ,)(and |or |)<np>[^<]+<\\/np>)?");
    }
}
