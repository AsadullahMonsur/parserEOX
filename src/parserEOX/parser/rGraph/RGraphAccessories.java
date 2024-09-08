package parserEOX.parser.rGraph;

public class  RGraphAccessories {
    public static String[] get_graph_attributes(){
       return new String[]{"id","viewBox","width","height"};
    }

    public static String[] get_linear_gradient_attributes(){
        return new String[]{"id",
                "refer",
                "x1","y1",
                "x2","y2",
                "gradientTransform"};
    }

    public static String[] get_radial_gradient_attributes(){
        return new String[]{"id",
                "refer",
                "cx","cy",
                "fx","fy",
                "r",
                "transform",
                "gradientTransform"};
    }

    public static String[] get_stop_attributes(){
        return new String[]{"id","offset","stop-color","stop-opacity"};
    }

    public static String[] get_group_attributes(){
        return  new String[]{"id"};
    }

    public static String[] get_path_attributes(){
        return new String[]{"id", "width", "height", "sides", "x", "y", "rx", "ry",
                            "cx", "cy", "r1", "r2", "arg1", "arg2", "d", "transform",
                "opacity",
                "fill",
                "fill-opacity",
                "stroke",
                "stroke-width",
                "stroke-linecap",
                "stroke-linejoin",
                "stroke-dasharray",
                "stroke-opacity"};
    }

    public static String[] get_circle_attributes(){
        return new String[]{"id","style","cx","cy","r","transform", "opacity",
                "fill",
                "fill-opacity",
                "stroke",
                "stroke-width",
                "stroke-linecap",
                "stroke-linejoin",
                "stroke-dasharray",
                "stroke-opacity"};
    }

    public static String[] get_rectangle_attributes(){
        return new String[]{"id","style","width","height","x","y","rx","ry","transform",
                "opacity",
                "fill",
                "fill-opacity",
                "stroke",
                "stroke-width",
                "stroke-linecap",
                "stroke-linejoin",
                "stroke-dasharray",
                "stroke-opacity"};
    }

    public static String[] get_ellipse_attributes(){
        return new String[]{"id","style","cx","cy","rx","ry","transform",
                "opacity",
                "fill",
                "fill-opacity",
                "stroke",
                "stroke-width",
                "stroke-linecap",
                "stroke-linejoin",
                "stroke-dasharray",
                "stroke-opacity"};
    }
}
