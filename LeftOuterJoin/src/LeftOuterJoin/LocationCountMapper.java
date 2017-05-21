package LeftOuterJoin;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by winn on 17/5/21.
 */
public class LocationCountMapper extends Mapper<Text, Text, Text, Text> {
    @Override
    public void map(Text key, Text value, Context context)throws IOException, InterruptedException{
        context.write(key, value);
    }
}
