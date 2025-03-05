package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * A {@code Remark} object is immutable and guarantees validity upon creation.
 */
public class Remark {
    /** The remark content associated with a person. */
    public final String value;

    /**
     * Constructs a {@code Remark} object.
     *
     * @param remark The remark content.
     * @throws NullPointerException if {@code remark} is null.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns the string representation of this remark.
     *
     * @return The remark content as a string.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if this remark is equal to another object.
     *
     * @param other The other object to compare.
     * @return {@code true} if both objects are of the same type and contain the same remark value,
     *     otherwise {@code false}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    /**
     * Returns the hash code of this remark.
     *
     * @return The hash code based on the remark value.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
