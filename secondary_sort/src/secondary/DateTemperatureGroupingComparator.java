package secondary;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by winn on 17/5/17.
 */
public class DateTemperatureGroupingComparator extends WritableComparator{
    public DateTemperatureGroupingComparator(){
        super(DateTemperaturePair.class, true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2){
        DateTemperaturePair pair = (DateTemperaturePair) wc1;
        DateTemperaturePair pair2 = (DateTemperaturePair) wc2;
        return pair.getYearMonth().compareTo(pair2.getYearMonth());
    }
}
