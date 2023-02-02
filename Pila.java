
import java.util.Vector;
/**
 * Esta Pila almacena números y también realiza las operaciones
 * necesarias para calcular las entradas Postfix
 * @author Ximena Llarena 22543
 * @version 1.0
 * @since 1/02/2023
 */
public class Pila implements Calculator, Stack<Double>{
    // atributos o propiedades
    private Vector<Double> vector; //vector inicial de 10 posicion y aumenta en 5
    private double resultado; //resultado esperado para el usuario
    
    public Pila() {
        this.vector = new Vector<Double>(10,5);
        this.resultado = 0;
    }
     /**
     * agrega el item proporcionado dentro del Vector
     */
    @Override
    public void add(Double item) {
        vector.add(item);
    }
    /**
     * Devuelve el item más reciente del Vector y lo remueve del mismo
     * @return el número previamente almacenado
     */
    @Override
    public Double remove() {
        Double numero_retornado = vector.remove(size()-1);
        return numero_retornado;
    }
    /**
     * Devuelve el último item del Vector sin removerlo del mismo
     */
    @Override
    public Double peek() {
        return vector.get(size()-1);
    }
    /**
     * Verifica si está vacío o no el Vector de almacenaje
     * @return true, si está vacío el Vector
     */
    @Override
    public boolean empty() {
        boolean emptiness = false;
        if(size() == 0)
        {
            emptiness = true;
        }
        return emptiness;
    }
    /**
     * Función que obtiene el tamaño del Vector
     * @return un número entero de la cantidad de los miembros dentro del Vector
     */
    @Override
    public int size() {
        return vector.size();
    }

    /**
     * realiza el cálculo de una línea de Postfix
     * @param la línea completa de la operación de Postfix en formato String
     * @return un Double del resultado final
     * @throws Exception en caso no sea posible realizar la operación o se realice una inválida
     */
    @Override
    public Double calculate(String operation) throws Exception {
        for (int i = 0; i < operation.length(); i++) {
            if(isOperator(operation.substring(i, i+1))) {
                operate(operation.substring(i, i+1));
            } else if (isNumber(operation.substring(i,i+1))) {
                add(Double.parseDouble(operation.substring(i, i+1))); // agrega el elemento como un numero usando stack
            }            
        }
        resultado = remove();//ya que el resultado se guardó en el vector, debería ser su único item y por lo tanto lo elimina y devuelve como resultado final
        return resultado;
    }   
    
    /**
     * Valida si el char es un operador 
     * @param c
     * @return true, si es un operador
     */
    protected boolean isOperator(String c){
        return (c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/"));
    }

    /**
     * Valida si el char es un numero
     * @param c
     * @return true si es un número
     */
    protected boolean isNumber(String c) {
        return ("1".equals(c)||"2".equals(c)||"3".equals(c)||"4".equals(c)||"5".equals(c)||"6".equals(c)||"7".equals(c)||"8".equals(c)||"9".equals(c)||"0".equals(c));
    }

    /**
     * Realiza las operaciones basicas requeridas 
     * @param operador Indica la operacion 
     */
    protected void operate(String operador) {
        Double resultante = 0.0;
        Double b = remove();
        Double a = remove();
        switch(operador) {
            case "+":
                resultante = a + b;
                break;
            case "-":
                resultante = a-b;
                break;
            case "*":
                resultante = a*b;
                break;
            case "/":
                if(b!=0){//lidiar con divisiones por 0
                    resultante = a/b;
                }else{
                    resultante = 0.0;
                }
                break;
            default:
                System.out.println("Ocurrió un error y no se realizó la operación");
                break;
        }
        add(resultante); // agrega el resultado al stack de Vector
    }

}