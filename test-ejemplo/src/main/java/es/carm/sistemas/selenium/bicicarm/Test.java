package es.carm.sistemas.selenium.bicicarm;

import es.carm.sistemas.TestSeleniumSSI;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author rafael
 */
public class Test {

    private String baseUrl;
    private WebDriver driver;
    TestSeleniumSSI resultado;
    WebDriverWait wait;
         
    public Test(TestSeleniumSSI resultado) {
        this.resultado = resultado;
    }

    public TestSeleniumSSI test() {

        long inicio;
        long fin;

        try {

            driver = resultado.getDriver();

            
            
            resultado.setOk("Test Correcto");

            this.baseUrl = "http://bici.carm.es";

            inicio = System.currentTimeMillis();
            driver.get(baseUrl);
            fin = System.currentTimeMillis();
            resultado.addMensaje("carga", "Carga pagina inicial", fin - inicio, false);

            if (Long.compare((fin - inicio), 8000) > 0) {
                resultado.setWarning("Tiempo de Carga demasiado alto");
            }

            driver.findElement(By.id("q")).clear();
            
            driver.findElement(By.id("q")).sendKeys("BICI Banco de datos de información al ciudadano");
            
            
            inicio = System.currentTimeMillis();
            driver.findElement(By.id("q_img")).click();
            fin = System.currentTimeMillis();
            resultado.addMensaje("busquedaCarm", "Busqueda BICI", fin - inicio, false);

            driver.findElement(By.linkText("BICI - Banco de datos de información al ciudadano")).click();


            driver.switchTo().frame("main");
            driver.findElement(By.cssSelector("font > b")).click();
            driver.findElement(By.name("O_DenCuerpo")).clear();
            driver.findElement(By.name("O_DenCuerpo")).sendKeys("ANALISTA DE APLICACIONES");

            inicio = System.currentTimeMillis();
            driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            fin = System.currentTimeMillis();
            resultado.addMensaje("busquedaBICI", "Busqueda Temario en BICI", fin - inicio, false);

            inicio = System.currentTimeMillis();
            driver.findElement(By.linkText("1.Orden de 27 de abril de 2016. BORM de 17 de mayo de 2016. Programa de Materias Comunes. Cuerpo Técnico, Opción Analista de Aplicaciones.")).click();

  
            driver.switchTo().frame("main_set") ;


            //Se dan 10 segundos de espera para que pueda cargar el pdf
            try {  
              wait = new WebDriverWait(driver,10);
              wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Orden de 27 de abril de 2016')]")));
            } catch (Exception e) {
              throw new Exception("El documento PDF no carga o no es correcto");
            }

            fin = System.currentTimeMillis();
            resultado.addMensaje("abrirPdf", "Abrir PDF", fin - inicio, false);
            
            
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            resultado.addMensajeExcepcion("Ex: " + e.getMessage().substring(0, 30), e);
            resultado.setCritical("Excepcion en el Test");

        } finally {
	    try {
	            driver.quit();
            }catch(Exception x) {
            }
            return resultado;
        }
    }
    
}
