package profiling;

import main.java.library.MathLib; //Matematická knihovna

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Profiling {

    private final main.java.library.MathLib MathLib = new MathLib(); //Objekt matematické knihovny

    public static void main(String[] args){
        try {//Pokus o nacaitani
            File Objective = new File("data.txt");
            Scanner myReader = new Scanner(Objective);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException error) {
            System.out.println("Soubor nebyl nalezen.");
            //error.printStackTrace();
        }



        /*
        int n = numbers.length;
        double counter = 0.0;

        for (int i = 0; i < n; i++) {
            counter += numbers[i];
        }

        double scale = MathLib.div(counter, n);

        double root = MathLib.mul(MathLib.div(1,1-n),MathLib.pow(counter) - MathLib.mul(n, MathLib.pow(scale)));

        double amplitude = MathLib.sqrt(root);

        System.out.println(amplitude);


    */
    }
}
