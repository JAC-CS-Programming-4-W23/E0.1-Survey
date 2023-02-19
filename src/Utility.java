/*
 * Copyright (c) 2022 Ian Clement. All rights reserved.
 */

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * A utility class providing useful methods used in the rest of the application.
 */
public class Utility {

    // hiding the constructor makes this class "un-instantiable"
    private Utility() { }

    /**
     * Determine the maximum value in a list of data, individually mapped by a supplied function.
     * @param data The list of data.
     * @param f A function to map the values in the list, whose result is used to determine the max.
     * @param <T> The type of values in the list.
     * @param <R> The type resulting from applying f to the value. Must be Comparable.
     * @return An optional max value, which will be "empty" if the list is empty.
     */
    public static <T, R extends Comparable<R>> Optional<T> maxBy(List<T> data, Function<T, R> f) {

        Iterator<T> iter = data.iterator();

        // empty list -> max is undefined.
        if(!iter.hasNext())
            return Optional.empty();

        // start: the max is the first element in the list
        T max = iter.next();
        R maxValue = f.apply(max);

        while(iter.hasNext()) {
            // get the next value
            T next = iter.next();
            R nextValue = f.apply(next);

            // check to see if 'next' is the new 'max'
            if(maxValue.compareTo(nextValue) < 0) {
                max = next;
                maxValue = nextValue;
            }
        }

        // wrap in an optional
        return Optional.of(max);
    }

}
