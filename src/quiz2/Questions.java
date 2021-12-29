package quiz2;

import java.util.Arrays;
import java.util.Scanner;

public class Questions {
    Scanner sc = new Scanner(System.in);
    int numquestions=10;
    String[] Ques;
    String[] Ans;
    String[] Mchoice;
    StorageFile gameSto;

    public void initQuestion(){
        gameSto = new StorageFile();
        sc.useDelimiter("\n");
        Ques = gameSto.Load("Questions.txt");
        if(Ques == null || Ques.length == 0){
            Ques = new String[numquestions];
            Ques[0] = "Is Mr. Dirty Eyelet a space parasite that reproduces itself by implanting false happy memories?";
            Ques[1] = "In the dimension of Doofus Rick (silly Rick) eat excrement?";
            Ques[2] = "The first episode of Ricky and Morty in the United States was in 2013?";
            Ques[3] = "Does the mega seed cause paralysis if you put it in your rectum?";
            Ques[4] = "There are 6 members of the council of Ricks of the Citadel?";
            Ques[5] = "Who is Rick's best friend?";
            Ques[6] = "Rick and Morty have won an Oscar for animation?";
            Ques[7] = "Who Shot Poopy Butthole?";
            Ques[8] = "What is his last name Rick?";
            Ques[9] = "What sport does the 'Meeseks' try to teach Jerry?";
            
            Ans = new String[numquestions];
            Ans[0] = "NO";
            Ans[1] = "NO";
            Ans[2] = "YES";
            Ans[3] = "NO";
            Ans[4] = "YES";
            Ans[5] ="BIRD MAN";
            Ans[6] = "NO";
            Ans[7] = "BETH";
            Ans[8] = "SANCHEZ";
            Ans[9] = "GOLF";
            
            Mchoice = new String[numquestions];
            Mchoice[0] = "YES,NO";
            Mchoice[1] = "YES,NO";
            Mchoice[2] = "YES,NO";
            Mchoice[3] = "YES,NO";
            Mchoice[4] = "YES,NO";
            Mchoice[5] = "BIRD MAN,SQUANCHY ";
            Mchoice[6] = "YES,NO";
            Mchoice[7] = "BETH,JERRY";
            Mchoice[8] = "SANCHEZ,FERNANDEZ";
            Mchoice[9] = "GOLF,BADMINTON";        
        }else{
            Ans = gameSto.Load("Answers.txt");
            Mchoice = gameSto.Load("Choices.txt");
        }        
    }

    public void modifyQuestions(){
        System.out.println("These are the available questions:");
        for (int i = 0; i < Ques.length; i++) {
            System.out.println(i+".-"+Ques[i]);
        }
        System.out.println("What question do you want to modify?");
        int modify = sc.nextInt();
        System.out.println("Write the modification of the question");
        Ques[modify] = sc.next();
        System.out.println("Modify your multiple choice (Separate each choice with a ',')");
        Mchoice[modify] = sc.next();
        System.out.println("Modify the correct answer to the question");
        Ans[modify] = sc.next().toUpperCase();
        System.out.println();
        System.out.print(Ques[modify]+"\n"+ formatea(Mchoice[modify]+"\n"+Ans[modify]));
        Save();
    }
    
    public  static String[]  remueveElement(String[] arrayObjetos, int i) {
    String[] nuevoArray = new String[arrayObjetos.length - 1];
    int posicion=0;
        for (int j = 0; j < arrayObjetos.length; j++) {
           if(i!=j){
               nuevoArray[posicion] = arrayObjetos[j];
               posicion++;
           } 
        }
     return nuevoArray;
    }
    
    public void deleteQuestions(){          
        if(Ques.length<1){
            System.out.println("Error, not all questions can be deleted");
        }else{
            System.out.println("These are the available questions:");
            for (int i = 0; i < Ques.length; i++) {
                System.out.println(i+".-"+Ques[i]);
            }
            System.out.println();
            System.out.println("What questions do you want to eliminate?");
            int eliminar = sc.nextInt();
            System.out.println("These are the questions that you can delete");
            if(eliminar < Ques.length){
                this.Ques = remueveElement(Ques,eliminar);
                this.Mchoice = remueveElement(Mchoice,eliminar);
                this.Ans = remueveElement(Ans,eliminar);
                this.numquestions = Ques.length;
            }else{
                System.out.println("It's not possible to delete this question");
            }
        }
        Save();
    }
    
    public void addQuestions(){
        boolean pedir = true;
        boolean continuar = true;
        do{
            int N = Ques.length;
            Ques = Arrays.copyOf(Ques, N + 1);
            System.out.println("Write a question to add it:");
            Ques[N] = sc.next();
            Mchoice = Arrays.copyOf(Mchoice, N + 1);
            System.out.println("Write the multiple options: (Separate each choice with a ',')");
            Mchoice[N] = sc.next();
            Ans = Arrays.copyOf(Ans, N + 1);
            System.out.println("Write the correct answer of the question to add it:");
            Ans[N] = sc.next().toUpperCase();
            do{
                System.out.println("Do you want to keep adding? (Y or N)");
                char respuesta = sc.next().toUpperCase().charAt(0);
                switch (respuesta) {
                    case 'N':
                        for (int i = 0; i < Ques.length; i++) {
                            System.out.println(i +".- "+ Ques[i]);
                            System.out.println(this.formatea(Mchoice[i]));
                            System.out.println("Correct Answer: "+ Ans[i]);
                            System.out.println();
                        }   pedir=false;
                        continuar = false;
                        break;
                    case 'Y':
                        pedir=false;
                        break;
                    default:
                        System.out.println("Wrong value, answer only (Y or N)");
                        break;
                }
                
            }while(pedir);  
        }while(continuar == true); 
        this.numquestions = Ques.length;
        Save();
    }
    
    public String formatea(String cadena){
        String devuelve = "";
        String[] array = cadena.split(","); 
        for (String array1 : array) {
            devuelve = devuelve + "- " +array1+ "\n" ;
        }
        return devuelve; 
    }
    
    public void Save(){
        gameSto.Save(Ques,"Questions.txt");
        gameSto.Save(Ans,"Answers.txt");
        gameSto.Save(Mchoice,"Choices.txt");
    }
    
    public void Reset(){
        gameSto.Reset("Questions.txt");
        initQuestion();
        Save();
    }
    
    public void MenuQuestions(){
        boolean pedir = true;
        do{
            System.out.println("POSSIBLE OPTIONS: \n   1.-Add \n   2.-Delete \n   3.-Modify \n   4.-Reset questions \n   5.-Exit to the menu");
            int opcion = sc.nextInt();
            System.out.println("-------------------");
            switch (opcion) {
                case 1:
                    System.out.println("You have selected the add option");
                    addQuestions();
                    break;
                case 2:
                    System.out.println("You have selected the delete option");
                    deleteQuestions();
                    break;
                case 3:
                    System.out.println("You have selected the modify option");
                    modifyQuestions();
                    break;
                case 4:
                    System.out.println("You have selected the Reset option");
                    Reset();
                    break;
                case 5:
                    System.out.println("You have exit to the menu");
                    pedir=false;
                    break;
                default:
                    System.out.println("Error, not a possible choose");
                    break;
            }
        }while(pedir==true);
    }
}
