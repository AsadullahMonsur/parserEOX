package parserEOX.formatter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLFormatter {
    public static String format(String input) {
        return operate(input, "0.5");
    }

    private static String operate(String input, String indent) {
        Source input_source = new StreamSource(new StringReader(input));
        StringWriter writer = new StringWriter();
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", indent);
            transformer.transform(input_source, new StreamResult(writer));

            return writer.toString().trim();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
