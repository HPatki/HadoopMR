For anyone paying attention to the Map and Reduce classes, it will become clear that Hadoop processes data based on Key's. More often that not, we tend to imagine input files to contain 'record's', one per line. This is a very simplistic view of looking at input data. However, input data can be more complex than this. Data can be in binary form - PDF files, word documents, XML files et all.

Hadoop can process such data with a little help provided by the developer. As an example, let's assume we are going to process PDF files.
The PDF file does not contain 'individual records'. Rather, it could be 'one' record itself. Thus each file becomes one record. As we know, one Split is processed by one mapper. Thus all records in one split would be processed by one mapper - and consequently in our case, one PDF will be processed by one mapper. It will not get split across multiple mappers. However, that will be multiple PDF files for processing. And Hadoop will start multiple mappers to process multiple PDF files.

The above scenario brings into focus two aspects </br>
<pre>&#9 1. Need of a record reader for the input file </pre> 
<pre>&#9 2. Preprocessing step where multiple files are combined into one file for efficiency purposes </pre>
    
Having written the necessary Record Reader & Combiner classes, the combined file would then have multiple 'records' in it. Such a file is 'splittable' and can potentially get distributed across multiple mappers based on how the input splits happen. 
