package topNununiq;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
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
        JobConf conf = new JobConf(TopNDriver.class);

        Job job1 = new Job(conf, "merge");

        job1.setJarByClass(TopNDriver.class);
        int N = Integer.parseInt(args[0]);
        job1.getConfiguration().setInt("N", N);

        //job.setInputFormatClass(SequenceFileInputFormat.class);
        //job.setOutputFormatClass(SequenceFileOutputFormat.class);
        job1.setMapperClass(AggregateByKeyMapper.class);
        job1.setReducerClass(AggregateByKeyReducer.class);
        //job.setNumReduceTasks(1);

        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(IntWritable.class);

        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);
        job1.setOutputFormatClass(SequenceFileOutputFormat.class);

        ControlledJob ctrljob1 = new ControlledJob(conf);
        ctrljob1.setJob(job1);

        FileInputFormat.setInputPaths(job1, new Path(args[1]));
        FileOutputFormat.setOutputPath(job1, new Path(args[2]));

        Job job2 = new Job(conf,"topN");
        job2.setJarByClass(TopNDriver.class);

        job2.setInputFormatClass(SequenceFileInputFormat.class);
        job2.setMapperClass(TopNMapper.class);
        job2.setReducerClass(TopNReducer.class);

        job2.setMapOutputKeyClass(NullWritable.class);
        job2.setMapOutputValueClass(Text.class);
        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(Text.class);

        ControlledJob ctrljob2 = new ControlledJob(conf);
        ctrljob2.setJob(job2);
        ctrljob2.addDependingJob(ctrljob1);

        FileInputFormat.setInputPaths(job2, new Path(args[2]));
        FileOutputFormat.setOutputPath(job2, new Path(args[3]));

        JobControl jobControl = new JobControl("myControl");
        jobControl.addJob(ctrljob1);
        jobControl.addJob(ctrljob2);

        Thread t = new Thread(jobControl);
        t.start();
        while (true){
            if(jobControl.allFinished()){
                theLogger.info(jobControl.getSuccessfulJobList());
                jobControl.stop();
                break;
            }
        }

        return 0;

       // boolean status = job1.waitForCompletion(true);
       // theLogger.info("run(): status=" + status);
       // return status ? 0: 1;
    }

    public static void main(String[] args) throws Exception{
        if(args.length!=4){
            theLogger.warn("usage TopNDriver <N> <input> <midoutput> <output>");
            System.exit(1);
        }
        theLogger.info("N="+args[0]);
        theLogger.info("inputDir="+args[1]);
        theLogger.info("outputDir="+args[3]);
        int returnStatus = ToolRunner.run(new TopNDriver(), args);
        System.exit(returnStatus);

    }
}
