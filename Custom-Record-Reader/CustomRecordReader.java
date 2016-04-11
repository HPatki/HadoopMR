import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.io.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hpatki on 10/04/16.
 */
public class CustomRecordReader extends RecordReader {

    private boolean printed = false;
    private Path file = null;
    private Configuration jc;
    private Text record;
    private FSDataInputStream inputStream;
    private BufferedReader rdr;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException
    {
        System.out.println("Entered initialize...");
        FileSplit split = (FileSplit) inputSplit;
        file = split.getPath();
        jc = taskAttemptContext.getConfiguration();
        inputStream= FileSystem.get(jc).open(file);
        rdr = new BufferedReader(new InputStreamReader(inputStream.getWrappedStream()));
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException
    {
        String rec = null;
        if (null != (rec=rdr.readLine()))
        {
            record = new Text(rec);
            return true;
        }
        return false;
    }

    @Override
    public Object getCurrentKey() throws IOException, InterruptedException
    {
        return null;
    }

    @Override
    public Object getCurrentValue() throws IOException, InterruptedException {
        return record;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
