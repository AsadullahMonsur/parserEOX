package parserEOX.parser.svg;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class InkscapeToRGraph {

 public InkscapeToRGraph(InputStream stream,InkscapeNotificationListener listener,String output_path,String output_file_name) {

  try{
   SAXParserFactory factory = SAXParserFactory.newInstance();
   SAXParser parser = factory.newSAXParser();
   InkscapeParseHandler handler = new InkscapeParseHandler(listener,output_file_name,output_path);
   parser.parse(stream,handler);
  }
  catch (Exception e){
   e.printStackTrace();
   System.out.println(e.getMessage()+"\n"+e.getCause());
  }
}

}
