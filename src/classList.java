
import java.io.*;
import java.util.LinkedList;

/**
 * A dictionary manages a collection of known, correctly-spelled words. 
 *
 * The dictionary is case insensitive and only stores "valid" word. 
 * A valid word is any sequence of letters (as determined by Character.isLetter) 
 * or apostrophes characters.
 */
public class classList {
	private LinkedList<String> diction;
	private String semester = null;

  /**
   * Constructs a dictionary from words provided by a TokenScanner.
   * <p>
   * A word is any sequence of letters (see Character.isLetter) or apostrophe 
   * characters. All non-word tokens provided by the TokenScanner should be ignored.
   *
   * <p>
   *
   * @param ts sequence of words to use as a dictionary
   * @throws IOException if error while reading
   * @throws IllegalArgumentException if the provided reader is null
   */
    public classList(TokenScanner ts) throws IOException {
    	diction = new LinkedList<String>();
    	if (ts != null) {
            while(ts.hasNext()) {
            	String word = ts.next();
            	if (TokenScanner.isWord(word) && !(word.toLowerCase().equals("summer")) && !(word.toLowerCase().equals("spring")) && !(word.toLowerCase().equals("fall"))) {
            	String wordfinal = word.toUpperCase();
            	diction.add(wordfinal);
            	} else if (TokenScanner.isWord(word)) {
            		semester = word;
            	}
            }
    	} else {
    		throw new IllegalArgumentException();
    	}
  }

   /**
   * Constructs a dictionary from words from a file.
   *
   *
   * @param filename location of file to read words from
   * @throws FileNotFoundException if the file does not exist
   * @throws IOException if error while reading
   */
   public static classList make(String filename) throws IOException {
	  Reader r = new FileReader(filename);
	  classList d = new classList(new TokenScanner(r));
	  r.close();
  	  return d;
   }

  /**
   * Returns the number of unique words in this dictionary. This count is
   * case insensitive: if both "DOGS" and "dogs" appeared in the file, it
   * should only be counted once in the returned sum.
   * 
   * @return number of unique words in the dictionary
   */
  public int getNumWords() {
     return diction.size();
  }
  
  public String getElement(int index) {
	     return diction.get(index);
	  }

  public String getSemester() {
	     return semester;
	  }
  
  /**
   * Test whether the input word is present in the Dictionary. If the word 
   * is not in the Dictionary the method should return false. Note that only 
   * strings containing nonword characters (such as spaces) will not be in the 
   * dictionary. If the argument is null, the method should also return false.
   * <p>
   * This check should be case insensitive.  For example, if the
   * Dictionary contains "dog" or "dOg" then isWord("DOG") should return true.
   * <p>
   * Calling this method should not re-open or re-read the source file.
   *
   * @param word a string token to check. Assume any leading or trailing
   *    whitespace has already been removed.
   * @return whether the word is in the dictionary
   */
  public boolean isWord(String word) {
    if (word == null || !TokenScanner.isWord(word)) {
    	return false;
    } else if (diction.contains(word.toUpperCase())) {
    	return true;
    }
    return false;
  }
}
