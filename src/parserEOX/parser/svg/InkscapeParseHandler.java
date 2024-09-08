package parserEOX.parser.svg;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

public class InkscapeParseHandler extends DefaultHandler {
    private boolean t_svg = false;
    private boolean t_g = false;
    private boolean t_lg = false;
    private boolean t_rg = false;
    private boolean t_stop = false;
    private boolean t_path = false;
    private boolean t_circle = false;
    private boolean t_ellipse = false;
    private boolean t_rect = false;

    private InkscapeNotificationListener listener;
    private StringBuilder output_writer;
    private String output_file_name;
    private String output_path;

    public InkscapeParseHandler(InkscapeNotificationListener listener, String output_file_name, String output_path) {
       output_writer = new StringBuilder();
       this.output_file_name = output_file_name;
       this.output_path = output_path;
       this.listener = listener;
    }

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
        return super.resolveEntity(publicId, systemId);
    }

    @Override
    public void startDocument() throws SAXException {
      if(output_writer!=null){
        String xml_version = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
        output_writer.append(xml_version).append("\n");
      }
    }

    @Override
    public void endDocument() throws SAXException {
      try {
//          System.out.println(output_writer.toString());
//        String data = XMLFormatter.format(output_writer.toString());
        RGraphExporter.export(listener,output_file_name, output_path, output_writer.toString());
      }
      catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage()+"\n"+e.getCause());
      }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
     try {
         if(qName.equalsIgnoreCase("svg")){
             t_svg = true;
             process_root_tag(attributes);
         }
         else if(qName.equalsIgnoreCase("linearGradient")){
             System.out.println("\nlinearGradient tag started");
             t_lg = true;

             process_linear_gradient_tag(attributes);
         }
         else if(qName.equalsIgnoreCase("radialGradient")){
             System.out.println("\nradialGradient tag started");
             t_rg = true;

             process_radial_gradient_tag(attributes);
         }
         else if(qName.equalsIgnoreCase("stop")){
             t_stop = true;
             if(t_lg==true){
                 System.out.println("stops");
                 process_linear_gradient_block(attributes);
             }
             else if(t_rg==true){
                 System.out.println("stops");
                 process_linear_gradient_block(attributes);
             }
         }
         else if(qName.equalsIgnoreCase("g")){
            t_g = true;
            process_group_tag(attributes);
         }
         else if(qName.equalsIgnoreCase("path")){
             t_path = true;
             process_path_tag(attributes);
         }
         else if(qName.equalsIgnoreCase("circle")){
             t_circle = true;
             process_circle_tag(attributes);
         }
         else if(qName.equalsIgnoreCase("ellipse")){
             t_ellipse = true;
             process_ellipse_tag(attributes);
         }
         else if(qName.equalsIgnoreCase("rect")){
             t_rect = true;
             process_rectangle_tag(attributes);
         }
     }
     catch (IOException e){
         e.printStackTrace();
         System.out.println(e.getMessage()+"\n"+e.getCause());
     }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("svg")){
            output_writer.append("</r-graph>");
            System.out.println("\n End of SVG File:" + qName);
            t_svg = false;
        }

        else if (qName.equalsIgnoreCase("g")){
            output_writer.append("  </g>").append("\n");;
            System.out.println("group tag ended \n");
            t_g = false;
        }

        else if (qName.equalsIgnoreCase("path")){
            System.out.println("path tag ended \n");
            t_path = false;
        }

        else if (qName.equalsIgnoreCase("circle")){
            System.out.println("circle tag ended \n");
            t_circle = false;
        }

        if (qName.equalsIgnoreCase("ellipse")){
            System.out.println("ellipse tag ended \n");
            t_ellipse = false;
        }

        else if (qName.equalsIgnoreCase("rect")){
            System.out.println("rect tag ended \n");
            t_rect = false;
        }

        else if (qName.equalsIgnoreCase("linearGradient")) {
            output_writer.append("  </linear-gradient>").append("\n");;
            System.out.println("linearGradient tag ended \n");
            t_lg = false;
        }

        else if (qName.equalsIgnoreCase("radialGradient")){
            output_writer.append("  </radial-gradient>").append("\n");;
            System.out.println("radialGradient tag ended \n");
            t_rg = false;
        }

        else if (qName.equalsIgnoreCase("stop")){
            if(t_lg){
              System.out.println("stop tag ended \n");
            }
            t_stop = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        super.skippedEntity(name);
        System.out.println(name);
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

    private void process_root_tag(Attributes attributes) throws IOException {
        output_writer.append("<r-graph");
        System.out.println("SVG Attributes: \n");

        for (String s:InkscapeAccessories.get_svg_root_attributes()){
           if(attributes.getValue(s)!=null){
               push_to_output_writer(s,attributes.getValue(s),"  ");
               System.out.println(s+"=\""+attributes.getValue(s)+"\"");
           }
        }
        output_writer.append(">").append("\n");
    }

    private void process_linear_gradient_tag(Attributes attributes) throws IOException {
        output_writer.append("  <linear-gradient");

        for (String s:InkscapeAccessories.get_linear_gradient_attributes()){
            if(attributes.getValue(s)!=null){
                if(s.equals("xlink:href")){
                    String attr = "refer";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else {
                    push_to_output_writer(s,attributes.getValue(s),"    ");
                }

                System.out.println(s+"=\""+attributes.getValue(s)+"\"");
            }
        }
        output_writer.append(">").append("\n");
    }

    private void process_linear_gradient_block(Attributes attributes) throws IOException {
      output_writer.append("    <stop");
      System.out.println("Total attributes: "+attributes.getLength());

      for (String s:InkscapeAccessories.get_stop_attributes()){
          if(attributes.getValue(s)!=null){
              push_to_output_writer(s,attributes.getValue(s),"      ");
          }
      }
      output_writer.append("/>").append("\n");
      System.out.println();
    }

    private void process_radial_gradient_tag(Attributes attributes) {
        output_writer.append("  <radial-gradient");

        for (String s:InkscapeAccessories.get_radial_gradient_attributes()){
            if(attributes.getValue(s)!=null){
                if(s.equals("xlink:href")){
                    String attr = "refer";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else {
                    push_to_output_writer(s,attributes.getValue(s),"    ");
                }

                System.out.println(s+"=\""+attributes.getValue(s)+"\"");
            }
        }
        output_writer.append(">").append("\n");
    }

    private void process_group_tag(Attributes attributes) {
        output_writer.append("  <g");
        System.out.println("Total attributes: "+attributes.getLength());

        for (String s:InkscapeAccessories.get_group_attributes()){
            if(attributes.getValue(s)!=null){
                push_to_output_writer(s,attributes.getValue(s),"    ");
            }
        }
        output_writer.append(">").append("\n");
        System.out.println();
    }

    private void process_path_tag(Attributes attributes) {
        output_writer.append("  <path");
        System.out.println("Total attributes: "+attributes.getLength());

        for (String s:InkscapeAccessories.get_path_attributes()){
            if(attributes.getValue(s)!=null){
                if(s.equals("sodipodi:sides")){
                    String attr = "sides";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:cx")){
                    String attr = "cx";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:cy")){
                    String attr = "cy";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:r1")){
                    String attr = "r1";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:r2")){
                    String attr = "r2";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:arg1")){
                    String attr = "arg1";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:arg2")){
                    String attr = "arg2";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:x")){
                    String attr = "x";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:y")){
                    String attr = "y";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:rx")){
                    String attr = "rx";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else if(s.equals("sodipodi:ry")){
                    String attr = "ry";
                    push_to_output_writer(attr,attributes.getValue(s),"    ");
                }
                else {
                    push_to_output_writer(s,attributes.getValue(s),"    ");
                }
            }
        }
        output_writer.append("/>").append("\n");
        System.out.println();
    }

    private void process_circle_tag(Attributes attributes) {
        output_writer.append("  <circle");
        System.out.println("Total attributes: "+attributes.getLength());

        for (String s:InkscapeAccessories.get_circle_attributes()){
            if(attributes.getValue(s)!=null){
                push_to_output_writer(s,attributes.getValue(s),"    ");
            }
        }
        output_writer.append("/>").append("\n");
        System.out.println();
    }

    private void process_ellipse_tag(Attributes attributes) {
        output_writer.append("  <ellipse");
        System.out.println("Total attributes: "+attributes.getLength());

        for (String s:InkscapeAccessories.get_ellipse_attributes()){
            if(attributes.getValue(s)!=null){
                push_to_output_writer(s,attributes.getValue(s),"    ");
            }
        }
        output_writer.append("/>").append("\n");
        System.out.println();
    }

    private void process_rectangle_tag(Attributes attributes) {
        output_writer.append("  <rectangle");
        System.out.println("Total attributes: "+attributes.getLength());

        for (String s:InkscapeAccessories.get_rectangle_attributes()){
            if(attributes.getValue(s)!=null){
                push_to_output_writer(s,attributes.getValue(s),"    ");
            }
        }
        output_writer.append("/>").append("\n");
        System.out.println();
    }

    private void push_to_output_writer(String var, String data,String indent){
        if(var.equals("style")){
           style_parts_organiser(data,indent);
        }
        else {
            output_writer.append("\n")
                    .append(indent)
                    .append(var)
                    .append("=\"")
                    .append(data)
                    .append("\"");
        }
    }

    private void style_parts_organiser(String data, String indent) {
        String []pot = data.split(";");

        for (String item: pot){
            String []pieces = item.split(":");
            output_writer.append("\n")
                    .append(indent)
                    .append(pieces[0])
                    .append("=\"")
                    .append(pieces[1])
                    .append("\"");
        }
    }
}
