package topN;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by winn on 17/5/18.
 */
public class TopNMapper extends Mapper<Text, IntWritable, NullWritable, Text> {
    private int N = 10;
    private SortedMap<Integer, String> top = new TreeMap<Integer, String>();

    @Override
    public void map(Text key, IntWritable value, Context context)throws IOException, InterruptedException{
        String keyAsString = key.toString();
        int frequency = value.get();
        String compositeValue = keyAsString + "," + frequency;
        top.put(frequency, compositeValue);
        if(top.size() > N){
            top.remove(top.firstKey());
        }
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException{
        this.N = context.getConfiguration().getInt("N", 10);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException{
        for(String str: top.values()){
            context.write(NullWritable.get(), new Text(str));
        }
    }
}
