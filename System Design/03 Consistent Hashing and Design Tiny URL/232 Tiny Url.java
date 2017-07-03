/**

Given a long url, make it shorter. To make it simpler, let's ignore the domain name.

You should implement two methods:

longToShort(url). Convert a long url to a short url.
shortToLong(url). Convert a short url to a long url starts with http://tiny.url/.
You can design any shorten algorithm, the judge only cares about two things:

The short key's length should equal to 6 (without domain and slash). And the acceptable characters are [a-zA-Z0-9]. For example: abcD9E
No two long urls mapping to the same short url and no two short urls mapping to the same long url.
Have you met this question in a real interview? Yes
Example
Given url = http://www.lintcode.com/faq/?id=10, run the following code (or something similar):

short_url = longToShort(url) // may return http://tiny.url/abcD9E
long_url = shortToLong(short_url) // return http://www.lintcode.com/faq/?id=10
The short_url you return should be unique short url and start with http://tiny.url/ and 6 acceptable characters. For example "http://tiny.url/abcD9E" or something else.

The long_url should be http://www.lintcode.com/faq/?id=10 in this case.


**/

public class TinyUrl {
    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    
    public static int Global_id;
    public static final int Len = 6;
    public static final String Prefix = "http://tiny.url/";
    public static String charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private HashMap<String, Integer> longToShort = new HashMap<>();
    private HashMap<Integer, String> shortToLong = new HashMap<>();
    
    public String longToShort(String url) {
        // Write your code here
        
        if (!longToShort.containsKey(url)) {
            ++Global_id;
            longToShort.put(url, Global_id);
            shortToLong.put(Global_id, url);
        }        
        return Prefix + idToShortUrl(longToShort.get(url));
    }
    
    private String idToShortUrl(int id) {
        StringBuilder sb = new StringBuilder();
        
        while (id > 0) {
            sb.insert(0, charSet.charAt(id % 62));
            id /= 62;
        }
        
        while (sb.length() < 6) {
            sb.insert(0, '0');
        }
        
        return sb.toString();
        
    }
    

    private int shortUrlToId(String url) {
        int id = 0;
        
        for (char c : url.toCharArray()) {
            id = id * 62 + charSet.indexOf(c);
        }
        
        return id;
    }


    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        // Write your code here
        
        return shortToLong.get(shortUrlToId(url.substring(Prefix.length())));
        
    }
}



/**

		
**/