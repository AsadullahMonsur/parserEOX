package parserEOX.parser.rGraph;

import parserEOX.parser.object.GraphData;

public interface RGraphNotificationListener {
    void onReceive(GraphData graph_data);
}
