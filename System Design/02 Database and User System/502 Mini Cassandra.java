/**

Cassandra is a NoSQL storage. The structure has two-level keys.

Level 1: raw_key. The same as hash_key or shard_key.
Level 2: column_key.
Level 3: column_value
raw_key is used to hash and can not support range query. let's simplify this to a string.
column_key is sorted and support range query. let's simplify this to integer.
column_value is a string. you can serialize any data into a string and store it in column value.

implement the following methods:

insert(raw_key, column_key, column_value)
query(raw_key, column_start, column_end) // return a list of entries
Have you met this question in a real interview? Yes
Example
insert("google", 1, "haha")
query("google", 0, 1)
>> [（1, "haha")]

**/

/**
 * Definition of Column:
 * public class Column {
 *     public int key;
 *     public String value;
 *     public Column(int key, String value) {
 *         this.key = key;
 *         this.value = value;
 *    }
 * }
 */
public class MiniCassandra {

    public HashMap<String, NavigableMap<Integer, String>> data;

    public MiniCassandra() {
        // initialize your data structure here.
        data = new HashMap<>();
    }
    
    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
        // Write your code here
/*
        if (!data.containsKey(raw_key)) {
            data.put(raw_key, new TreeMap<Integer, String>());
        }
        data.get(raw_key).put(column_key, column_value);
*/
        
        data.computeIfAbsent(raw_key, k -> new TreeMap<>()).put(column_key, column_value);

    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
        // Write your code here
        List<Column> result = new ArrayList<>();
        
        if (data.containsKey(raw_key)) {
            for (Map.Entry<Integer, String> kvPair : data.get(raw_key).entrySet()) {
                if (kvPair.getKey() >= column_start && kvPair.getKey() <= column_end) {
                    result.add(new Column(kvPair.getKey(), kvPair.getValue()));
                }
            }
        }
        
        return result;
    }
}


/**

		
**/