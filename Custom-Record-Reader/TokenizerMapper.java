import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

public class TokenizerMapper extends Mapper<Object, Text, CompositeKey, DoubleWritable>
{
    private List<String> keys = new ArrayList<String>();
    private Logger logger = Logger.getLogger("FileAppender");

    TokenizerMapper ()
    {
        //keys.add("154664001");
    }

    @Override
    public void setup (Context ctx)
    {
        String key = null;

        try
        {
            BufferedReader fr = new BufferedReader(new FileReader("./keys.txt"));
            while (null != (key=fr.readLine()))
            {
                keys.add(key);
                logger.info("Read key.." + key + " from file");
            }
        }
        catch(Exception e)
        {

        }
    }

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
    {
        String valueString = value.toString();
        String[] val = valueString.split("\t");
        //logger.info (val[0]);
        for (String onekey:keys)
        {
            //logger.info("checking key.." + onekey);

            if (onekey.equalsIgnoreCase(val[0]))
            {
                CompositeKey inst = new CompositeKey(val[0], val[1], val[2]);
                context.write(inst, new DoubleWritable(Double.parseDouble(val[6])));
                break;
            }
        }
    }
}
