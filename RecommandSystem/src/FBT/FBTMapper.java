package FBT;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by winn on 17/5/26.
 */
public class FBTMapper extends Mapper<LongWritable, Text, PairOfStrings, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException{

    }
}
