import java.io.*;
import java.util.*;

public class LexiconTester {
    public static void main(String[] args) {
        try {
            // Create a file object which links to the path of text file
            File file = new File("F:\\Study\\Coding practice\\CodingMentor\\PracticeTest1\\sample1-pp.txt");
            // Read in the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            // Create some variables to hold the word
            String s;
            String word = null;
            String[] arrayWords;
            // Data structure of lexicon
            ArrayList aListWords = new ArrayList();
            // Variable to count number of words in total (including duplicate)
            int j = 0;
            // Read every line of text
            while ((s = br.readLine()) != null)
            {
                // Create a new scanner with the specified String Object
                Scanner scan = new Scanner(s);
                // Check if the scanner has a token
                while (scan.hasNext())
                {
                    // for each word, remove any non-letter characters, turn to lowercase
                    word = scan.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    aListWords.add(word); // add word to lexicon
                    j++; // increase word count
                }
            }
            br.close();
            aListWords = removeDuplicates(aListWords); // remove any word duplicate
            Collections.sort(aListWords); // sort in alphabetical order
            int size = aListWords.size();
            // Print out word in lexicon
            for (int i = 0; i < size; i++)
            {
                System.out.println(aListWords.get(i));
            }
            System.out.println("Total words: " + j);
        } catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Remove the duplicates
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> aList) {
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        // Traverse through the first list
        for (T element : aList)
        {
            // if this element not in newList, then add it
            if (!newList.contains(element))
            {
                newList.add(element);
            }
        }
        return newList;
    }
}
