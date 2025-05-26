package dk.sdu.mmmi.cbse.common.components;

public interface Lifespan {

    /**
     * Sets lifespan to a given value
     *
     * Preconditions:
     * Instance of implementation has an int variable called lifespan that is not final
     *
     * Postconditions:
     * Lifespan variable value is set to value specified in argument of method
     */
    void setLifespan(int value);

    /**
     * Updates lifespan subtracting 1 from it if its value is above 0
     *
     * Preconditions:
     * Instance of implementation has an int variable called lifespan that is not final
     * Lifespan value is not below 0
     *
     * Postconditions:
     * Lifespan value is decreased by 1 if value before method call is above 0
     *
     */
    void update();

    /**
     * Returns true if lifespan is above 0
     *
     * Preconditions:
     * Instance of implementation has an int variable called lifespan
     *
     * Postconditions:
     * Returns true if lifespan is above 0
     */
    boolean hasLifespan();
}
