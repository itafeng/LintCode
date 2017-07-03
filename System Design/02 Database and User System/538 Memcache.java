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

public class Memcache {

    public class Value {
        public Value () {
        }

        public Value (int _time, int _value, int _ttl) {
            addTime = _time;
            value = _value;
            ttl = _ttl;
        }
        
        public int addTime;
        public int value;
        public int ttl;
        
    }
    
    private Map<Integer, Value> memCache;
    public static final int NOKEY = (1 << 31) - 1;
    
    
    public Memcache() {
        // Initialize your data structure here.
        memCache = new ConcurrentHashMap<Integer, Value>();
    }

    public int get(int curtTime, int key) {
        // Write your code here
        checkCache(curtTime);
        
        if (!memCache.containsKey(key)) {
            return NOKEY;
        }
        
        return memCache.get(key).value;
        
    }

    public void set(int curtTime, int key, int value, int ttl) {
        // Write your code here
        checkCache(curtTime);
        
        Value val = new Value(curtTime, value, ttl);
        memCache.put(key, val);
    }

    public void delete(int curtTime, int key) {
        // Write your code here
        checkCache(curtTime);
        
        if (memCache.containsKey(key)) {
            memCache.remove(key);
        }
    }
    
    public int incr(int curtTime, int key, int delta) {
        // Write your code here
        checkCache(curtTime);
        
        if (!memCache.containsKey(key)) {
            return NOKEY;
        }
        
        memCache.get(key).value += delta;
        
        return memCache.get(key).value;
        
    }

    public int decr(int curtTime, int key, int delta) {
        // Write your code here
        return incr(curtTime, key, -delta);
    }
    
    private void checkCache(int curTime) {
        for (Map.Entry<Integer, Value> keyValuePair : memCache.entrySet()) {
            int key = keyValuePair.getKey();
            Value val = keyValuePair.getValue();
            
            if (val.ttl == 0) {
                continue;
            }

            if (curTime > val.addTime + val.ttl - 1) {
                memCache.remove(key);
            }

        }
    }
}

/**

		
**/