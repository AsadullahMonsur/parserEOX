package parserEOX.parser.svg;

import java.io.*;
import java.util.List;

public class RGraphExporter {
  private static File file;
  private static BufferedWriter bw;
  private static FileWriter fw;

    public static void export(InkscapeNotificationListener listener, String file_name, String output_path, String data) throws IOException {
      file = new File(output_path+"/"+file_name+".xml");

      fw = new FileWriter(file,false);
      bw = new BufferedWriter(fw);
      bw.write(data);
      bw.flush();

      fw.close();
      bw.close();

      listener.onReceive(List.of("Successful"));
  }
}
