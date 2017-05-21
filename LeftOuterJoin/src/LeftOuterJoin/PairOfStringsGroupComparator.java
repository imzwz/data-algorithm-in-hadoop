package LeftOuterJoin;

import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.RawComparator;

/**
 * Created by winn on 17/5/21.
 */
public class PairOfStringsGroupComparator implements RawComparator<PairOfStrings>{
    @Override
    public int compare(PairOfStrings first, PairOfStrings second){
        return first.getLeft().compareTo(second.getLeft());
    }

    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2){
        DataInputBuffer buffer = new DataInputBuffer();
        PairOfStrings a = new PairOfStrings();
        PairOfStrings b = new PairOfStrings();
        try{
            buffer.reset(b1,s1,l1);
            a.readFields(buffer);
            buffer.reset(b2,s2,l2);
            b.readFields(buffer);
            return compare(a,b);
        }catch (Exception e){
            return -1;
        }
    }
}
