package es.israeldelamo.openwebinar2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.israeldelamo.openwebinar2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        /**
         * La etiqueta de depuración
         */
        const val TAG = "DEPURANDO"
    }

    private var primerNumero= 0.0
    private var segundoNumero = 0.0
    private var operacion: String ?= null



    /**
     * Permite hacer referencia directa a los elementos del XML sin instanciarlo antes
     */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // con estas lineas asociamos la raiz del xml al esta clase
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //comenzamos con ninguna operacion
        operacion = null






        //que se encarge del onclickListener mi propia clase
        binding.boton0.setOnClickListener(this)
        binding.boton1.setOnClickListener(this)
        binding.boton2.setOnClickListener(this)
        binding.boton3.setOnClickListener(this)
        binding.boton4.setOnClickListener(this)
        binding.boton5.setOnClickListener(this)
        binding.boton6.setOnClickListener(this)
        binding.boton7.setOnClickListener(this)
        binding.boton8.setOnClickListener(this)
        binding.boton9.setOnClickListener(this)

        binding.botonMas.setOnClickListener(this)
        binding.botonMenos.setOnClickListener(this)
        binding.botonPor.setOnClickListener(this)
        binding.botonDividir.setOnClickListener(this)
        binding.botonComa.setOnClickListener(this)
        binding.botonLimpiar.setOnClickListener(this)
        binding.botonIgual.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        //para hacerlo clickeable le pasamos un when que leera quién le llama y actuara en consecuencia
       when (view){
           ////////////////////////////////////////////////////////////////////////////////////////
           /////////////////////////// ZONA DE NUMEROS /////////////////////////////////
           ////////////////////////////////////////////////////////////////////////////////////////
           //mediante el enlace a binding puedo hacer referencia a cada elemento directamente
           binding.boton0 -> {
               Log.d(TAG,"Botón 0")
               onNumeroPulsado("0")
            }
           binding.boton1 -> {
               Log.d(TAG,"Botón 1")
               onNumeroPulsado("1")
           }
           binding.boton2 -> {
               Log.d(TAG,"Botón 2")
               onNumeroPulsado("2")
           }
           binding.boton3 -> {
               Log.d(TAG,"Botón 3")
               onNumeroPulsado("3")
           }
           binding.boton4 -> {
               Log.d(TAG,"Botón 4")
               onNumeroPulsado("4")
           }
           binding.boton5 -> {
               Log.d(TAG,"Botón 5")
               onNumeroPulsado("5")
           }
           binding.boton6 -> {
               Log.d(TAG,"Botón 6")
               onNumeroPulsado("6")
           }
           binding.boton7 -> {
               Log.d(TAG,"Botón 7")
               onNumeroPulsado("7")
           }
           binding.boton8 -> {
               Log.d(TAG,"Botón 8")
               onNumeroPulsado("8")
           }
           binding.boton9 -> {
               Log.d(TAG,"Botón 9")
               onNumeroPulsado("9")
           }
           binding.boton0 -> {
               Log.d(TAG,"Botón 0")
               onNumeroPulsado("0")
           }

           binding.botonComa -> {
               Log.d(TAG, "Botón ,")
               onNumeroPulsado(",")
           }
           ////////////////////////////////////////////////////////////////////////////////////////
           /////////////////////////// ZONA DE OPERADORES /////////////////////////////////
           ////////////////////////////////////////////////////////////////////////////////////////


           binding.botonMas -> {
               Log.d(TAG,"Botón +")
               onOperadorPulsado("+")
           }
           binding.botonMenos -> {
               Log.d(TAG,"Botón -")
               onOperadorPulsado("-")
           }
           binding.botonPor -> {
               Log.d(TAG,"Botón *")
               onOperadorPulsado("*")
           }
           binding.botonDividir -> {
               Log.d(TAG,"Botón /")
               onOperadorPulsado("/")
           }
           binding.botonLimpiar -> {
               Log.d(TAG,"Botón CE")
               onCEPulsado()
           }
           binding.botonIgual-> {
               Log.d(TAG,"Botón =")
               onIgualPulsado()
           }



       }
    }


    /**
     * Envia a dibujar pantalla la información del numero pulsado
     * @param numero el numero que hemos pulsado
     */
    private fun onNumeroPulsado(numero: String){
        dibujarPantalla(numero)
        mirarOperacion()
    }

    /**
     * Refresca el campo de pantalla del XML con la nueva información,
     * si le paso un numero lo pondra en pantalla
     * si le paso otra cosa refrescara con el mismo valor que tenia antes mas una concatenacion
     */

    private fun dibujarPantalla(numero: String){
        //Unit es a kotlin como void es a java
        val result: String = if (binding.pantallaCalculadora.text == "0" && numero !=",")
            numero
         else
             //concatenación de lo anterior y el nuevo numero pasado
            "${binding.pantallaCalculadora.text}$numero"

        //ahora ponemos el valor
        binding.pantallaCalculadora.text = result
    }


    /**
     * Trata los diferentes operadores de la calculadora, si se ha pulado la operacion lo nuevo
     * que entra va al segundo operador, de lo contrario al primero
     */
    private fun mirarOperacion(){

        //si el resultado de la operacióno existe que devuelva el primer operador
        // de lo contrario el segundo
        if (operacion == null)
            primerNumero = binding.pantallaCalculadora.text.toString().toDouble()
        else
            segundoNumero = binding.pantallaCalculadora.text.toString().toDouble()
    }


    /**
     * Trata los diferentes operadores de la calculadora, al haber pulsado un operador debe
     * poner a cero la pantalla para que el usuario pueda meter nuevos numeros
     */
    private fun onOperadorPulsado(operacion :String){
        // recupera la operación a realizar
        this.operacion = operacion
        // lee lo que pone en la pantalla y metelo en primer operador
        primerNumero = binding.pantallaCalculadora.text.toString().toDouble()
        // limpia la pantalla esperando al siguiente operador
        binding.pantallaCalculadora.text = "0"
    }


    /**
     * Da tratamiento al botón igual
     */
    private fun onIgualPulsado(){
        val result= when (operacion){
            "+"-> primerNumero + segundoNumero
            "-"-> primerNumero - segundoNumero
            "*"-> primerNumero * segundoNumero
            "/"-> primerNumero / segundoNumero

            else -> 0
        }

        //limpia la operación
        operacion = null
        primerNumero  = result.toDouble()

        //formatea el resultado para que solo tenga dos comas
        binding.pantallaCalculadora.text = if (result.toString().endsWith(".0")){
            result.toString().replace(".0","")
        } else {
            "%.2f".format(result)
        }

    }


    /**
     * Limpia la pantalla y borra las variables de operador1, 2 y operacion
     */

    private fun onCEPulsado(){

    }

}