package valency.deep.clean;
import java.awt.Image;
import valency.deep.clean.main_window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ValencyDeepClean {
    
   
    
    public static void main(String[] args) throws IOException {
        
        class ProcessHolder {
           Process cleaner_process = null;
        }
        
        class PathHolder{
            Path tempfile = null;
        }
        
        final ProcessHolder processHolder = new ProcessHolder();
        final PathHolder pathHolder = new PathHolder();
        
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }       
        
        System.out.println("Program Started");
        
        JFrame main_window = new main_window();
        
        Image image = ImageIO.read(ValencyDeepClean.class.getResource("/valency/deep/clean/Images/icon_bar.png"));
        
        main_window.setIconImage(image);
        main_window.setVisible(true);
        
        main_window window = new main_window();
        
       
        
       Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            
          try{
             if(processHolder.cleaner_process != null && processHolder.cleaner_process.isAlive()){
                  processHolder.cleaner_process.destroy();
                  processHolder.cleaner_process.waitFor();
             }
             
             if(pathHolder.tempfile != null){
                 Files.deleteIfExists(pathHolder.tempfile);
             }
             
              System.out.println("Connection closed");
             
          }catch(IOException | InterruptedException e){
              e.printStackTrace();
          }   
      
       }));
      
        InputStream path = ValencyDeepClean.class.getResourceAsStream("cleaner.py");
        
        System.out.println(path);
        
        if(path == null){
            System.err.println("python script not found in the desired path");
            return;
        }
        
        try{
          pathHolder.tempfile = Files.createTempFile("cleaner", ".py");
          Files.copy(path,pathHolder.tempfile,StandardCopyOption.REPLACE_EXISTING);
          
          ProcessBuilder processBuilder = new ProcessBuilder("python", pathHolder.tempfile.toString());
          processHolder.cleaner_process = processBuilder.start();
          
          BufferedReader input = new BufferedReader(new InputStreamReader(processHolder.cleaner_process.getInputStream()));
          String line;
          
          while((line = input.readLine()) != null){
              System.out.println(line);
          }
          
          int exitcode = processHolder.cleaner_process.waitFor();
          
            System.out.println("Script executed successfully with code:" + exitcode);
        
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        
        
        
        
       
    }
    
}
