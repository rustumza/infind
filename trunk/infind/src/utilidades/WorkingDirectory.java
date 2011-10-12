/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.File;
import java.net.URL;

/**
 *
 * @author desarrollo
 */
public class WorkingDirectory {

    private static File WORKING_DIRECTORY;
    private static String PATH;

    public static String getPath() {
        String Recurso = WorkingDirectory.class.getSimpleName() + ".class";
        
        if (PATH == null) {
            try {
                URL url = WorkingDirectory.class.getResource(Recurso);

                if (url.getProtocol().equals("file")) {
                    File f = new File(url.toURI());
                    System.out.println("1_ "+f.getPath());
                    PATH = f.getParentFile().getPath();

                } else if (url.getProtocol().equals("jar")) {

                    String expected = "!/" + Recurso;
                    String s = url.toString();
                    s = s.substring(4);
                    s = s.substring(0, s.length() - expected.length() + 1);
                    File f = new File(new URL(s).toURI());
                    System.out.println("1_ "+f.getPath());
                    PATH = f.getParentFile().getPath();

                }
            } catch (Exception ex) {
                PATH=".";
            }
        }
        return PATH;
    }

    public static File get() {

        String Recurso = WorkingDirectory.class.getSimpleName() + ".class";
        if (WORKING_DIRECTORY == null) {
            try {
                URL url = WorkingDirectory.class.getResource(Recurso);
                System.out.println(url);
                if (url.getProtocol().equals("file")) {
                    File f = new File(url.toURI());

                    System.out.println(f.getPath());
                    do {

                        f = f.getParentFile();
                    } while (!f.isDirectory());

                    WORKING_DIRECTORY = f;
                } else if (url.getProtocol().equals("jar")) {
                    String expected = "!/" + Recurso;
                    String s = url.toString();
                    s = s.substring(4);
                    s = s.substring(0, s.length() - expected.length() + 1);
                    File f = new File(new URL(s).toURI());

                    System.out.println(f.getPath());
                    do {

                        f = f.getParentFile();
                    } while (!f.isDirectory());
                    WORKING_DIRECTORY = f;
                }
            } catch (Exception e) {
                WORKING_DIRECTORY = new File(".");
            }
        }
        return WORKING_DIRECTORY;
    }

}
