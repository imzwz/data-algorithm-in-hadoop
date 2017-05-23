package pojo;

/**
 * Created by winn on 17/5/23.
 */

import java.util.Set;
import java.util.TreeSet;

public class CommonFriends {
    public static Set<Integer> intersection(Set<Integer> user1friends, Set<Integer> user2friends){
        if((user1friends == null) || (user1friends.isEmpty())){
            return null;
        }
        if((user2friends == null) || (user2friends.isEmpty())){
            return null;
        }
        if(user1friends.size() < user2friends.size()){
            return intersect(user1friends, user2friends);
        }else{
            return intersect(user2friends, user1friends);
        }
    }

    private static Set<Integer> intersect(Set<Integer> smallSet, Set<Integer> largeSet) {
        Set<Integer> result = new TreeSet<Integer>();
        for(Integer x : smallSet){
            if(largeSet.contains(x)){
                result.add(x);
            }
        }
        return result;
    }
}
