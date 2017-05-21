package LeftOuterJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * Created by winn on 17/5/21.
 */
public class LeftOuterJoinUserMapper extends Mapper<LongWritable, Text, PairOfStrings, PairOfStrings> {
    PairOfStrings outputKey = new PairOfStrings();
    PairOfStrings outputValue = new PairOfStrings();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
        String[] tokens = value.toString().split("\t");
        if(tokens.length==2){
            outputKey.set(tokens[0], "1");
            outputValue.set("L", tokens[1]);
            context.write(outputKey, outputValue);
        }
    }
}
