package parserEOX.parser.svg;

public class InkscapeAccessories {
   public static String get_drawing_component_tag_by_name(String input){
       return input;
   }

   public static String[] get_svg_root_attributes(){
       return new String[]{"id","viewBox","width","height"};
    }

   public static String[] get_linear_gradient_attributes(){
      return new String[]{"id",
                          "xlink:href",
                          "x1","y1",
                          "x2","y2",
                          "gradientTransform"};
   }

   public static String[] get_radial_gradient_attributes(){
     return new String[]{"id",
                         "xlink:href",
                         "cx","cy",
                         "fx","fy",
                         "r",
                         "transform",
                         "gradientTransform"};
   }

   public static String[] get_stop_attributes(){
      return new String[]{"id","offset","style"};
   }

   public static String[] get_group_attributes(){
      return  new String[]{"id"};
   }

   public static String[] get_path_attributes(){
      return new String[]{"id",
                          "width",
                          "height",
                          "style",
                          "x",
                          "y",
                          "rx",
                          "ry",
                          "sodipodi:x",
                          "sodipodi:y",
                          "sodipodi:rx",
                          "sodipodi:ry",
                          "sodipodi:sides",
                          "sodipodi:cx",
                          "sodipodi:cy",
                          "sodipodi:r1",
                          "sodipodi:r2",
                          "sodipodi:arg1",
                          "sodipodi:arg2",
                          "d",
                          "transform"};
   }

   public static String[] get_circle_attributes(){
      return new String[]{"id","style","cx","cy","r","transform"};
   }

   public static String[] get_rectangle_attributes(){
       return new String[]{"id","style","width","height","x","y","rx","ry","transform"};
   }

    public static String[] get_ellipse_attributes(){
        return new String[]{"id","style","cx","cy","rx","ry","transform"};
    }
}
