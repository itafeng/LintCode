/**

Implement a simple twitter. Support the following method:

postTweet(user_id, tweet_text). Post a tweet.
getTimeline(user_id). Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
getNewsFeed(user_id). Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
follow(from_user_id, to_user_id). from_user_id followed to_user_id.
unfollow(from_user_id, to_user_id). from_user_id unfollowed to to_user_id.
Have you met this question in a real interview? Yes
Example
postTweet(1, "LintCode is Good!!!")
>> 1
getNewsFeed(1)
>> [1]
getTimeline(1)
>> [1]
follow(2, 1)
getNewsFeed(2)
>> [1]
unfollow(2, 1)
getNewsFeed(2)
>> []


**/

/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */
public class MiniTwitter {

    public HashMap<Integer, List<Integer>> friendship;
    public List<Tweet> tweets;

    public MiniTwitter() {
        // initialize your data structure here.
        tweets = new ArrayList<Tweet>();
        friendship = new HashMap<Integer, List<Integer>>();
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        //  Write your code here
        Tweet tweet = Tweet.create(user_id, tweet_text);
        tweets.add(tweet);
        return tweet;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // Write your code here
        List<Tweet> result = new ArrayList<Tweet>();
        int count = 0;
        for (int i = tweets.size() - 1; i >= 0 && count < 10; --i) {
            if (tweets.get(i).user_id == user_id){
                result.add(tweets.get(i));
                ++count;
            }
            else {
                if (friendship.containsKey(user_id)) {
                    for (int friend : friendship.get(user_id)) {
                        if (friend == tweets.get(i).user_id) {
                            result.add(tweets.get(i));
                            ++count;
                        }
                    }
                }
            }
        }
        return result;
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
        // Write your code here
        List<Tweet> result = new ArrayList<Tweet>();
        int count = 0;
        for (int i = tweets.size() - 1; i >= 0 && count < 10; --i) {
            if (tweets.get(i).user_id == user_id) {
                result.add(tweets.get(i));
                ++count;
            }
        }
        return result;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
        if (!friendship.containsKey(from_user_id)) {
            friendship.put(from_user_id, new ArrayList<Integer>());
        }
        friendship.get(from_user_id).add(to_user_id);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
        
        if (friendship.containsKey(from_user_id)) {
            List<Integer> friends = friendship.get(from_user_id);
            int index = friends.indexOf(to_user_id);
            if (index >= 0) {
                friends.remove(index);
            }
        }
    }
}

/**

		
**/