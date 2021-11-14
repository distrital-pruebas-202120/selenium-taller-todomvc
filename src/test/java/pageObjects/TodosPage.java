package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object para TODOMVC
 */
public class TodosPage {

    private WebDriver driver;

    public TodosPage(WebDriver driver, String url)
    {
        this.driver = driver;
        driver.get(url);
        esperarACargarLaPagina();
    }

    private WebElement getCampoNuevaTarea() {
        return driver.findElement(By.className("new-todo"));
    }

    public void agregarTarea(String nombreNuevaTarea)
    {
        getCampoNuevaTarea().click();
        getCampoNuevaTarea().sendKeys(nombreNuevaTarea);
        // getCampoNuevaTarea().sendKeys(Keys.TAB);
        getCampoNuevaTarea().sendKeys(Keys.ENTER);


        String text = getCampoNuevaTarea().getText();
        text = getCampoNuevaTarea().getAttribute("value");
        System.out.println(text);
    }

    public List<FilaTarea> getListaTareas() {

        List<FilaTarea> resultados = new ArrayList<>();
        List<WebElement> filas = driver.findElements(By.cssSelector(".todo-list li"));
        for(WebElement fila: filas) {
            resultados.add(new FilaTarea(fila, driver));
        }
        return resultados;
    }

    public FilaTarea buscarTarea(String textoTarea)
    {
        List<FilaTarea> tareas = getListaTareas();
        for (FilaTarea tarea : tareas) {
            if (tarea.getTextoTarea().equals(textoTarea))
                return tarea;
        }
        return null;
    }

    public void borrarTareasCompletadas()
    {
        // localiza el elemento con class="clear-completed" y hace clic en él
        driver.findElement(By.cssSelector(".clear-completed")).click();
    }

    public void seleccionarVerTodo()
    {
        // localiza, dentro de los <ul> con class="filters", el <a> con texto "All"
        driver.findElement(By.xpath("//ul[@class='filters']//a[text()='All']")).click();
    }

    public void seleccionarVerTareasActivas()
    {
        // localiza, dentro de los <ul> con class="filters", el <a> con texto "Active"
        driver.findElement(By.xpath("//ul[@class='filters']//a[text()='Active']")).click();
    }

    public void seleccionarVerTareasCompletadas()
    {
        // localiza, dentro de los <ul> con class="filters", el <a> con texto "Completed"
        driver.findElement(By.xpath("//ul[@class='filters']//a[text()='Completed']")).click();
    }

    private void esperarACargarLaPagina()
    {
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("new-todo"))
        );
    }
    
}