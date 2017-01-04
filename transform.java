import java.io.*;
import java.util.*;

public class transform{
  public static void main(String[] args){
    String text="";

    String[] parts=text.split(", ");
    String fileName="adj.txt";
    try{
      for(String s:parts){
        FileWriter writer = new FileWriter(fileName, true);
        PrintWriter print_liner = new PrintWriter(writer);
        print_liner.printf( "%s" + "%n" , s);
        print_liner.close();
      }
    }catch(Exception ex){}
  }
}
