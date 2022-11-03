package garcia.rubio.tablas


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import garcia.rubio.tablas.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var resultado=0
    var textoOperacion = ""

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        generarOperacion()

        binding.btnVerificar.setOnClickListener { view : View ->
            if(binding.etRespuesta.text.isNotEmpty()){
                verificarRespuesta(view)
                //verificarRespuesta()
            }else{
                m("Escribe una respuesta")
            }
        }
    }

    fun verificarRespuesta(view: View){
        val messageResId = if(binding.etRespuesta.text.toString().toInt() == resultado){
            R.string.msgCorrect
        }else{
            R.string.msgIncorrect
        }

        val colorBackground = if(binding.etRespuesta.text.toString().toInt() == resultado){
            R.color.verde
        }else{
            R.color.rojo
        }
        val mySnack = Snackbar.make(view,messageResId,Snackbar.LENGTH_LONG)
        mySnack.setBackgroundTint(getColor(colorBackground))
        mySnack.show()

        generarOperacion()
    }

    fun m(mensaje:String){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show()
    }


    fun generarOperacion(){
        binding.etRespuesta.text.clear()

        val listaOperaciones = listOf("+",'-','*')

        val num1 = Random.nextInt(0,11)//generar números entre 0-10
        val num2 = Random.nextInt(0,11)//
        //asignar el texto de la operación a mi
        //textView (se llame como se llame)

        val operacionARealizar = listaOperaciones[Random.nextInt(0,3)]

        when(operacionARealizar){
            "+"->{
                resultado = num1+num2
                textoOperacion = "$num1 + $num2 = ?"
            }
            "-"->{
                resultado = num1-num2
                textoOperacion = "$num1 - $num2 = ?"
            }
            "*"->{
                resultado = num1*num2
                textoOperacion = "$num1 x $num2 = ?"
            }
        }

        binding.tvOperacion.text = textoOperacion
    }
}