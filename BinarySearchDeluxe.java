import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class BinarySearchDeluxe {


    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        // @citation Adapted from:
        // https://www.cs.princeton.edu/courses/
        // archive/fall21/cos226/lectures/21ElementarySorts.pdf
        // Accessed 10/03/2021
        // test to make sure than no argument is null
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("No null arguments");
        }

        // first several lines are exact same as normal binary search
        int lo = 0, hi = a.length - 1;

        // create variable first to store index of last key found
        int first = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // compare the key with the middle value to then check the left half
            // or right half
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
                // now that we have found the key, set first to store the index
                // and since it is the first key that we are looking for, do another
                // binary search on the left half to see if there is a duplicate
            else {
                first = mid;
                hi = mid - 1;
            }
        }

        // return the most recent index we found of the key
        return first;
    }


    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    //
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        // make sure no argument is null
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("No null arguments");
        }

        // same beginning part as last method
        int lo = 0, hi = a.length - 1;
        int last = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;

                // now, instead of searching the left half after finding the right
                // key, we search the right half to see if there is another
                // duplicate afterwards
            else {
                last = mid;
                lo = mid + 1;
            }
        }
        return last;
    }


    // unit testing (required)
    public static void main(String[] args) {

        String[] test = { "A", "B", "B", "B", "C", "F" };
        StdOut.println(firstIndexOf(test, "B", String.CASE_INSENSITIVE_ORDER));


        StdOut.println(lastIndexOf(test, "B", String.CASE_INSENSITIVE_ORDER));


    }
}
