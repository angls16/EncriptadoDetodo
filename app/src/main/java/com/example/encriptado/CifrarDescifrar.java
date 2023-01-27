package com.example.encriptado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class CifrarDescifrar extends AppCompatActivity {
    private EditText txt_original;
    private RadioButton rb1, rb2;
    private TextView resp;
    private ImageButton copia;
    String clave = "8,2413,-4,2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cifrar_descifrar);
        txt_original = (EditText) findViewById(R.id.txtIngresar);
        rb1 = (RadioButton) findViewById(R.id.rdBuCifrar);
        rb2 = (RadioButton) findViewById(R.id.rdBuDes);
        resp = (TextView) findViewById(R.id.txtResul);
        copia = (ImageButton) findViewById(R.id.btnCopiar1);
    }

    //metodo para regresar
    public void Regresar(View view) {
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String nuevaClave) {
        this.clave = nuevaClave;
    }

    public ArrayList<String> convertirBloques(String m, int bloques) {
        ArrayList<String> ltBloques = new ArrayList<>();
        String temporal = "";
        String sinEspacio = "";
        int contador = 0;
        int pivote = 0;
        //Para quitar los espacios
        for (char c : m.toCharArray()) {
            if (c != 32) {
                sinEspacio = sinEspacio + String.valueOf(c);
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
        while (contador < grupos) {
            temporal = sinEspacio.substring(pivote, bloques);
            System.out.println("cadena1: " + temporal);
            ltBloques.add(temporal);
            contador++;
            pivote = pivote + temp;
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

    public ArrayList<String> sustitucion(ArrayList<String> cadenaEnBloques, boolean suma, int valor) {
        ArrayList<String> ltSustitucionTemp = cadenaEnBloques;
        for (int i = 0; i < cadenaEnBloques.size(); i = i + 2) {
            String nuevaPalabra = "";
            for (char j : cadenaEnBloques.get(i).toCharArray()) {
                System.out.print("letra:" + j);
                int letra = (int) j;
                System.out.print(" ascii: " + letra);
                int despues = 0;
                if (suma) {
                    despues = letra + valor;
                } else {
                    despues = letra - valor;
                }
                System.out.println(" despues: " + despues);
                nuevaPalabra = nuevaPalabra + String.valueOf((char) despues);
            }
            ltSustitucionTemp.set(i, nuevaPalabra);
        }
        return ltSustitucionTemp;
    }

    // Permutacion
    public ArrayList<String> permutacion(ArrayList<String> cadenaSustituida, String orden) {

        ArrayList<String> ltPermutacion = cadenaSustituida;
        String nuevaPalabra = "";
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
            System.out.println(n);
        }
        return ltPermutacion;
    }

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

    // la lista
    public ArrayList<String> permutacionDeco(ArrayList<String> cadenaSustituida, String orden) {
        ArrayList<String> ltPermutacion = cadenaSustituida;
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

    //Metodo para el boton calcular
    public void Calcular(View view) {
        String textoriginal = txt_original.getParent().toString();
        int tam_bloq, per, iteracion;
        String susti;
        String textoSeparado[] = getClave().split(",");
        tam_bloq = Integer.parseInt(textoSeparado[0]);
        per = Integer.parseInt(textoSeparado[1]);
        susti = textoSeparado[2];
        iteracion = Integer.parseInt(textoSeparado[3]);
        if (rb1.isChecked() == true) {
            txt_original.setError(null);
            int maxVal = tam_bloq / 2;
            if (Integer.toString(per).length() == (tam_bloq / 2)) {
                for (int i = 1; i < maxVal; i++) {
                    if
                    (Integer.toString(per).charAt(0) != Integer.toString(per).charAt((i))) {
                        char el = susti.charAt(0);
                        char sig = '+';
                        char sign = '-';
                        if (susti.length() == 1 && (el != sig && el != sign)) {
                            Toast.makeText(this, "Error: Algoritmo incorrecto",Toast.LENGTH_LONG).show();
                            return;
                        }
                    } else {
                        // Si no, entonces indicamos el error y damos focus
                        Toast.makeText(this, "Error: Numeros repetidos en la permutacion",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            } else {
                Toast.makeText(this, "Error: La permutacion tiene tamaño incorrecto",Toast.LENGTH_LONG).show();
            }
            char operacion = susti.charAt(0);
            int nOperacion = Integer.parseInt(String.valueOf(susti.charAt(1)));
            boolean suma = true;
            if (operacion != '+') {
                suma = false;
            }
            int rep = 0;
            ArrayList<String> textoCifrado = new ArrayList<>();
            while (rep < iteracion) {
                textoCifrado = permutacion(
                        sustitucion(
                                cambiarPosicion(
                                        permutacion(
                                                sustitucion(
                                                        convertirBloques( txt_original.getText().toString(), tam_bloq / 2), suma, nOperacion), Integer.toString(per))), suma, nOperacion), Integer.toString(per));
                rep++;
            }
            String setTexto = "";
            for (String textoC : textoCifrado) {
                setTexto += textoC;
            }
            System.out.println(setTexto);
            resp.setText(setTexto);
        } else if (rb2.isChecked() == true) {
            txt_original.setError(null);
            int maxVal = tam_bloq / 2;
            if (Integer.toString(per).length() == (tam_bloq / 2)) {
                for (int i = 1; i < maxVal; i++) {
                    if (Integer.toString(per).charAt(0) != Integer.toString(per).charAt((i))) {
                        char el = susti.charAt(0);
                        char sig = '+';
                        char sign = '-';
                        if (susti.length() == 1 && (el != sig && el != sign)) {
                            Toast.makeText(this, "Error: Algoritmo incorrecto",Toast.LENGTH_LONG).show();
                            return;
                        }
                    } else {
                        Toast.makeText(this, "Error: Numeros repetidos en la permutacion",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            } else {
                Toast.makeText(this, "Error: La permutacion tiene tamaño incorrecto",Toast.LENGTH_LONG).show();
            }
            char operacion = susti.charAt(0);
            int nOperacion = Integer.parseInt(String.valueOf(susti.charAt(1)));
            boolean suma = false;
            if (operacion != '+') {
                suma = true;
            }
            int rep = 0;
            ArrayList<String> textoClaro = new ArrayList<>();
            while (rep < iteracion) {
                textoClaro = permutacionDeco(
                        sustitucion(
                                cambiarPosicion(
                                        permutacionDeco(
                                                sustitucion(

                                                        convertirBloques(txt_original.getText().toString(), tam_bloq / 2), suma, nOperacion), Integer.toString(per))), suma, nOperacion), Integer.toString(per));
                rep++;
            }
            String setTexto = "";
            for (String textoC : textoClaro) {
                setTexto += textoC;
            }
            System.out.println(setTexto);
            resp.setText(setTexto);
        }
        copia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnCopiar1) {
                    ClipboardManager clipboard = (ClipboardManager)
                            getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("text",
                            resp.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(CifrarDescifrar.this, "Copiado a portapapeles", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

