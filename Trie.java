import java.util.ArrayList;

public class Trie implements CS211CountingDictionaryInterface {

    TrieNode root = new TrieNode();

    //insert string
    public void insert(String key) {
        String key1 = key.toLowerCase();
        Word k = new Word(key.toLowerCase(), 1);
        root.insert(CS211HAMTHelper.getHash(key1), k);
    }

    //delete node
    public boolean delete(String key) {
        String key1 = key.toLowerCase();
        Word z = new Word(key, 1);
        if (root.find(CS211HAMTHelper.getHash(key1), z)==null)
            return false;
        else root.delete(CS211HAMTHelper.getHash(key1), z);
        return true;
    }

    //if word does not exist, return -1
    //if word does exist, return count of word
    public int find(String key) {
        String key1 = key.toLowerCase();
        Word m = new Word(key, 1);
        if (root == null)
            return -1;
        else {
            Word w = root.find(CS211HAMTHelper.getHash(key1), m);
            if (w == null)
                return -1;
            else return w.getCount();
        }
    }

    @Override
    public ArrayList<Word> allKeyValue() {
        ArrayList<Word> v = new ArrayList<Word>();
        root.allKeyValue(v);
        return v;
    }

    public void print() {
        root.print("");
    }

    //test program
    public static void main(String[] args) {
        Trie t = new Trie();

        t.insert("MamMoth");
        t.insert("mammoth");
        t.insert("mAmMoTh");
        t.insert("amherst");
        t.insert("CoLlEge");
        t.insert("Co" + "llege");
        t.insert("mAm" + "moTh");
        t.insert("maggie");
        t.insert("moo");
        t.insert("MaggIe");
        t.insert("MaGgIe");
        t.print();
        t.delete("mammoth");
        t.print();
    }
}