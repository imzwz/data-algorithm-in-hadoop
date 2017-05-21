package RelativeFrequence;

import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;

import java.io.IOException;

/**
 * Created by winn on 17/5/21.
 */
public class OrderInversionGroupingComparator extends WritableComparator {
    public OrderInversionGroupingComparator(){
        super(PairOfWords.class, true);
    }

    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2){
        try{
            int firstVIntL1 = WritableUtils.decodeVIntSize(b1[s1]);
            int firstVIntL2 = WritableUtils.decodeVIntSize(b2[s2]);
            int firstStrL1 = readVInt(b1, s1);
            int firstStrL2 = readVInt(b2, s2);
            int cmp = compareBytes(b1, s1+firstVIntL1, firstStrL1, b2,s2+firstVIntL2, firstStrL2);
            if(cmp != 0){
                return cmp;
            }
            int secondVIntL1 = WritableUtils.decodeVIntSize(b1[s1+firstVIntL1 + firstStrL1]);
            int secondVIntL2 = WritableUtils.decodeVIntSize(b2[s2 + firstVIntL2 + firstStrL2]);
            int secondStrL1 = readVInt(b1, s1+firstVIntL1 + firstStrL1);
            int secondStrL2 = readVInt(b2, s2 + firstVIntL2 + firstStrL2);
            return compareBytes(b1, s1 + firstVIntL1 + firstStrL1+ secondVIntL1, secondStrL1, b2, s2+firstVIntL2 + firstStrL2+ secondVIntL2, secondStrL2);
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }
}
