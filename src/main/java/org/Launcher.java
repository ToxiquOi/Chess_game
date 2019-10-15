package org;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher {

    private static final Logger launcherLogger = Logger.getLogger(Launcher.class.getSimpleName());

    private static class StreamPrinter implements Runnable {

        private static final Logger StreamPrinterLogger = Logger.getLogger(StreamPrinter.class.getSimpleName());

        private final InputStream inputStream;

        private StreamPrinter(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            try {
                while ((line = br.readLine()) != null) {
                    launcherLogger.log(Level.INFO, line);
                }
            } catch (IOException e) {
                StreamPrinterLogger.log(Level.SEVERE, e.toString());
            }
        }
    }

    public static boolean run(String className,boolean enableOpenGL) {
        try {
            ArrayList<String> argumentList = new ArrayList<>();
            String javaHome = System.getProperty("java.home");
            argumentList.add( new File(javaHome, "bin/java").getPath() );
            argumentList.add("-cp");
            argumentList.add( System.getProperty("java.class.path") );

            if (enableOpenGL) {
                argumentList.add("-Dsun.java2d.opengl=True");
            }
            argumentList.add(className);

            ProcessBuilder pb = new ProcessBuilder(argumentList);
            pb.directory(new File(System.getProperty("user.dir")));

            Process p = pb.start();
            StreamPrinter outputStream = new StreamPrinter(p.getInputStream());
            StreamPrinter inputStream = new StreamPrinter(p.getErrorStream());
            new Thread(outputStream).start();
            new Thread(inputStream).start();

            p.waitFor();

            return p.exitValue() == 0;
        } catch (InterruptedException | IOException e) {
//            launcherLogger.log(Level.SEVERE, e.toString());
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public static void main(String[] args)
    {
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Voulez vous lancer avec OpenGL ?",
                "Paramètres lancement",
                JOptionPane.YES_NO_OPTION
        );
        run("org.chessgame.Main",selectedOption == JOptionPane.YES_OPTION);
    }
}
