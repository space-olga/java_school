import java.io.*;
import java.util.*;

public class CreateSet {
    static final String FILENAME = "C:\\Temp\\input2057.txt";

    public static void main(String[] argv) throws IOException {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            SetElements setElements = new SetElements();

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;
            int i = 0, operation = 0;

            while ((sCurrentLine = br.readLine()) != null) {
                StringTokenizer st =
                        new StringTokenizer(sCurrentLine);
                while (st.hasMoreTokens()) {
                    operation = Integer.parseInt(st.nextToken());
                    if (i > 0) {
                        switch (operation) {
                            // добавить элемент в множество
                            case 1:
                                setElements.addElement(Integer.parseInt(st.nextToken()));
                                break;
                            // удалить минимальныей элемент из множества
                            case 2:
                                System.out.println(setElements.deleteElement());
                                break;
                        }
                    }
                    i++;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
