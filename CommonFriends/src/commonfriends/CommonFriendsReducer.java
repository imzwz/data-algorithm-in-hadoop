package commonfriends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.*;

/**
 * Created by winn on 17/5/23.
 */
public class CommonFriendsReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
        Map<String, Integer> map = new HashMap<String, Integer>();
        Iterator<Text> iterator = values.iterator();
        int numOfValues = 0;
        while(iterator.hasNext()){
            String friends = iterator.next().toString();
            if(friends.equals("")){
                context.write(key, new Text("[]"));
                return;
            }
            addFriends(map, friends);
            numOfValues++;
        }
        List<String> commonFriends = new ArrayList<String>();
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            if(entry.getValue() == numOfValues){
                commonFriends.add(entry.getKey());
            }
        }
        context.write(key, new Text(commonFriends.toString()));
    }

    private void addFriends(Map<String, Integer> map, String friends) {
        String[] friendList = friends.split(",");
        for(String friend: friendList){
            Integer count = map.get(friend);
            if(count == null){
                map.put(friend, 1);
            }else{
                map.put(friend, ++count);
            }
        }
    }
}
