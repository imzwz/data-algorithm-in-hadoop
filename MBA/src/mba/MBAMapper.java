package mba;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by winn on 17/5/22.
 */
public class MBAMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    public static final int DEFAULT_NUMBER_OF_PAIRS = 2;
    private static final Text reducerKey = new Text();
    private static final IntWritable NUMBER_ONE = new IntWritable(1);

    int numberOfPairs;

    @Override
    protected void setup(Context context)throws IOException, InterruptedException{
        this.numberOfPairs = context.getConfiguration().getInt("number.of.pairs", DEFAULT_NUMBER_OF_PAIRS);

    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
        String line = value.toString();
        List<String> items = convertItemsToList(line);
        if((items == null) || (items.isEmpty())){
            return;
        }
        generateMapperOutput(numberOfPairs, items, context);
    }

    private static List<String> convertItemsToList(String line) {
        if((line==null) || (line.length()==0)){
            return null;
        }
        String[] tokens = line.split(",");
        if((tokens == null) || (tokens.length==0) ){
            return null;
        }
        List<String> items = new ArrayList<String>();
        for(String token : tokens){
            if(token != null){
                items.add(token.trim());
            }
        }
        return items;
    }

    private void generateMapperOutput(int numberOfPairs, List<String> items, Context context)throws IOException, InterruptedException {
        List<List<String>> sortedCombinations = Combination.findSortedCombinations(items, numberOfPairs);
        for(List<String> itemList: sortedCombinations){
            reducerKey.set(itemList.toString());
            context.write(reducerKey, NUMBER_ONE);
        }

    }

}
