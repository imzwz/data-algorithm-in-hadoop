package mba;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by winn on 17/5/22.
 */
public class MBADriver {
    public static void main(String[] args)throws Exception{
        Path input = new Path(args[0]);
        Path output = new Path(args[1]);
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(MBADriver.class);
        job.setJobName("Market Basket Analysis");

        FileInputFormat.addInputPath(job, input);
        FileOutputFormat.setOutputPath(job, output);

        job.setMapperClass(MBAMapper.class);
        job.setReducerClass(MBAReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        if(job.waitForCompletion(true)){
            return;
        }else{
            throw new Exception("MBADriver failed");
        }
    }
}
