import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.net.URI;

public class SalesAgg
{
	public static void main(String[] args) throws Exception 
	{
	    Configuration conf = new Configuration();

	    Job job = Job.getInstance(conf, "Sales Agg");
		job.setInputFormatClass(CustomInputFormat.class);
	    job.setJarByClass(SalesAgg.class);
	    job.setMapperClass(TokenizerMapper.class);
	    job.setCombinerClass(Aggregator.class);
	    job.setReducerClass(Aggregator.class);
	    job.setOutputKeyClass(CompositeKey.class);
	    job.setOutputValueClass(DoubleWritable.class);
		FileInputFormat.setMaxInputSplitSize(job,10000000);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.addCacheFile(new URI("/user/hpatki/data/keys.txt"));
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
}
