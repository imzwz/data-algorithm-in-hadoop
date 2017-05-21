package RelativeFrequence;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * Created by winn on 17/5/21.
 */
public class RelativeFrequencyMapper extends Mapper<LongWritable, Text, PairOfWords, IntWritable> {
    private int neighborWindow = 2;
    private final PairOfWords pair = new PairOfWords();
    private final IntWritable totalCount = new IntWritable();
    private static final IntWritable ONE = new IntWritable(1);

    @Override
    public void setup(Context context){
        this.neighborWindow = context.getConfiguration().getInt("neighbor.window", 2);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
        String[] tokens = value.toString().split(" ");
        if((tokens == null) || (tokens.length < 2)){
            return;
        }
        for(int i=0; i< tokens.length; i++){
            tokens[i] = tokens[i].replaceAll("\\W+", "");
            if(tokens[i].equals("")){
                continue;
            }
            pair.setWord(tokens[i]);

            int start = (i - neighborWindow < 0 ) ? 0 : i - neighborWindow;
            int end = (i + neighborWindow >= tokens.length) ? tokens.length -1 : i+neighborWindow;
            for (int j = start; j <= end; j++){
                if(j==i){
                    continue;
                }
                pair.setNeighbor(tokens[j].replaceAll("\\W+", ""));
                context.write(pair, ONE);
            }
            pair.setNeighbor("*");
            totalCount.set(end-start);
            context.write(pair, totalCount);
        }

    }
}
