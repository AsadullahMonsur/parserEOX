package parserEOX.parser.rGraph;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class RGraphToObject {
    public RGraphToObject(InputStream stream,RGraphNotificationListener listener) {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            RGraphParseHandler handler = new RGraphParseHandler(listener);
            parser.parse(stream,handler);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage()+"\n"+e.getCause());
        }
    }
}
