package LeftOuterJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;


/**
 * Created by winn on 17/5/21.
 */
public class LeftJoinDriver  {
    public static void main(String[] args) throws Exception{
        Path transactions = new Path(args[0]);
        Path users = new Path(args[1]);
        Path output = new Path(args[2]);

        Configuration conf = new Configuration();
        Job job = new Job(conf);
        job.setJarByClass(LeftJoinDriver.class);
        job.setJobName("Phase-1: left outer join");

        job.setPartitionerClass(PairOfStringsPartitioner.class);
        job.setGroupingComparatorClass(PairOfStringsGroupComparator.class);
        job.setReducerClass(LeftOuterJoinReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        MultipleInputs.addInputPath(job, transactions, TextInputFormat.class, LeftOuterJoinTransactionMapper.class);
        MultipleInputs.addInputPath(job, users, TextInputFormat.class, LeftOuterJoinUserMapper.class);
        job.setMapOutputKeyClass(PairOfStrings.class);
        job.setMapOutputValueClass(PairOfStrings.class);

        if(job.waitForCompletion(true)){
            return;
        }else{
            throw  new Exception("Phase-1: Left Outer Join job failed");
        }
    }
}
