package LeftOuterJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by winn on 17/5/21.
 */
public class LocationCountReducer extends Reducer<Text, Text, Text, LongWritable> {
    @Override
    public void reduce(Text productID, Iterable<Text> locations, Context context) throws IOException, InterruptedException{
        Set<String> set = new HashSet<String>();
        for (Text location: locations){
            set.add(location.toString());
        }
        context.write(productID, new LongWritable(set.size()));
    }
}
