package quiz2;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner sc;
    DecimalFormat df;
    Random Qrandom;
    Configuration gameCon;
    Questions gameQues;
    StorageFile gameSto;
    Rankings Rank;
    final double BONIFICATION = 0.5;
    double correct =0;
    double bonuspoint = 0;
    double incorrect = 0;
    double consecutiveerrors = 0;
    double winstreak = 0;   
    int[] comprobation;
    int i = 0;
   
    void Run(){
        initGame();
        MenuGame();
    }
    
    private void initGame(){
        gameCon = new Configuration();
        gameSto = new StorageFile();
        Rank = new Rankings();
        gameQues = new Questions();
        
        Qrandom = new Random();
        df = new DecimalFormat("0.00");
        sc=new Scanner(System.in);
        sc.useDelimiter("\n");
        df.setMaximumFractionDigits(2);
        gameQues.initQuestion();
        Rank.initRanking();
        Rank.SaveRank();
        Rank.Load();
        comprobation = new int[gameQues.Ques.length];
    }
    
    private void MenuGame(){
        boolean pedir = true;
        do{
            System.out.println("POSSIBLE OPTIONS: \n   1.-Play \n   2.-Configuration \n   3.-Exit Game");
            int opcion = sc.nextInt();
            System.out.println("-------------------");
            switch (opcion) {
                case 1:
                    System.out.println("You have selected the play option");
                    starQuiz();
                    break;
                case 2:
                    System.out.println("You have selected the Configuration option");
                    gameQues.MenuQuestions();
                    gameCon.MAXIMUM = gameQues.Ques.length;
                    break;
                case 3:
                    System.out.println(gameCon.BOLD + "THE GAME FINISH" + gameCon.RESET);      
                    pedir = false;
                    break;
                default:
                    System.out.println("Error, not a possible choose");
                    break;
            }
        }while(pedir == true);
    }
    
    private void starQuiz(){
        boolean continueQ = true;
        System.out.println(gameCon.BOLD + gameCon.QUIZNAME + "!!!" + gameCon.RESET);
        System.out.println("---------------------");
         do {
            System.out.println("How many questions do you want to answer?\n" + "The maximum questions to answer are "+ gameCon.MAXIMUM);
            gameCon.Q2answer = sc.nextInt();
            System.out.println();
            if (gameCon.Q2answer > gameCon.MAXIMUM) {
                System.out.println("Incorrect number, maximum is " + gameCon.MAXIMUM);
                System.out.println("Press anykey to continue or 'E' to exit the game");
                gameCon.exit = sc.next().toUpperCase().charAt(0);
                if (gameCon.exit == 'E') {
                    System.out.println("HAVE FINISHED THE QUIZ");
                    continueQ = false;
                }
            }else{
                comprobation = new int[gameQues.Ques.length];
                do {
                    int randomNumber = Qrandom.nextInt(gameQues.Ques.length);
                    if (comprobation[randomNumber] == 0){
                        comprobation[randomNumber] = 1;
                        System.out.print("" + (i + 1) + ". " + gameQues.Ques[randomNumber]+"\n"+ gameQues.formatea(gameQues.Mchoice[randomNumber]));
                        System.out.println("Answer:");
                        String answer = sc.next();
                        if (gameQues.Ans[randomNumber].equals(answer.toUpperCase())){
                            System.out.println("\n" + gameCon.GREENCOLOR + "Correct Answer!" + gameCon.RESET);
                            System.out.println();
                            correct++;
                            winstreak++;
                            i++;
                            consecutiveerrors = 0;
                            if (winstreak >= 3) {
                                System.out.println("You are on win streak, you have a bonification, +0.5 extra points");
                                System.out.println();
                                bonuspoint = bonuspoint + BONIFICATION;
                            }
                        }else if(!gameQues.Ans[randomNumber].equals(answer.toUpperCase())){
                            System.out.println("\n" + gameCon.REDCOLOR + "Incorrect" + gameCon.RESET + ".The correct answer is " + gameQues.Ans[randomNumber]);
                            System.out.println();
                            incorrect++;
                            winstreak = 0;
                            i++;
                            consecutiveerrors++;
                            if (winstreak == 0) {
                                System.out.println("You are not win streak finish");
                                System.out.println();
                            }
                            if (consecutiveerrors == 3) {
                                System.out.println(gameCon.REDCOLOR + "You have made 3 mistakes in a row" + gameCon.RESET);
                                i = gameCon.Q2answer;
                            }
                        }else{
                            System.out.println();
                            System.out.println("\n" + gameCon.REDCOLOR +"Error entering the answer"+ gameCon.RESET);
                            System.out.println();
                        }
                    }
                } while (i < gameCon.Q2answer);
                printScore();
                continueQ = playAgain(continueQ);
            }
        } while (continueQ);
    }

    private Boolean playAgain(Boolean tmpContinueQ) {
        System.out.println("Do you want to play again?(YES or NO)");
        String pagain = sc.next().toUpperCase();
        switch (pagain) {
            case "NO":
                System.out.println(gameCon.BOLD + "QUIz FINISH" + gameCon.RESET);
                tmpContinueQ = false;
                break;
            case "YES":
                correct = 0;
                incorrect = 0;
                i = 0;
                consecutiveerrors = 0;
                winstreak = 0;
                break;
            default:
                System.out.println(gameCon.REDCOLOR + "Error occurred, you  have introduced a incompatible decision" + gameCon.RESET);
                System.out.println(gameCon.BOLD + "QUIZ FINISH" + gameCon.RESET);
                tmpContinueQ = false;
                break;
        }
        return tmpContinueQ;
    }
    private void printScore(){
        double points = correct+bonuspoint;
        System.out.println();
        System.out.print("\t YOUR SCORE");
        System.out.println();
        System.out.println("\n Number of" + gameCon.GREENCOLOR + " correct" + gameCon.RESET + " answers: " + correct);
        System.out.println("\n Number of bonus points: " + bonuspoint);
        System.out.println("\n Number of" + gameCon.REDCOLOR + " incorrect" + gameCon.RESET + " answers: " + incorrect);
        System.out.println();
        gameCon.percent = (double) (points * 100) / gameCon.Q2answer;
        System.out.print("Your hit percentage is: ");
        System.out.print(df.format(gameCon.percent));
        System.out.print("%");
        System.out.println();
        if (gameCon.percent < 33) {
            System.out.println(gameCon.PERCENT33);
            System.out.println();
        } else if (gameCon.percent > 34 && gameCon.percent < 66) {
            System.out.println(gameCon.PERCENT34_66);
            System.out.println();
        } else if (gameCon.percent > 67 && gameCon.percent < 99) {
            System.out.println(gameCon.PERCENT67_99);
            System.out.println();
        } else {
            System.out.println(gameCon.PERCENT100);
            System.out.println();
        }
        Rank.StartRanking(points);
        Rank.ShowRanking();
    }
}
