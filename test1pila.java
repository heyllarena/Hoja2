import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * Clase para realizar las pruebas unitarias
 * @author Pedro Marroquín 22543
 * @since 01/02/2023
 * @version 1.0
 */
public class PilaTest {
    Pila pila = new Pila();
    @Test
    /**
     * Se prueba que se agregue un item correctamente al vector
     */
    public void TestAddPeek()
    {
        pila.add(4.0);
        Double obtenido = pila.peek();
        Double esperado = 4.0;
        assertEquals(esperado, obtenido);
    }
    @Test
    /**
     * Se prueba que se agregue un item,
     * se verifica el tamaño,
     * luego se remueve el valor, verificando que sea el mismo
     * y que el tamaño se haya reducido por 1
     */
    public void TestAddRemove()
    {
        pila.add(3.0);

        int esperadoSize = 1;
        int obtenidoSize = pila.size();
        assertEquals(esperadoSize, obtenidoSize);

        Double obtenido = pila.remove();
        Double esperado = 3.0;
        assertEquals(esperado, obtenido);

        int esperadoSize1 = 0;
        int obtenidoSize1 = pila.size();
        assertEquals(esperadoSize1, obtenidoSize1);
    }

    @Test
    /**
     * Revisa que el vector esté vacío
     */
    public void TestEmpty()
    {
        boolean esperado = true;
        boolean obtenido = pila.empty();
        assertEquals(esperado, obtenido);

        pila.add(1.0);
        boolean esperado1 = false;
        boolean obtenido1 =pila.empty();
        assertEquals(esperado1, obtenido1);
    }

    @Test
    /**
     * verifica que devuelva el tamaño correcto del vector
     */
    public void TestSize()
    {
        pila.add(1.0);
        pila.add(2.0);
        pila.add(3.0);

        int esperado = 3;
        int obtenido = pila.size();

        assertEquals(esperado, obtenido);
    }

    @Test
    /**
     * Evalúa que se calculen correctamente las operaciones PostFix
     */
    public void TestCalculate() throws Exception
    {
        Double esperado = 15.0;
        Double obtenido = pila.calculate("12+4*3+");
        assertEquals(esperado, obtenido);
    }

    @Test
    /**
     * Comprueba que identifique los 4 operadores como operadores y que no identifique un número como operador
     */
    public void TestIsOperator()
    {
        boolean esperado = true;
        boolean obtenido;
        String[] operators = {"+","-","*","/"};
        for(int i = 0; i<operators.length; i++){
            obtenido = pila.isOperator(operators[i]);
            assertEquals(esperado, obtenido);
        }
        boolean esperado1 = false;
        boolean obtenido1 = pila.isOperator("4");
        assertEquals(esperado1, obtenido1);
    }

    @Test
    /**
     * verifica que se identifique correctamente los números y no cualquier otro caracter
     */
    public void TestNumber()
    {
        boolean esperado = true;
        boolean obtenido;
        String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
        for(int i = 0; i<numbers.length; i++){
            obtenido = pila.isNumber(numbers[i]);
            assertEquals(esperado, obtenido);
        }
        boolean esperado1 = false;
        boolean obtenido1 = pila.isNumber("+");
        assertEquals(esperado1, obtenido1);
    }

    @Test
    /**
     * verifica que realice las 4 operaciones básicas correctamente
     */
    public void TestOperate()
    {
        Double esperadoSuma = 9.0;
        Double esperadoResta = 3.0;
        Double esperadoMultiplicacion = 18.0;
        Double esperadoDivision = 2.0;

        String[] operators = {"+","-","*","/"};
        Double[] resultados = new Double[4];
        for(int i = 0; i<operators.length; i++)
        {
            pila.add(6.0);
            pila.add(3.0);
            pila.operate(operators[i]);
            Double obtenido = pila.remove();
            
            resultados[i] = obtenido;
        }
        assertEquals(esperadoSuma, resultados[0]);
        assertEquals(esperadoResta, resultados[1]);
        assertEquals(esperadoMultiplicacion, resultados[2]);
        assertEquals(esperadoDivision, resultados[3]);
    }
}