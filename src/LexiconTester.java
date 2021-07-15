import java.io.*;
import java.util.*;

public class LexiconTester {
    // Main section
    public static void main(String[] args) {
        ReadTextFiles();
    }

    // Read words from text files and print out the words in alphabetical order
    public static void ReadTextFiles() {
        try {
            // Create a file object which links to the path of text file
            File file_dir = new File("F:\\Study\\Coding practice\\CodingMentor\\LexiconBuilder\\TextFiles");
            File[] files = file_dir.listFiles();

            // Data structure to store words
            ArrayList aListWords = new ArrayList();
            // Create some variables to hold the word
            String s;
            String word = null;
            String[] arrayWords;

            for (File f : files)
            {
                if(f.isFile())
                {
                    BufferedReader inputStream = null;
                    try
                    {
                        inputStream = new BufferedReader(new FileReader(f));
                        while ((s = inputStream.readLine()) != null)
                        {
                            // Create a new scanner with the specified String Object
                            Scanner scan = new Scanner(s);
                            // Check if the scanner has a token
                            while (scan.hasNext())
                            {
                                // for each word, remove any non-letter characters, turn to lowercase
                                word = scan.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                                aListWords.add(word); // add word to lexicon
                            }
                        }
                        aListWords = removeDuplicates(aListWords); // remove any word duplicate
                        Collections.sort(aListWords); // sort in alphabetical order
                        int size = aListWords.size();
                        // Print out word in lexicon
                        for (int i = 0; i < size; i++)
                        {
                            System.out.println(aListWords.get(i));
                        }
                        System.out.println("Total words: " + size);
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
            }
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
