
/*
 * Osher Elhadad
 *
 * This file defines a Including class.
 */

/**
 * This Including class represents the regex pattern of including,
 * extends the BasicRegex class.
 *
 * @version 1.00 22 Jun 2021
 * @author Osher Elhadad
 */
public class Including extends BasicRegex {

    /**
     * Including is the constructor method.
     * it initialize the regex string of this class.
     *
     * @param decorator is the relation decorator- to do many regex.
     */
    public Including(Relation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)including <np>[^<]+<\\/np>(( |, | , | ,)<np>[^<]+<\\/np>)*"
                + "(( |, | , | ,)(and |or |)<np>[^<]+<\\/np>)?");
    }
}
