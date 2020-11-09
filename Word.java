import java.util.Objects;

public class Word implements Comparable<Word> {

    private String theWord;
    private int theCount;

    public String toString() {
        return theWord+"  "+theCount;
    }

    public Word(String word, Integer count) {
        this.theWord = word;
        theCount = count;
    }

    //equals method - takes object - check if object is a word - cast to word - return true of words contain the same theWord string

    //overriding equals method
    @Override
    public boolean equals(Object o) {
        //if object is a word
        if (this == o) {
            return true;
        }
        //if object is null or if it is not a word
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        //cast object to Word
        Word word = (Word) o;
        return Objects.equals(theWord, word.theWord);
    }




    /* compareTo should return a positive number if this is greater
     * than other, 0 if they are equal and a negative number if this is less.
     *
     * this is greater (less than) if its count is greater (less than) that of
     * other.  If the counts are equal, you should determine which theWord is
     * larger as a String.  compareTo is implemented in Java for Strings, you
     * should use it
     */

    public int compareTo(Word other) {
        //if this is greater than other
        if (this.theCount > other.theCount){
            return 1;
        }
        //if this is equal to other
        else if (this.theCount == other.theCount){
            if (this.theWord.compareTo(other.theWord) > 0){
                return 1;
            }
            else if (this.theWord.compareTo(other.theWord) < 0){
                return -1;
            } else {
                return 0;
            }

        }
        //if this is less than other
        else return -1;
    }

    //returns count of word
    public int getCount(){
        return theCount;
    }

    //returns string of word
    public String getString(){
        return theWord;
    }

    //increment theCount
    public void addCount(){
        theCount++;
    }

}

