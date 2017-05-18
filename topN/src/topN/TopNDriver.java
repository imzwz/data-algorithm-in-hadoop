package topN;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

/**
 * Created by winn on 17/5/18.
 */
public class TopNDriver extends Configured implements Tool {
    private static Logger theLogger = Logger.getLogger(TopNDriver.class);

    public int run(String[] args) throws Exception{
        Job job = new Job(getConf());
        job.setJarByClass(TopNDriver.class);
        int N = Integer.parseInt(args[0]);
        job.getConfiguration().setInt("N", N);
        job.setJobName("TopNDriver");
        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        job.setMapperClass(TopNMapper.class);
        job.setReducerClass(TopNReducer.class);
        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        boolean status = job.waitForCompletion(true);
        theLogger.info("run(): status=" + status);
        return status ? 0: 1;
    }

    public static void main(String[] args) throws Exception{
        if(args.length!=3){
            theLogger.warn("usage TopNDriver <N> <input> <output>");
            System.exit(1);
        }
        theLogger.info("N="+args[0]);
        theLogger.info("inputDir="+args[1]);
        theLogger.info("outputDir="+args[2]);
        int returnStatus = ToolRunner.run(new TopNDriver(), args);
        System.exit(returnStatus);

    }
}
