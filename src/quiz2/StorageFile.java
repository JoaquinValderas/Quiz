package quiz2;
import java.io.*;
import java.util.Scanner;

public class StorageFile{
    File archivo;
    BufferedWriter bw;
    Scanner sc = new Scanner(System.in);
    boolean pedir = true;
    
    public void Create(String nombreArchivo){
        archivo = new File(nombreArchivo);
        try{
            if(!archivo.exists()){
                archivo.createNewFile();
            }else{
                archivo.delete();
                archivo.createNewFile();
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public String[] Load(String nombreArchivo) {
        // TODO falta q no lo abra dos 
        BufferedReader br;
        String array[] = null;
        try {
            br = new BufferedReader(new FileReader(archivo));
            String linea;
            int numlin = 0;
            while ((linea = br.readLine()) != null) {
               numlin++;
            }
            array = new String[numlin];
            br.close();
            br = new BufferedReader(new FileReader(archivo));
            numlin=0;
            while ((linea = br.readLine()) != null) {
                array[numlin]=linea;
               numlin++;
            }
        }catch(Exception e){
            System.out.println(e);    
        }
        return array;
    }
    
    
    public void Save(String[] ArrayTemp, String nombreArchivo){
        Create(nombreArchivo);
        try{
            bw = new BufferedWriter(new FileWriter(archivo,true));
            for (int i = 0; i < ArrayTemp.length; i++){
                bw.write(ArrayTemp[i]+"\n");
            } 
            bw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
