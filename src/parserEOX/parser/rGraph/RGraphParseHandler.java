package parserEOX.parser.rGraph;

import parserEOX.parser.object.GraphData;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.HashMap;

public class RGraphParseHandler extends DefaultHandler {

    private GraphData graph;
    private RGraphNotificationListener listener;

    private boolean t_group = false;
    private String group_element = "empty";
    private String group_tracker = "";

    private boolean t_lg = false;
    private String lg_element = "empty";
    private String lg_tracker = "";

    public RGraphParseHandler(RGraphNotificationListener listener) {
        graph = new GraphData();
        this.listener = listener;
    }

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
        return super.resolveEntity(publicId, systemId);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("\n r-graph parsing started");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\n r-graph parsing ended");
        listener.onReceive(graph);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if(qName.equalsIgnoreCase("r-graph")){
                System.out.println("\nr-graph tag started");
                processor_for_root_tag(attributes,RGraphAccessories.get_graph_attributes());
            }
            else if(qName.equalsIgnoreCase("linear-gradient")){
                t_lg = true;
                lg_tracker = attributes.getValue("id");
                System.out.println("\nlinearGradient tag started");
                processor(attributes,RGraphAccessories.get_linear_gradient_attributes(),graph.getLinear_gradient());
            }
            else if(qName.equalsIgnoreCase("radial-gradient")){
                System.out.println("\nradialGradient tag started");
                processor(attributes,RGraphAccessories.get_radial_gradient_attributes(),graph.getRadial_gradient());
            }
            else if(qName.equalsIgnoreCase("stop")){
                if(t_lg){
                    lg_element = lg_element+","+attributes.getValue("id");
                }
                System.out.println("stop tag started");
                processor(attributes,RGraphAccessories.get_stop_attributes(),graph.getStop());
            }
            else if(qName.equalsIgnoreCase("g")){
                t_group = true;
                group_tracker = attributes.getValue("id");
                System.out.println("\ngroup tag started");
                processor(attributes,RGraphAccessories.get_group_attributes(),graph.getGroup());
            }
            else if(qName.equalsIgnoreCase("path")){
                if(t_group){
                   group_element = group_element+","+attributes.getValue("id");
                }
                System.out.println("\npath tag started");
                processor(attributes,RGraphAccessories.get_path_attributes(),graph.getPath());
            }
            else if(qName.equalsIgnoreCase("circle")){
                if(t_group){
                    group_element = group_element+","+attributes.getValue("id");
                }
                System.out.println("\ncircle tag started");
                processor(attributes,RGraphAccessories.get_circle_attributes(),graph.getCircle());
            }
            else if(qName.equalsIgnoreCase("ellipse")){
                if(t_group){
                    group_element = group_element+","+attributes.getValue("id");
                }
                System.out.println("\nellipse tag started");
                processor(attributes,RGraphAccessories.get_ellipse_attributes(),graph.getEllipse());
            }
            else if(qName.equalsIgnoreCase("rectangle")){
                if(t_group){
                    group_element = group_element+","+attributes.getValue("id");
                }
                System.out.println("\nrectangle tag started");
                processor(attributes,RGraphAccessories.get_rectangle_attributes(),graph.getRectangle());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage()+"\n"+e.getCause());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("r-graph")){
            System.out.println("\nr-graph tag ended");
        }
        else if(qName.equalsIgnoreCase("linear-gradient")){
            if(!lg_tracker.equals("") && !lg_element.equals("") && !lg_element.equals("empty")){
                if(graph.getLinear_gradient().get(lg_tracker).get("x1")==null){
                    graph.getLinear_gradient().get(lg_tracker).put("elements",lg_element);
                }
            }
            lg_element = "empty";
            lg_tracker = "";
            t_lg = false;
            System.out.println("\nlinearGradient tag ended");
        }
        else if(qName.equalsIgnoreCase("radial-gradient")){
            System.out.println("\nradialGradient tag ended");
        }
        else if(qName.equalsIgnoreCase("stop")){
            System.out.println("stop tag ended");
        }
        else if(qName.equalsIgnoreCase("g")){
            if(!group_tracker.equals("") && !group_element.equals("") && !group_element.equals("empty")){
               graph.getGroup().get(group_tracker).put("elements",group_element);
            }
            group_element = "empty";
            group_tracker = "";
            t_group = false;
            System.out.println("\ngroup tag ended");
        }
        else if(qName.equalsIgnoreCase("path")){
            System.out.println("\npath tag ended");
        }
        else if(qName.equalsIgnoreCase("circle")){
            System.out.println("\ncircle tag ended");
        }
        else if(qName.equalsIgnoreCase("ellipse")){
            System.out.println("\nellipse tag ended");
        }
        else if(qName.equalsIgnoreCase("rectangle")){
            System.out.println("\nrectangle tag ended");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        super.skippedEntity(name);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }


    private void processor(Attributes attributes, String []selectors,HashMap<String, HashMap<String,String>> map){
        String id = "empty";
        HashMap<String,String> element_map = new HashMap<String, String>();

        for(String s:selectors){
            String value = attributes.getValue(s);
            if(value!=null){
                if(s.equals("id")){
                   id = value;
                }
                element_map.put(s,value);
            }
        }

        map.put(id,element_map);
    }

    private void processor_for_root_tag(Attributes attributes, String []selectors){
        HashMap<String,String> element_map = new HashMap<String, String>();

        for(String s:selectors){
            String value = attributes.getValue(s);
            if(value!=null){
                element_map.put(s,value);
            }
        }

        graph.insert_graph(element_map);
    }
}
