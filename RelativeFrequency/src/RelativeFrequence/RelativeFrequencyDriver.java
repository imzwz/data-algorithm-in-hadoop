package RelativeFrequence;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by winn on 17/5/21.
 */
public class RelativeFrequencyDriver {
    public static void main(String[] args)throws Exception{
        int neighborWindow = Integer.parseInt(args[0]);
        Path inputPath = new Path(args[1]);
        Path outputPath = new Path(args[2]);
        Job job = new Job(new Configuration(), "RelativeFrequencyDriver");
        job.setJarByClass(RelativeFrequencyDriver.class);
        job.setJobName("RelativeFrequencyDriver");

        job.getConfiguration().setInt("neighbor.window", neighborWindow);

        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        job.setMapOutputKeyClass(PairOfWords.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(PairOfWords.class);
        job.setOutputValueClass(DoubleWritable.class);

        job.setMapperClass(RelativeFrequencyMapper.class);
        job.setReducerClass(RelativeFrequencyReducer.class);
        job.setPartitionerClass(OrderInversionPartitioner.class);
        job.setGroupingComparatorClass(OrderInversionGroupingComparator.class);
        if(job.waitForCompletion(true)){
            return;
        }else{
            throw new Exception("RelativeFrequency job failed");
        }
    }
}
