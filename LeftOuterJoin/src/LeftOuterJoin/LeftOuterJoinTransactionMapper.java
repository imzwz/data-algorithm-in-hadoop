package LeftOuterJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by winn on 17/5/21.
 */
public class LeftOuterJoinTransactionMapper extends Mapper<LongWritable, Text, PairOfStrings, PairOfStrings> {
    PairOfStrings outputKey = new PairOfStrings();
    PairOfStrings outputValue = new PairOfStrings();

    @Override
    public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException{
        String[] tokens = value.toString().split("\t");
        String productId = tokens[1];
        String userId = tokens[2];
        outputKey.set(userId, "2");
        outputValue.set("P", productId);
        context.write(outputKey,outputValue);

    }
}
