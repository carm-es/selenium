/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.carm.sistemas.selenium.bicicarm;

import es.carm.sistemas.TestSeleniumSSI;

public class Lanzador {

    public static void main(String[] args) {
 
        Test test = null;
        TestSeleniumSSI resultado = null;
        

        //Se crea un objeto de la Clase  TestSeleniumSSI, se le pasan los parametros de llamada del test, y el nombre del mismo        
        try {
           resultado = new TestSeleniumSSI(args, "BiciCarm");
            
        }  catch (Exception e) {
            System.out.println (e.getMessage());
            System.exit(0);
        }
        


//Se crea un objeto de la Clase que encapsula el TEST
        test = new Test(resultado);

//Se ejecuta el TEST
        resultado = test.test();

//Se saca por salida estandar el resultado del TEST
        System.out.println(resultado.getTResultado(false));

//Se retorna el TEST con el codigo de ejecución del mismo
        System.exit(resultado.getResultado());
    }
}
