package com.example.encriptado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class GenerarClave extends AppCompatActivity implements View.OnClickListener {
    EditText tamBloque, permutacion, sustitucion, iteracion, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_clave);
        tamBloque = (EditText) findViewById(R.id.numBloque);
        permutacion = (EditText) findViewById(R.id.numPermutacion);
        sustitucion = (EditText) findViewById(R.id.numSustitucion);
        iteracion = (EditText) findViewById(R.id.numIteracion);
        resultado = (EditText) findViewById(R.id.txtResultado);
    }
    //metodo para regresar

    public void Regresar(View view) {
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);

    }

    public String formato_clave(String b, String p, String s, String i) {
        String formateo = b + "," + p + "," + s + "," + i;
        return formateo;
    }

    public ArrayList<String> convertirBloques(String textoClaro, int bloques) {
        //Crenado el array

        ArrayList<String> ltBloques = new ArrayList<>();
        String temporal = ""; // creamos la variable en el las se almacena en grupos
        String sinEspacio = "";//guarda la cadena sin espacio
        int contador = 0;// nos permite saber si los bloques se completaron
        int pivote = 0;

//Para quitar los espacios
        for (char caracter : textoClaro.toCharArray()) {
            if (caracter != 32) {
                sinEspacio = sinEspacio + String.valueOf(caracter);
            }
        }
        int temp = bloques;
        int grupos = sinEspacio.length() / bloques;
        int residuo = sinEspacio.length() % bloques;
        boolean res = false;
        System.out.println("grupo: " + grupos);
        System.out.println("residuo: " + residuo);
        if (residuo > 0) {
            grupos = grupos + 1;
            res = true;
        }
        while (contador < grupos) {//se tiene en cuenta un rango de toda la cadena
            temporal = sinEspacio.substring(pivote, bloques);
            System.out.println("cadena1: " + temporal);
            ltBloques.add(temporal);
            contador++;
            pivote = pivote + temp;//
            bloques = bloques + temp;
            if (contador == grupos - 1 && res) {
                temporal = sinEspacio.substring(pivote, sinEspacio.length());
                int espacios = temp - residuo;
                for (int i = 0; i < espacios; i++) {
                    temporal = temporal + " ";
                }
                ltBloques.add(temporal);
                contador++;
            }
        }

        return ltBloques;
    }

    public ArrayList<String> sustitucion(ArrayList<String> cadenaEnBloques, boolean suma, int valor) {//
        ArrayList<String> ltSustitucionTemp = cadenaEnBloques;

        for (int i = 0; i < cadenaEnBloques.size(); i = i + 2) {//
            String nuevaPalabra = "";
            for (char j : cadenaEnBloques.get(i).toCharArray()) {
                System.out.print("letra:" + j);
                int letra = (int) j;
                System.out.print(" ascii: " + letra);
                int despues = 0;
                if (suma) {//
                    despues = letra + valor;
                } else {
                    despues = letra - valor;
                }
                System.out.println(" despues: " + despues);
                nuevaPalabra = nuevaPalabra + String.valueOf((char) despues);//
            }
            ltSustitucionTemp.set(i, nuevaPalabra);///
        }
        return ltSustitucionTemp;
    }

    public ArrayList<String> permutacion(ArrayList<String> cadenaSustituida) {
        ArrayList<String> ltPermutacion = cadenaSustituida;
        String nuevaPalabra = "";
        String orden = "2413";
        for (int i = 0; i < cadenaSustituida.size(); i = i + 2) {
            int contador = 0;
            nuevaPalabra = "";
            while (contador < cadenaSustituida.get(i).length()) {
                int pos = Integer.parseInt(String.valueOf(orden.charAt(contador)));
                nuevaPalabra = nuevaPalabra + String.valueOf(cadenaSustituida.get(i).charAt(pos - 1));

                contador++;
            }
            ltPermutacion.set(i, nuevaPalabra);

        }

        for (String n : ltPermutacion) {
            System.out.println("Permutacion" + n);
        }
        return ltPermutacion;
    }

    //Cambio de posiscion
    public ArrayList<String> cambiarPosicion(ArrayList<String> cadenaSustituida) {
        System.out.println("Cambio de posicion");
        ArrayList<String> ltPosicion = cadenaSustituida;

        int tam = cadenaSustituida.size();
        boolean par = true;
        if (tam % 2 != 0) {
            tam = tam - 1;
            par = false;
        }
        for (int k = 0; k < tam; k = k + 2) {
            String temp = cadenaSustituida.get(k + 1);
            ltPosicion.set(k + 1, cadenaSustituida.get(k));
            ltPosicion.set(k, temp);
        }
        return ltPosicion;
    }

    public ArrayList<String> permutacionDeco(ArrayList<String> cadenaSustituida) {//
        ArrayList<String> ltPermutacion = cadenaSustituida;
        String orden = "2413";
        String nuevaPalabra = "";
        String l1 = "";
        String l2 = "";
        String l3 = "";
        String l4 = "";
        String l5 = "";
        String l6 = "";
        String l7 = "";
        String l8 = "";


        for (int i = 0; i < cadenaSustituida.size(); i = i + 2) {
            int contador = 0;

            nuevaPalabra = "";
            l1 = "";
            l2 = "";
            l3 = "";
            l4 = "";
            l5 = "";
            l6 = "";
            l7 = "";
            l8 = "";

            while (contador < cadenaSustituida.get(i).length()) {
                System.out.println("Valor de I: " + i);
                int pos = Integer.parseInt(String.valueOf(orden.charAt(contador)));
                System.out.println("Posición: " + pos);
                if (pos == 1) {
                    l1 = String.valueOf(cadenaSustituida.get(i).charAt(contador));
                }
                if (pos == 2) {
                    l2 = String.valueOf(cadenaSustituida.get(i).charAt(contador));
                }
                if (pos == 3) {
                    l3 = String.valueOf(cadenaSustituida.get(i).charAt(contador));
                }
                if (pos == 4) {
                    l4 = String.valueOf(cadenaSustituida.get(i).charAt(contador));
                }

                if (pos == 5) {
                    l5 = String.valueOf(cadenaSustituida.get(i).charAt(contador));
                }
                if (pos == 6) {
                    l6 = String.valueOf(cadenaSustituida.get(i).charAt(contador));
                }
                if (pos == 7) {
                    l7 = String.valueOf(cadenaSustituida.get(i).charAt(contador));
                }
                if (pos == 8) {
                    l8 = String.valueOf(cadenaSustituida.get(i).charAt(contador));
                }
                contador++;
            }
            nuevaPalabra = l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8;
            System.out.println("Valor de comando: " + nuevaPalabra);
            ltPermutacion.set(i, nuevaPalabra);

        }


        return ltPermutacion;
    }


    @Override
    public void onClick(View v) {
        int itam_bloq, iper;
        if (v.getId() == R.id.btnAceptar) {
            tamBloque.setError(null);
            itam_bloq = Integer.parseInt(tamBloque.getText().toString());
            iper = Integer.parseInt(permutacion.getText().toString());
            if (itam_bloq % 2 == 0) { //verifcar si el tamanio es par
                permutacion.setError(null);
                int maxVal = itam_bloq / 2;
                if (permutacion.getText().length() == (itam_bloq / 2)) {
                    for (int i = 1; i < maxVal; i++) {
                        if (permutacion.getText().charAt(0) != permutacion.getText().charAt((i))) {
                            char el = sustitucion.getText().charAt(0);
                            char sig = '+';
                            char sign = '-';
                            if (sustitucion.length() == 1 && (el != sig && el != sign)) {
                                sustitucion.setError("Debe Ingresar +  o - ");
                                sustitucion.requestFocus();
                                return;
                            }
                        } else {
// Si no, entonces indicamos el error y damos focus


                            permutacion.setError("NO se deben repetir los números ");
                            permutacion.requestFocus();
                            return;
                        }
                    }


                } else {
                    permutacion.setError("Debe ser la mitad del tama..");

                    permutacion.requestFocus();

                }

                int rep = 0;
                ArrayList<String> textoCifrado = new ArrayList<>();
                while (rep < 2) {
                    textoCifrado = permutacion(
                            sustitucion(cambiarPosicion(
                                    permutacion(
                                            sustitucion(
                                                    convertirBloques(
                                                            formato_clave(tamBloque.getText().toString(),
                                                                    permutacion.getText().toString(), sustitucion.getText().toString(),
                                                                    iteracion.getText().toString()), 4), false, 4))), false, 4));
                    rep++;
                }
                String setTexto = "";
                for (String textoC : textoCifrado) {
                    setTexto += textoC;
                }
                System.out.println(setTexto);
                tamBloque.setText("");
                permutacion.setText("");
                sustitucion.setText("");
                iteracion.setText("");
                resultado.setText(setTexto);
            } else {
                tamBloque.setError("Solo Numeros pares");
                tamBloque.requestFocus();
            }
        }
        if (v.getId() == R.id.btnCopiar) {
            ClipboardManager clipboard = (ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("text", resultado.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copiado a portapapeles", Toast.LENGTH_LONG).show();
        }
    }

}








