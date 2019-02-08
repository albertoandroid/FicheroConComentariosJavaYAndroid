package com.androiddesdecero.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText etFile;
    private Button btSave;
    private Button btRead;
    private static final String FILE_NAME = "texto.txt";
    private static final String FILE_NAME_2 = "texto.txt";


    /*
    Hasta la fecha hemos visto a la hora de almacenar datos las variables.
    desde tipo primitivo como enteros a objetos como pueden ser objetos propios.
    Pero todos estos datos una vez se acaba la ejecución se pierden, se borran y ya nos los tenemos.
    Solo viven en tiempo de ejecución.
    Pero muchas veces necesitamos guardar los datos de forma permanente.
    Vamos a ver la entrada de datos y salida de datos.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFile = findViewById(R.id.etFile);
        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile();
            }
        });
        btRead = findViewById(R.id.btRead);
        btRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });
    }

    private void saveFile(){
        String textoASalvar = etFile.getText().toString();
        FileOutputStream fileOutputStream = null;
        try{
            //FileInputStream es la clase que nos permite escribir un fichero como un flujo de bytes.
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            //Escribimos en el fichero, importante en Bytes.
            fileOutputStream.write(textoASalvar.getBytes());
            Log.d("TAG", "Fichero salvado: " + getFilesDir() +"/" + FILE_NAME);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {

                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private void readFile(){
        FileInputStream fileInputStream = null;
        try {
            //FileInputStream es la clase que nos permite leer un fichero como un flujo de bytes.
            fileInputStream = openFileInput(FILE_NAME);
            //InputStremreader es un puente de flujos de Bytes a Caracteres.
            //Es decir lee bytes y los decodifica en caracteres.
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            //BufferesReader -> Para leer los datos. Lector de flujo de caracteres.
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //String y StrinBuilder para leer una linea y dejar todo en Strinbuilder
            StringBuilder stringBuilder = new StringBuilder();
            String lineaTexto;
            //readLine -> leer solo una linea de texto
            while ((lineaTexto = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineaTexto).append("\n");
            }

            etFile.setText(stringBuilder);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try{
                    fileInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    Siempre hay que saber el tipo de fichero que vamos a leer. Si va a ser un fichero de texto
    una imagen, musica, etc..
     */


    /*
    Hasta la fecha hemos visto a la hora de almacenar datos las variables.
    desde tipo primitivo como enteros a objetos como pueden ser objetos propios.
    Pero todos estos datos una vez se acaba la ejecución se pierden, se borran y ya nos los tenemos.
    Solo viven en tiempo de ejecución.
    Pero muchas veces necesitamos guardar los datos de forma permanente.
    Vamos a ver la entrada de datos y salida de datos.
     */

    public void leerFichero(){
        //FileReader -> para leer un fichero de texto.
        try{
            //FileReader -> para acceder al fichero. Pero no para
            //Leerlo
            FileReader fileReader = new FileReader(FILE_NAME_2);
            //BufferesReader -> Para leer los datos. Lector de flujo
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //readLine -> leer solo una linea de texto
            String lineaTexto;
            StringBuilder stringBuilder = new StringBuilder();
            //Mientras leer linea sea diferente de null
            //puedo segir leyendo
            while ((lineaTexto = bufferedReader.readLine())!=null){
                stringBuilder.append(lineaTexto).append("\n");
            }
            Log.d("TAG1", stringBuilder.toString());
        }catch (Exception e){

        }
    }

    public void escribirFichero(){
        try{
            //Abrir o Crear y Abrir fichero.
            FileWriter fileWriter = new FileWriter(FILE_NAME_2);
            //Acceder al Fichero para Escribir
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //Escribir
            String texto = "mi primera escritura en fichero";
            //Para escribir es bufferedwriter
            bufferedWriter.write(texto);
            //Hasta que no cierras el fichero no se salva.
            //Cerramos Buffer de escritura
            bufferedWriter.close();

        }catch (Exception e){

        }finally {

        }

    }

}
