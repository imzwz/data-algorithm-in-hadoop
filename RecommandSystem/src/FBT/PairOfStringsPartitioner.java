package FBT;

import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by winn on 17/5/21.
 */
public class PairOfStringsPartitioner extends Partitioner<PairOfStrings, Object>  {
    @Override
    public int getPartition(PairOfStrings key, Object value, int numOfPartitions){
        return (key.getLeft().hashCode() & Integer.MAX_VALUE) % numOfPartitions;
    }

}
