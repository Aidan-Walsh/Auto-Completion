import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Autocomplete {
    // array of terms for immutability
    private Term[] organized;

    // store value of index of first string found with prefix from binary search
    private int first;

    // store value of index of last string found with prefix from binary search
    private int last;


    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {

        // make sure array is not null
        if (terms == null) throw new IllegalArgumentException("No null argument");
        first = 0;
        last = 0;
        organized = new Term[terms.length];

        // iterate through, making sure no element is  null and to copy argument
        // array into copy
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null) {
                throw new IllegalArgumentException("No null argument");
            }
            organized[i] = terms[i];
        }

        // sort the copy of the terms array using the compareTo method in
        // Term.java
        Arrays.sort(organized);
    }

    // Returns all terms that start with the given prefix, in descending order
    // of weight.
    public Term[] allMatches(String prefix) {

        // make sure that the argument is not null
        if (prefix == null) throw new IllegalArgumentException("No null argument");

        // create new array that only contains the matches to the prefix
        Term[] matches = new Term[numberOfMatches(prefix)];

        // iterate through, going from index first to last to get all objects
        // that had a matching prefix
        for (int i = 0; i < matches.length; i++) {
            matches[i] = organized[first + i];
        }

        // then sort by reverse weight order
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {

        // make sure that the argument is not null
        if (prefix == null) throw new IllegalArgumentException("No null argument");

        // create a Term with the prefix to compare all queries to
        Term tester = new Term(prefix, 0);

        // set index of first key with prefix using method from previously written
        // class
        first = BinarySearchDeluxe.firstIndexOf(organized, tester,
                                                Term.byPrefixOrder(prefix.length()));

        // set index of last key with prefix using method from previously written
        // class
        last = BinarySearchDeluxe.lastIndexOf(organized, tester,
                                              Term.byPrefixOrder(prefix.length()));

        // the number of matches will be the distance from the first to last
        // which is them subtracted from each other, plus 1, unless the indexes
        // were never found
        if (last != -1 && first != -1) return last - first + 1;
        else return 0;


    }

    // unit testing (required)
    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
