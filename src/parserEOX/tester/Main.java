package parserEOX.tester;

import parserEOX.parser.object.GraphData;
import parserEOX.parser.rGraph.RGraphNotificationListener;
import parserEOX.parser.svg.InkscapeNotificationListener;
import parserEOX.parser.svg.InkscapeToRGraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
       // execute();

        FakeSVGPathGenerator.generate("m 64.318589,169.73811 c 5.933189,-2.23164 12.220601,-2.94973 18.485463,-3.54589 9.274266,-0.80362 18.577588,-0.76426 27.859928,-0.1915 6.86498,0.46941 13.64375,1.6284 20.18822,3.78168 2.75855,0.90763 7.57467,2.85819 10.33549,3.95161 7.04943,2.79846 13.6785,6.73319 19.20785,11.96598 1.14344,1.08211 2.17465,2.27694 3.26197,3.41542 6.44283,7.32227 12.07947,15.29497 17.83205,23.15698 3.84885,5.34256 7.76362,10.72785 12.88509,14.92344 4.83033,3.64246 10.71968,4.7329 16.55915,5.58384 5.71407,0.74595 11.49386,0.63628 17.24394,0.67502 3.82158,-0.17415 7.8167,-0.24909 11.44529,-1.63346 6.45097,-2.46114 12.09033,-6.67403 18.06136,-10.14089 5.8715,-4.53225 10.01911,-10.73935 13.74616,-17.05316 3.12797,-6.34354 4.91817,-13.18238 6.85902,-19.94551 2.30324,-7.41189 4.7417,-14.78448 7.49019,-22.04361 1.8622,-4.80237 0.88873,-2.47376 2.90635,-6.9917 0,0 -12.95349,5.94863 -12.95349,5.94863 v 0 c -1.89276,4.54403 -0.97444,2.21505 -2.74552,6.99064 -2.74921,7.32589 -5.32221,14.71642 -7.58923,22.20718 -1.99637,6.64253 -3.68494,13.43265 -6.96882,19.59799 -3.77821,6.21497 -8.03533,12.2881 -14.12175,16.45692 3.16646,-1.79153 6.34791,-3.5568 9.49938,-5.37458 0.90019,-0.51923 -1.79807,1.04985 -2.74476,1.47848 -3.50481,1.58689 -7.42899,1.85362 -11.21279,2.02587 -5.71856,0.0218 -11.45059,0.0215 -17.14955,-0.51178 -5.64299,-0.67168 -11.30927,-1.61269 -16.04702,-4.99438 -5.1969,-3.9295 -9.15695,-9.22609 -13.14232,-14.3224 -5.87167,-7.84095 -11.42471,-15.94605 -17.91366,-23.30248 -1.10566,-1.18489 -2.14949,-2.43065 -3.31699,-3.55466 -5.59322,-5.38491 -12.30183,-9.63971 -19.59309,-12.28086 -5.33836,-2.01314 -10.66354,-4.25166 -16.15864,-5.81221 -4.72436,-1.34168 -9.59648,-1.88608 -14.46692,-2.36435 -9.3076,-0.652 -18.62331,-0.67957 -27.935585,-0.0162 -6.06065,0.46053 -12.159603,0.93159 -17.991144,2.7644");
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
