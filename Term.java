import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {

    // Query for each term
    private String word;

    // Weight for each term
    private long importance;

    // length of the query
    private int length;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {

        // make sure that arguments are valid
        if (query == null || weight < 0) {
            throw new IllegalArgumentException("Illegal Argument/s!");
        }

        // initialize instance variables
        word = query;
        importance = weight;
        length = word.length();
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new byReverseWeightOrder();
    }


    private static class byReverseWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b) {

            // comparing the weights, but in reverse so that we get the highest
            // weight first rather than the lowest
            return Long.compare(b.importance, a.importance);
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {

        // make sure r is non-negative
        if (r < 0) throw new IllegalArgumentException("No negative input");
        return new byPrefixOrder(r);
    }

    private static class byPrefixOrder implements Comparator<Term> {

        // instance variable to store r (first r characters that we will be
        // comparing
        private int substring;

        // method to initialize instance variable where we receive r
        public byPrefixOrder(int t) {
            substring = t;
        }

        // compare method that will return 1 if the first r characters of b
        // precedes a, -1 if a precedes b and 0 if otherwise
        public int compare(Term a, Term b) {

            // store r into a variable so that it can get changed
            int previousR = substring;

            // test to see if r is bigger than any of the words to then
            // set variable to min length to iterate through
            if (substring > Math.min(a.word.length(), b.word.length())) {
                previousR = Math.min(a.word.length(), b.word.length());
            }

            // iterate through until iterated through previousR
            for (int i = 0; i < previousR; i++) {

                // return 1 or -1 depending on values of characters
                if (a.word.charAt(i) > b.word.charAt(i)) return 1;
                else if (a.word.charAt(i) < b.word.charAt(i)) return -1;
            }

            // now, we compare the sizes since all characters are equal
            // if previousR never changed, this means that r is less than any
            // of the word sizes, so we know up until r, words are equal in size
            // otherwise, just return the difference in word size
            if (previousR == substring) return 0;
            else return a.word.length() - b.word.length();


        }
    }


    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        int smallLength;

        // store the shortest word's length in a variable
        if (that.word.length() <= length) smallLength = that.word.length();
        else smallLength = length;

        // iterate through words, comparing the characters so that 1 is returned
        // if the argument precedes the object and -1 if vice versa
        for (int i = 0; i < smallLength; i++) {
            if (word.charAt(i) < that.word.charAt(i)) return -1;
            else if (word.charAt(i) > that.word.charAt(i)) return 1;
        }

        // then compare lengths, returning 0 if equal, -1 if the shortest one is
        // the object and 1 otherwise
        if (length == that.word.length()) return 0;
        if (smallLength == length) return -1;
        else return 1;

    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return importance + "\t" + word;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term[] test = new Term[5];
        test[0] = new Term("the", 10);
        test[1] = new Term("there", 5);
        test[2] = new Term("their", 15);
        test[3] = new Term("they", 20);
        test[4] = new Term("thereable", 2);

        StdOut.println("Each of 5 terms printed: ");
        for (int i = 0; i < test.length; i++) {
            StdOut.println(test[i].toString());
        }


        StdOut.println();
        StdOut.println("Sorted by Lexicographic Order:");
        Arrays.sort(test);
        for (Term testing : test) {
            StdOut.println(testing);
        }

        StdOut.println();
        StdOut.println("Sorted by Lexicographic Order with r:");
        Arrays.sort(test, Term.byPrefixOrder(Integer.parseInt(args[0])));
        for (Term testing : test) {
            StdOut.println(testing);
        }

        StdOut.println();
        StdOut.println("Sorted by Reverse Weight Order:");
        Arrays.sort(test, Term.byReverseWeightOrder());
        for (Term testing : test) {
            StdOut.println(testing);
        }


    }
}


