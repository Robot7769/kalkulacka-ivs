package profiling;

import main.java.library.MathLib; //Matematická knihovna

import java.io.*;
import java.util.ArrayList;
public class Profiling {

    private final MathLib MathLib = new MathLib(); //Objekt matematické knihovny

    public static void main(String[] args) {
        ArrayList<Double> Values = new ArrayList<>();
        //Nacitani            //TODO predelat aby se dalo nacitat pomoci java Profiling *.txt
            File Objective = new File("src/profiling/data.txt"); //cesta v pocitaci, nefunkcni pro ostatni
            if(!Objective.exists() || !Objective.canRead()){
                System.out.println("Soubor nebyl nalezen.");
            }
        try (BufferedReader myReader = new BufferedReader(new FileReader(Objective))){
            String line;
            while ((line = myReader.readLine()) != null) {
                if (line.length() > 0){
                   String[] parts = line.split(" ");
                    for (int j = 0; j < parts.length; j++) {
                        try {
                            Values.add(Double.parseDouble(parts[j]));
                        }catch (Exception e){
                            System.out.println("Neobsahuje cislo.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

     for (int j = 0; j < Values.size(); j++) { //test vypisu dat
            System.out.println(Values.get(j));
        }
  /*
        double counter = 0.0;
        double NumOfNum = Values.size();

        for (int i = 0; i < Values.size(); i++) {
            counter += Values.get(i);
        }

        double scale = MathLib.div(counter, NumOfNum);

        double root = main.java.library.MathLib.mul(MathLib.div(1,1-Values),MathLib.pow(counter) - MathLib.mul(Values, MathLib.pow(scale)));

        double amplitude = MathLib.sqrt(root);

        System.out.println(amplitude);

       */
    }
}
