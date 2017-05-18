package secondary;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by winn on 17/5/17.
 */
public class DateTemperaturePartitioner extends Partitioner<DateTemperaturePair, Text> {
    @Override
    public int getPartition(DateTemperaturePair pair, Text text, int numberOfPartitions){
        return Math.abs(pair.getYearMonth().hashCode() % numberOfPartitions);
    }
}
