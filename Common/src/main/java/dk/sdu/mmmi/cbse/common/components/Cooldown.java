package dk.sdu.mmmi.cbse.common.components;

public interface Cooldown {

    /**
     * Sets cooldown to a given value
     *
     * Preconditions:
     * Instance of implementation has an int variable called cooldown that is not final
     *
     * Postconditions:
     * Cooldown variable value is set to value specified in argument of method
     */
    void setCooldown(int value);

    /**
     * Updates cooldown subtracting 1 from it if its value is above 0
     *
     * Preconditions:
     * Instance of implementation has an int variable called cooldown that is not final
     * Cooldown value is not below 0
     *
     * Postconditions:
     * Cooldown value is decreased by 1 if value before method call is above 0
     *
     */
    void update();

    /**
     * Returns true if cooldown is 0
     *
     * Preconditions:
     * Instance of implementation has an int variable called cooldown
     *
     * Postconditions:
     * Returns true if cooldown is 0
     *
     */
    boolean noCooldown();
}
