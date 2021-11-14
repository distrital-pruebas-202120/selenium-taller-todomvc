package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Fila en pantalla con una tarea del TODOMVC
 */
public class FilaTarea {

    private WebDriver driver;
    private WebElement rowElement;

    public FilaTarea(WebElement rowElement,WebDriver driver)
    {
        this.driver = driver;
        this.rowElement = rowElement;
    }

    private WebElement getCheckboxCompletado() {
        // retorna el <input> con type=checkbox
        return rowElement.findElement(By.cssSelector("input[type='checkbox']"));
    }
    
    public String getTextoTarea() {
        return rowElement.getText();
    }

    public boolean isCompletado() {
        // revisa si la fila tiene class="completed"
        return rowElement.getAttribute("class").contains("completed");
    }
    
    public void editarTarea(String nuevoNombre)
    {
        // busca el <label>
        WebElement editable = rowElement.findElement(By.cssSelector("label"));
        Actions actions = new Actions(driver);
        actions.moveToElement(editable)
            .doubleClick()
            .build()
            .perform();
            
        // busca el <input> con class="edit"
        WebElement input = rowElement.findElement(By.cssSelector("input.edit"));
        // eliminar el texto actual
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        // escribe el nuevo texto
        input.sendKeys(nuevoNombre + Keys.ENTER);
    }

    public void borrarTarea()
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(rowElement)
            .build()
            .perform();

        // localiza el <button> con class="destroy"
        rowElement.findElement(By.cssSelector("button.destroy"))
            .click();
    }

    public void marcarComoCompletado()
    {
        getCheckboxCompletado().click();
    }

    public void marcarComoActivo()
    {
        getCheckboxCompletado().click();
    }
    
}