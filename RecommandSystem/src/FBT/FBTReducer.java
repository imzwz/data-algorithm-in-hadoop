package FBT;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by winn on 17/5/26.
 */
public class FBTReducer extends Reducer<PairOfStrings, IntWritable, PairOfStrings, IntWritable> {

    @Override
    public void reduce(PairOfStrings key, Iterable<IntWritable> values, Context context)throws IOException,InterruptedException{

    }
}
