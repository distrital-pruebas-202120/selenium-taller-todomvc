package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.TodosPage;

/**
 * Ejemplo de prueba de interfaz usando 
 * usando los Page Objects para TODOMVC 
 */
public class EjemploTest {

    // En una aplicación real, es muy probable que el navegador
    // a utilizar y la ruta URL a probar se obtenga de un archivo
    // de configuración o usando un patrón de fábrica

    private WebDriver webDriver;
    private String ChromeDriverPath = "/mnt/c/ProgramData/chocolatey/lib/chromedriver/tools/chromedriver.exe";

    private String url = "https://todomvc.com/examples/vanilla-es6/";

    // antes de cada prueba
    @BeforeEach
    public void preparaFixture() {
        // usa el navegador Chrome
        System.setProperty("webdriver.chrome.driver", ChromeDriverPath);  
        webDriver = new ChromeDriver();
    }

    // NOTA: El código de la prueba NO debe tener código de Selenium

    @Test
    public void agregarUnaTarea() {
        // se conecta a la página de TODOMVC
        TodosPage todosPage = new TodosPage(webDriver, url);

        // agrega una tarea
        todosPage.agregarTarea("Ejemplo");
        
        // revisa si se agregó la tarea
        Assertions.assertNotNull(todosPage.buscarTarea("Ejemplo"));
    }

    // luego de cada prueba
    @AfterEach
    public void liberaRecursos() {
        // cierra el navegador
        // NOTA: puede colocar un comentario para dejar el navegador abierto
        webDriver.close();
    }

}
