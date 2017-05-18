package secondary;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.SymlinkBaseTest;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.logging.Logger;

/**
 * Created by winn on 17/5/17.
 */
public class SecondarySortDriver extends Configured implements Tool {

    private static Logger theLogger = Logger.getLogger(String.valueOf(SecondarySortDriver.class));

    @Override
    public int run(String[] args) throws Exception{
        Configuration conf = getConf();
        Job job = new Job(conf);
        job.setJarByClass(SecondarySortDriver.class);
        job.setJobName("SecondarySortDriver");
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputKeyClass(DateTemperaturePair.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(SecondarySortMapper.class);
        job.setReducerClass(SecondarySortReducer.class);
        job.setPartitionerClass(DateTemperaturePartitioner.class);
        job.setGroupingComparatorClass(DateTemperatureGroupingComparator.class);

        boolean status = job.waitForCompletion(true);
        theLogger.info("run(): status="+status);
        return status ? 0 : 1;

    }

    public static void main(String[] args)throws Exception{
        if (args.length !=2){
            theLogger.warning("SecondarySortDriver <input-dir> <output-dir>");
            throw new IllegalArgumentException("SecondarySortDriver <input-dir> <output-dir>");
        }
        int returnStatus = submitJob(args);
        theLogger.info("returnStatus="+returnStatus);
        System.exit(returnStatus);
    }
    public static int submitJob(String[] args)throws Exception{
        int returnStatus = ToolRunner.run(new SecondarySortDriver(), args);
        return returnStatus;
    }
}
