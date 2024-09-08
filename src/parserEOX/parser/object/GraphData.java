package parserEOX.parser.object;

import java.util.HashMap;

public class GraphData {
    private HashMap<String,String> graph;
    private final HashMap<String,HashMap<String,String>> linear_gradient;
    private final HashMap<String,HashMap<String,String>> radial_gradient;
    private final HashMap<String,HashMap<String,String>> stop;
    private final HashMap<String,HashMap<String,String>> group;
    private final HashMap<String,HashMap<String,String>> path;
    private final HashMap<String,HashMap<String,String>> circle;
    private final HashMap<String,HashMap<String,String>> ellipse;
    private final HashMap<String,HashMap<String,String>> rectangle;

    public GraphData() {
        graph = new HashMap<String,String>();
        linear_gradient = new HashMap<String,HashMap<String,String>>();
        radial_gradient = new HashMap<String,HashMap<String,String>>();
        stop = new HashMap<String,HashMap<String,String>>();
        group = new HashMap<String,HashMap<String,String>>();
        path = new HashMap<String,HashMap<String,String>>();
        circle = new HashMap<String,HashMap<String,String>>();
        ellipse = new HashMap<String,HashMap<String,String>>();
        rectangle = new HashMap<String,HashMap<String,String>>();
    }

    public void insert_graph(HashMap<String,String> item) {
       graph = item;
    }

    public void insert_linear_gradient(String id,HashMap<String,String> item){
        linear_gradient.put(id,item);
    }

    public void insert_radial_gradient(String id,HashMap<String,String> item){
        radial_gradient.put(id,item);
    }

    public void insert_stop(String id,HashMap<String,String> item){
        stop.put(id,item);
    }

    public void insert_group(String id,HashMap<String,String> item){
        group.put(id,item);
    }

    public void insert_path(String id,HashMap<String,String> item){
        path.put(id,item);
    }

    public void insert_circle(String id,HashMap<String,String> item){
        circle.put(id,item);
    }

    public void insert_ellipse(String id,HashMap<String,String> item){
        ellipse.put(id,item);
    }

    public void insert_rectangle(String id,HashMap<String,String> item){
        rectangle.put(id,item);
    }

    public HashMap<String, String> getGraph() {
        return graph;
    }

    public HashMap<String, HashMap<String, String>> getLinear_gradient() {
        return linear_gradient;
    }

    public HashMap<String, HashMap<String, String>> getRadial_gradient() {
        return radial_gradient;
    }

    public HashMap<String, HashMap<String, String>> getStop() {
        return stop;
    }

    public HashMap<String, HashMap<String, String>> getGroup() {
        return group;
    }

    public HashMap<String, HashMap<String, String>> getPath() {
        return path;
    }

    public HashMap<String, HashMap<String, String>> getCircle() {
        return circle;
    }

    public HashMap<String, HashMap<String, String>> getEllipse() {
        return ellipse;
    }

    public HashMap<String, HashMap<String, String>> getRectangle() {
        return rectangle;
    }
}
