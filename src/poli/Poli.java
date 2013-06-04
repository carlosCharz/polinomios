package poli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author Charz++
 */
public class Poli {

    private double[] fc;
    private double[] gc;
    private double[] qc;
    private double[] rc;

    public void leerDatos() {
        String linea;
        String[] num;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            linea = br.readLine();
            num = linea.split(" ");
            fc = new double[num.length];
            fillArrayOfDouble(num, fc);

            linea = br.readLine();
            num = linea.split(" ");
            gc = new double[num.length];
            fillArrayOfDouble(num, gc);
        } catch (IOException ex) {
            System.out.println("Ocurrió una excepcion: " + ex);
        }
    }

    public void fillArrayOfDouble(String[] origen, double[] destino) {
        for (int i = 0; i < origen.length; i++) {
            destino[i] = Double.parseDouble(origen[i]);
        }
    }

    public double[] copyArray(double[] origen) {
        double[] destino = new double[origen.length];
        for (int i = 0; i < origen.length; i++) {
            destino[i] = origen[i];
        }
        return destino;
    }

    public void procesarDivision() { //division de polinomios
        int cont = 0;
        double aux1;
        int gradoR = grado(fc);
        qc = new double[gradoQ() + 1];
        rc = copyArray(fc);
        double[] aux2 = new double[rc.length];//-aux1*gc

        while (grado(gc) <= gradoR) {
            aux1 = rc[cont] / gc[0];
            qc[cont] = aux1;

            for (int i = 0; i < gc.length; i++) {
                aux2[i + cont] = -1 * aux1 * gc[i];
            }

            for (int i = 0; i < rc.length; i++) {
                rc[i] = rc[i] + aux2[i];
            }
            Arrays.fill(aux2, 0);
            cont++;
            gradoR--;
        }
    }

    private int grado(double[] poli) {
        return poli.length - 1;
    }

    private int gradoQ() {
        return grado(fc) - grado(gc);
    }

    private int gradoR(double[] arr) {
        return arr.length - cuentaCerosIzq(arr) - 1;
    }

    public void imprimirDatos() {
        System.out.print("Coeficientes Dividendo: ");
        for (int i = 0; i < fc.length; i++) {
            System.out.print(fc[i] + "  ");
        }
        System.out.println(" grado: " + grado(fc));

        System.out.print("Coeficientes Divisor: ");
        for (int i = 0; i < gc.length; i++) {
            System.out.print(gc[i] + "  ");
        }
        System.out.println(" grado: " + grado(gc));
    }

    private int cuentaCerosIzq(double[] arr) {
        int cont = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                cont++;
            } else {
                break;
            }
        }
        return cont;
    }

    public void imprimirResultado() {
        System.out.print("Coeficientes Cociente: ");
        for (int i = 0; i < gradoQ() + 1; i++) {
            System.out.print(redondea(qc[i]) + "  ");
        }
        System.out.println(" grado: " + gradoQ());
        System.out.print("Residuo: ");
        for (int i = 0; i < rc.length; i++) {
            System.out.print(redondea(rc[i]) + "  ");
        }
        System.out.println(" grado: " + gradoR(rc));
    }

    private double redondea(double i) {
        return (double)(Math.round(i*100))/100;
    }
}
