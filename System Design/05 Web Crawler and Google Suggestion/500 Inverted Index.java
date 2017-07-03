/**

Create an inverted index with given documents.

 Notice

Ensure that data does not include punctuation.

Have you met this question in a real interview? Yes
Example
Given a list of documents with id and content. (class Document)

[
  {
    "id": 1,
    "content": "This is the content of document 1 it is very short"
  },
  {
    "id": 2,
    "content": "This is the content of document 2 it is very long bilabial bilabial heheh hahaha ..."
  },
]
Return an inverted index (HashMap with key is the word and value is a list of document ids).

{
   "This": [1, 2],
   "is": [1, 2],
   ...
}

**/

/**
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
public class Solution {
    /**
     * @param docs a list of documents
     * @return an inverted index
     */
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        // Write your code here
        Map<String, List<Integer>> dictionary = new HashMap<>();
        
        for (Document doc : docs) {
            
            String[] words = doc.content.split(" ");
            for (String word : words) {
                if (word.isEmpty()) {
                    continue;
                }
                
                if (!dictionary.containsKey(word)) {
                    dictionary.put(word, new ArrayList<Integer>());
                }
                
                List<Integer> indices = dictionary.get(word);
                
                if (indices.indexOf(doc.id) < 0) {
                    dictionary.get(word).add(doc.id);
                }
            }
            
        }
        
        return dictionary;
        
    }
}


/**

		
**/