package RelativeFrequence;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;



/**
 * Created by winn on 17/5/21.
 */
public class OrderInversionPartitioner extends Partitioner<PairOfWords, IntWritable> {
    @Override
    public int getPartition(PairOfWords key, IntWritable value, int numOfPartitions){
        String leftWord = key.getLeftElement();
        return Math.abs(((int)hash(leftWord)) % numOfPartitions);
    }

    private static long hash(String str){
        long h = 1125899906842597L;
        int length = str.length();
        for(int i=0; i< length; i++){
            h = 31*h + str.charAt(i);
        }
        return h;
    }
}
