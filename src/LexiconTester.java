import java.io.*;
import java.util.*;

public class LexiconTester {
    // Data structure to store words
    private static ArrayList<String> aListWords = new ArrayList<String>();
    // Main section
    public static void main(String[] args) {
        LexiconBuilder();
    }

    // Read words from text files and print out the words in alphabetical order
    public static void LexiconBuilder() {
        try {
            ReadFile();
            // Sort the words in alphabetical order
            Collections.sort(aListWords);

            // Write to another text file
            PrintWriter pw = new PrintWriter("sample3.txt");
            String prev = "";
            for (String s: aListWords)
            {
                // check the similarity between two words
                if(s.length() == prev.length() && CountWordDiff(s, prev) == 0)
                {
                    continue; // two words are totally same
                }
                int count = 0;
                ArrayList<String> relativeWords = new ArrayList<String>();
                for (String s1: aListWords)
                {
                    // Compute the frequency of words
                    if (s.equals(s1)) // two words are same
                    {
                        count++; // count duplicate words
                    }
                    else if (s.length() == s1.length() && CountWordDiff(s,s1) == 1) // neighbour words
                    {
                        boolean exist = false;
                        for (String rel : relativeWords)
                        {
                            if (CountWordDiff(rel, s1) == 0)
                            {
                                exist = true;
                                break;
                            }
                        }

                        // prevent add the same neighbour words
                        if (!exist)
                        {
                            relativeWords.add(s1);
                        }
                    }
                }
                prev = s;
                // print to text file
                pw.println(s + " " + count + " [" + String.join(", ", relativeWords) + "]");
            }
            pw.close();

        } catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Calculate the frequency of each word
    public static <T> ArrayList<T> checkFrequency(ArrayList<T> aList) {
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        ArrayList<Integer> freq = new ArrayList<Integer>();
        /*
        while (aList.size() != 0)
        {
            T word = aList.get(0);
            int frequency = Collections.frequency(aList, word);
            freq.add(frequency);
            aList.removeAll(Collections.singleton(word));
            //System.out.println(word + " " + frequency);
        }
        */
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

    // Calculate how many letters different between two words
    private static int CountWordDiff(String s1, String s2)
    {
        int word_diff = 0;
        for (int i = s1.length() - 1; i >= 0; i--)
        {
            if (s1.charAt(i) != s2.charAt(i))
            {
                word_diff++;
            }
        }
        return word_diff;
    }

    // Check if the two words are only different in one character
    private static boolean isDiffOne(String s1, String s2)
    {
        int diff = CountWordDiff(s1, s2);
        if (diff > 1)
            return false;
        else
            return true;
    }

    // Read the text file
    private static void ReadFile() throws Exception
    {
        // Create a file object which links to the path of text file
        File file_dir = new File("F:\\Study\\Coding practice\\CodingMentor\\LexiconBuilder\\TextFiles");
        File[] files = file_dir.listFiles();

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
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }
    }

}
