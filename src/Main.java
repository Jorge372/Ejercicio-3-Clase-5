import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    /* Para el correcto funcionamiento deberá introducir ciertos parametros antes de compilar y correr el programa
        1) El caracter C si queire codificar o el caracter D si quiere decodificr
        2) El valor de cuantos numeros se desplazara en el codigo ASCII
        3) Ruta del archivo de entrada
        4) Ruta del archivo de salida
       Cada uno de los items deberan estar separados por un espacio " "
     */
    public static void main(String[] args) throws IOException {
        //tomamos el caso en el que unicamente tenemos el abecedario en minuscula que termina en espacio
        Path archivo1 = Paths.get(args[2]);
        Path archivo2 = Paths.get(args[3]);
        System.out.println(archivo1.toAbsolutePath());
        System.out.println(archivo2.toAbsolutePath());

        String frase="";

        for (String linea : Files.readAllLines(archivo1)) {
            frase= linea;
        }

        int ASCII;
        char nuevafrase[] = frase.toCharArray();
        int desplazamiento = Integer.parseInt(args[1]);

        if (args[0].equals("C")) {
            for (int i = 0; i < frase.length(); i++) {
                ASCII = frase.charAt(i) + desplazamiento;
                if (ASCII < 97)
                    ASCII = 96 + (ASCII - 32);
                else if (ASCII == 123)
                    ASCII = 32;
                else if (ASCII > 123)
                    ASCII = 96 + (ASCII - 123);

                nuevafrase[i] = (char) ASCII;
            }
        }
        else {
            for (int i = 0; i < frase.length(); i++) {
                ASCII = frase.charAt(i) - desplazamiento;
                if (ASCII < 32)
                    ASCII = 123 - (32 - ASCII);
                else if (ASCII == 96)
                    ASCII = 32;
                else if (ASCII < 96)
                    ASCII = 123 - (96 - ASCII);

                nuevafrase[i] = (char) ASCII;
            }
        }

        String resultado = String.valueOf(nuevafrase);
        Files.writeString(archivo2,resultado);
        System.out.println("El proceso se realizo con exito");
    }
}