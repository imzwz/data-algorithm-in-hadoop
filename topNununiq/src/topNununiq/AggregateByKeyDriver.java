package topNununiq;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

/**
 * Created by winn on 17/5/19.
 */
public class AggregateByKeyDriver extends Configured implements Tool{
    private static Logger theLogger = Logger.getLogger(AggregateByKeyDriver.class);

    public int run(String[] args) throws Exception{
        Job job = new Job(getConf());
        job.setJobName("AggregateByKeyDriver");


        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(AggregateByKeyMapper.class);
        job.setReducerClass(AggregateByKeyReducer.class);
        job.setCombinerClass(AggregateByKeyReducer.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean status = job.waitForCompletion(true);
        theLogger.info("run():status=" + status);
        return status ? 0 : 1;
    }
/*
    public static void main(String[] args) throws Exception{
        if(args.length !=2){
            theLogger.warn("usage AggregateByKeyDriver <input> <output>");
            System.exit(1);
        }
        theLogger.info("inputDir="+args[0]);
        theLogger.info("outputDir="+args[1]);
        int returnStatus = ToolRunner.run(new AggregateByKeyDriver(), args);
        System.exit(returnStatus);
    }
*/
}
