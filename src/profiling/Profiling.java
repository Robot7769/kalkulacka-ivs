package profiling;

import main.java.library.MathLib; //Matematická knihovna

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Profiling {

    private final main.java.library.MathLib MathLib = new MathLib(); //Objekt matematické knihovny

    public static void main(String[] args){
        try {                                           //Nacitani            //TODO predelat aby se dalo nacitat pomoci java Profiling *.txt && presunout data do pole
            File Objective = new File("src/profiling/data.txt");
            Scanner myReader = new Scanner(Objective);
            while (myReader.hasNext()){
                String data = myReader.next();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Soubor nebyl nalezen.");
            //e.printStackTrace();
        }



        /* dodelat prevod z nacitani do rovnice
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
