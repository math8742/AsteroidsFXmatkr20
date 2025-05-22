package dk.sdu.mmmi.cbse.common.components;

public interface Health {
    /**
    * Sets health to a given value
     *
     * Preconditions:
     * Instance of implementation has an int variable called health that is not final
     *
     * Postconditions:
     * Health variable value is set to value specified in argument of method
    *
     */
    void setHealth(int value);

    /**
     * Subtracts value from health variable of the instance of implementation
     *
     * Preconditions:
     * Instance of implementation has an int variable called health that is not final
     * Value of health variable is above 0
     * Value being subtracted will not bring health below 0
     *
     * Postconditions:
     * Health variable value is decreased by value
     *
     */
    void subtractHealth(int value);

    /**
     * Returns true if health is above 0
     *
     * Preconditions:
     * Instance of implementation has an int variable called health
     *
     * Postconditions:
     * Returns true if health is greater than 0, otherwise returns false
     */
    boolean positiveHealth();
}
