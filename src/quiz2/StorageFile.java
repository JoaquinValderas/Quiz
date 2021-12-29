package quiz2;
import java.io.*;
import java.util.Scanner;

public class StorageFile{
    File filemanager;
    BufferedWriter bw;
    Scanner sc = new Scanner(System.in);
    boolean pedir = true;
    final String nombrefolder = "datos";
    String temp;
    Questions gameQues;

    public void CreateFolder(String NombreCarpeta){
        String dir = System.getProperty("user.dir");
        temp = dir+"/"+ NombreCarpeta;
        filemanager = new File(temp);
        try{
            if(!filemanager.exists()){
                filemanager.mkdir();
            }
        }catch(Exception e){
        }
    }
            
    public void Create(String nombreArchivo){
        CreateFolder(nombrefolder);
        nombreArchivo = nombrefolder+"/"+nombreArchivo;
        filemanager = new File(nombreArchivo);
        try{
            if(!filemanager.exists()){
                filemanager.createNewFile();
            }else{
                filemanager.delete();
                filemanager.createNewFile();
            }
        }catch(Exception e){
        }
    }
    
    public String[] Load(String nombreArchivo) {
        nombreArchivo = nombrefolder+"/"+nombreArchivo;
        // TODO falta q no lo abra dos 
        BufferedReader br;
        String array[] = null;
        try {
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            int numlin = 0;
            while ((linea = br.readLine()) != null) {
               numlin++;
            }
            array = new String[numlin];
            br.close();
            br = new BufferedReader(new FileReader(nombreArchivo));
            numlin=0;
            while ((linea = br.readLine()) != null) {
                array[numlin]=linea;
               numlin++;
            }
            br.close();
        }catch(Exception e){
        }
        return array;
    }
    
    public void Reset(String nombreArchivo){
        nombreArchivo = nombrefolder+"/"+nombreArchivo;
        filemanager = new File(nombreArchivo);
        filemanager.delete();
        try{
            filemanager = new File(nombreArchivo);
            filemanager.createNewFile();
            bw = new BufferedWriter(new FileWriter(filemanager,true));
            bw.write("");
            bw.close();
            }catch(Exception e){
        }
        System.out.println();
    }
    
    public void Save(String[] ArrayTemp, String nombreArchivo){
        Create(nombreArchivo);
        try{
            bw = new BufferedWriter(new FileWriter(filemanager,true));
            for (int i = 0; i < ArrayTemp.length; i++){
                bw.write(ArrayTemp[i]+"\n");
            } 
            bw.close();
        }catch(Exception e){
        }
    }
}
