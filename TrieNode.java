import java.util.ArrayList;

public class TrieNode {

    //arraylist to hold all words hashed to that position in trie
    private ArrayList<Word> holder;

    //links to 26 letters to continue word
    private TrieNode[] links;

    //instantiate trienode (arraylist to hold all original/unhashed words, links to all letters)
    public TrieNode() {
        holder = new ArrayList<Word>();
        links = new TrieNode[26];
    }

    //convert a letter to a number
    private int let(char c) {
        return c - 'a';
    }

    //first character of key
    private char firstChar(String key) {
        return key.charAt(0);
    }

    //remaining word from second character to end of word
    private String rest(String key) {
        return key.substring(1,key.length());
    }

    //link in trie based on character
    private TrieNode linkWordStart(String start) {
        return links[let(firstChar(start))];
    }

    //insert string
    public void insert(String key, Word oKey) {
        //recursive
        if (key.length() == 0) {
            //if word is in holder arraylist, increment associated count
            if (holder.contains(oKey)) {
                int j = holder.indexOf(oKey);
                holder.get(j).addCount();
            }
            //if word is not in holder arraylist, add
            else {
                holder.add(oKey);
            }
        }
        //if string to add has remaining characters
        else {
            //if first character already exists, pass in substring from second character to end
            if (linkWordStart(key) != null) {
                links[let(firstChar(key))].insert(rest(key), oKey);
            }
            //if first character does not already exist, create new node, pass in substring from second to end
            else {
                links[let(firstChar(key))] = new TrieNode();
                links[let(firstChar(key))].insert(rest(key), oKey);
            }
        }
    }

    //base case - if length of string is 0 (we are at the end of the word) see if wordHere is null or not
    //if length of key is not 0, check if the next letter in string is null
    //if yes, return null. if no, call find recursively on substring made from second character to end
    public Word find(String key, Word oKey) {
        if (key.length() == 0) {
            if (holder.contains(oKey)) {
                int j = holder.indexOf(oKey);
                return holder.get(j);
            } else {
                return null;
            }
        }
        else {
            if (linkWordStart(key) == null)
                return null;
            else return linkWordStart(key).find(rest(key), oKey);
        }
    }

    //creates an ArrayList of all existing words
    public void allKeyValue(ArrayList<Word> v) {
        if (holder != null) {
            for (int i = 0; i < holder.toArray().length; i++) {
                if (holder.get(i) != null) {
                    v.add(holder.get(i));
                }
            }
        }
        for (int i = 0; i < links.length; i++) {
            if (links[i] != null) {
                links[i].allKeyValue(v);
            }
        }
    }

    //if string is a word, print the string and true
    //if string is not a word, print the string and false
    //runs through array attached to node, if space in array is not null, print string and "-"
    public void print(String string) {
        if (holder != null)
            System.out.println(string+" "+ holder);
        else System.out.println(string+" empty");
        for (int i =0; i<26; i++) {
            if (links[i]!=null) {
                links[i].print(string+"-");
            }
        }
    }

    //delete string
    public boolean delete(String key, Word oKey) {
        //if key exists in trie
        if (find(key, oKey) != null) { //already in delete method in Trie, but kept structure for returning booleans
            recursiveDelete(key, oKey);
            return true;
        }
        //if key doesn't exist in trie, otherwise  return  false
        return false;
    }

    //helper method - deletion
    public void recursiveDelete(String key, Word oKey) {
        //if key has length 0, base case set wordHere to null
        if (key.length() == 0) {
            holder.remove(oKey);
        }
        //if key does not have length 0
        else {
            links[let(firstChar(key))].recursiveDelete(rest(key), oKey);
            //if links[let(firstChar(key))] has non-null word or if node has kids, can't cut it off
            if (anyKids() == true ||  links[let(firstChar(key))].holder != null) {
                return;
            }
            //if it's safe to delete a link
            else {
                links[let(firstChar(key))] = null;
            }
        }
    }

    //helper method - checks if kids exist
    public boolean anyKids() {
        for (int i = 0; i < links.length; i++) {
            if (links[i] != null) {
                return true;
            }
        }
        return false;
    }

}
