package parserEOX.tester;

import parserEOX.parser.object.GraphData;
import parserEOX.parser.rGraph.RGraphNotificationListener;
import parserEOX.parser.rGraph.RGraphToObject;
import parserEOX.parser.svg.InkscapeNotificationListener;
import parserEOX.parser.svg.InkscapeToRGraph;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
       // execute();
    }

    private static void execute() {
        String path = "src/parserEOX/parser/sample";
        String name4 = "grand_path";
        String file_name = name4;

        try {
            File  file = new File("src/parserEOX/parser/sample/"+file_name+".svg");
            InputStream stream = new FileInputStream(file);
            InkscapeToRGraph parser_01 = new InkscapeToRGraph(stream,svg_listener,path,file_name);

            //RGraphToObject parser_02 = new RGraphToObject(new FileInputStream("src/parserEOX/parser/sample/"+file_name+".xml"),rgraph_listener);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final RGraphNotificationListener rgraph_listener = new RGraphNotificationListener() {
        @Override
        public void onReceive(GraphData graph_data) {
            System.out.println("Successfully Received");
            System.out.println(graph_data.getCircle().get("path1296"));
        }
    };

    private static final InkscapeNotificationListener svg_listener = new InkscapeNotificationListener() {
        @Override
        public void onReceive(List<String> info) {
            if(info!=null) {
                System.out.println(info.get(0));
            }
        }
    };
}
