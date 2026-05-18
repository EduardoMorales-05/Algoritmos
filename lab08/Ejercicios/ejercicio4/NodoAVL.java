public class NodoAVL
{
    int dato;
    int altura;

    NodoAVL izq;
    NodoAVL der;

    public NodoAVL(int dato)
    {
        this.dato = dato;
        this.altura = 1;

        izq = null;
        der = null;
    }
}
