/**

Implement a memcache which support the following features:

get(curtTime, key). Get the key's value, return 2147483647 if key does not exist.
set(curtTime, key, value, ttl). Set the key-value pair in memcache with a time to live (ttl). The key will be valid from curtTime to curtTime + ttl - 1 and it will be expired after ttl seconds. if ttl is 0, the key lives forever until out of memory.
delete(curtTime, key). Delete the key.
incr(curtTime, key, delta). Increase the key's value by delta return the new value. Return 2147483647 if key does not exist.
decr(curtTime, key, delta). Decrease the key's value by delta return the new value. Return 2147483647 if key does not exist.
It's guaranteed that the input is given with increasingcurtTime.

Have you met this question in a real interview? Yes
Clarification
Actually, a real memcache server will evict keys if memory is not sufficient, and it also supports variety of value types like string and integer. In our case, let's make it simple, we can assume that we have enough memory and all of the values are integers.

Search "LRU" & "LFU" on google to get more information about how memcache evict data.

Try the following problem to learn LRU cache:
http://www.lintcode.com/problem/lru-cache

Example
get(1, 0)
>> 2147483647
set(2, 1, 1, 2)
get(3, 1)
>> 1
get(4, 1)
>> 2147483647
incr(5, 1, 1)
>> 2147483647
set(6, 1, 3, 0)
incr(7, 1, 1)
>> 4
decr(8, 1, 1)
>> 3
get(9, 1)
>> 3
delete(10, 1)
get(11, 1)
>> 2147483647
incr(12, 1, 1)
>> 2147483647


**/

public class Solution {

    // @param n a positive integer
    // @param k a positive integer
    // @return a Solution object
    private static Solution instance = null;
    public static int length;
    public static int numShardsPerMachine;
    public static List<Integer> MicroShards;
    public static HashMap<Integer, Integer> machTable;
    
    public static Solution create(int n, int k) {
        // Write your code here
        if (instance == null) {
            length = n;
            numShardsPerMachine = k;
            MicroShards = new ArrayList<Integer>();
            machTable = new HashMap<Integer, Integer>();
            instance = new Solution();
        }
        return instance;
    }

    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        // Write your code here
        Random rand = new Random();
        List<Integer> shards = new ArrayList<Integer>();
        
        int shard;
        
        for (int i = 0; i < numShardsPerMachine; ++i) {
            shard = rand.nextInt(length);
            while (!MicroShards.isEmpty() && MicroShards.indexOf(shard) >= 0) {
                shard = rand.nextInt(length);
            }
            
            shards.add(shard);
            MicroShards.add(shard);
            machTable.put(shard, machine_id);
        }

        Collections.sort(shards);
        Collections.sort(MicroShards);
        return shards;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {
        // Write your code here

        int begin = 0;
        int end = MicroShards.size() - 1;
        int pos;
        int median;
        int shard;
        
        while (begin <= end && begin >= 0 && end < MicroShards.size()) {
            median = begin + (end - begin) / 2;
            
            if (MicroShards.get(median) > hashcode) {
                end = median - 1;
            }
            else if (MicroShards.get(median) < hashcode) {
                begin = median + 1;
            }
            else {
                break;
            }
        } 
        
        pos = begin + (end - begin) / 2;
        if (pos < 0) {
            pos = 0;
        }
        else if (pos >= MicroShards.size() - 1) {
            pos = MicroShards.size() - 1;
        }

        if ( MicroShards.get(pos) >= hashcode) {
            shard = MicroShards.get(pos);
        }
        else {
            shard = pos == MicroShards.size() - 1 ? MicroShards.get(0) : MicroShards.get(pos + 1);
        }

        return machTable.get(shard);
    }
}

/**

		
**/