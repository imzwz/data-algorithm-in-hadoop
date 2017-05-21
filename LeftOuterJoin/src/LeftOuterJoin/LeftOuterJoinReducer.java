package LeftOuterJoin;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by winn on 17/5/21.
 */
public class LeftOuterJoinReducer extends Reducer<PairOfStrings, PairOfStrings, Text, Text> {
    Text productID = new Text();
    Text locationId = new Text("undefined");

    @Override
    public void reduce(PairOfStrings key, Iterable<PairOfStrings> values, Context context) throws IOException, InterruptedException{
        Iterator<PairOfStrings> iterator = values.iterator();
        if(iterator.hasNext()){
            PairOfStrings firstPair = iterator.next();
            if(firstPair.getLeft().equals("L")){
                locationId.set(firstPair.getRight());
            }
        }
        while (iterator.hasNext()){
            PairOfStrings procuctPair = iterator.next();
            productID.set(procuctPair.getRight());
            context.write(productID, locationId);
        }
    }
}
