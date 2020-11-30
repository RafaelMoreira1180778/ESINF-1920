package DEI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author DEI-ESINF
 */
public class TREE_WORDS extends BST<TextWord> {

    public void createTree() throws FileNotFoundException {
        Scanner readfile = new Scanner(new File("src/PL/xxx.xxx"));
        while (readfile.hasNextLine()) {
            String[] pal = readfile.nextLine().split("(\\,)|(\\s)|(\\.)");
            for (String word : pal)
                if (word.length() > 0)
                    insert(new TextWord(word, 1));
        }
        readfile.close();
    }

    /**
     * Inserts a new word in the tree, or increments the number of its occurrences.
     *
     * @param element
     */
    @Override
    public void insert(TextWord element) {
        root = insert(element, root);
    }

    private BST.Node<TextWord> insert(TextWord element, BST.Node<TextWord> node) {
        if (node == null) {
            return new BST.Node(element, null, null);
        }

        if (node.getElement().compareTo(element) == 0) { //replacing existing elements
            node.getElement().incOcorrences();
        } else if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(insert(element, node.getLeft()));

        } else {
            node.setRight(insert(element, node.getRight()));
        }
        return node;
    }

    /**
     * Returns a map with a list of words for each occurrence found.
     *
     * @return a map with a list of words for each occurrence found.
     */
    public Map<Integer, List<String>> getWordsOccurrences() {
        Map<Integer, List<String>> occur = new HashMap();
        List<TextWord> list = (List<TextWord>) inOrder();
        for (TextWord tw : list) {
            List<String> arr = occur.get(tw.getOcorrences());
            if (arr == null) arr = new ArrayList<>();
            arr.add(tw.getWord());
            occur.put(tw.getOcorrences(), arr);
        }
        return occur;
    }

}
