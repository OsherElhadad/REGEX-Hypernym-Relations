
/*
 * Osher Elhadad
 *
 * This file defines a Especially class.
 */

/**
 * This Especially class represents the regex pattern of especially,
 * extends the BasicRegex class.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class Especially extends BasicRegex {

    /**
     * Especially is the constructor method.
     * it initialize the regex string of this class.
     *
     * @param decorator is the relation decorator- to do many regex.
     */
    public Especially(Relation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)especially <np>[^<]+<\\/np>(( |, | , | ,)<np>[^<]+<\\/np>)*"
                + "(( |, | , | ,)(and |or |)<np>[^<]+<\\/np>)?");
    }
}
