package steps;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchProductSteps {
	
	WebDriver controlador;
	//String matriz[][];
	String matriz[][];
	
	
	
	@Given("^Abrimos el navegador e ingresamos a EBAY$")
	public void abrimos_el_navegador_e_ingresamos_a_EBAY() throws Throwable {
	    System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
	    controlador = new ChromeDriver();
	    controlador.manage().window().maximize();
	    controlador.get("https://www.ebay.com/");
	}

	@When("^ingresamos los zapatos \"([^\"]*)\" de tamanio \"([^\"]*)\"$")
	public void ingresamos_los_zapatos_de_tamanio(String marca, int tamanio) throws Throwable {
	    controlador.findElement(By.id("gh-ac")).sendKeys("shoes");
	    controlador.findElement(By.id("gh-btn")).click();
	    Thread.sleep(2000);	
	    
	    controlador.findElement(By.xpath(".//div[@class='pnl-b pad-bottom']//*[text()='"+tamanio+"']")).click();
	    Thread.sleep(2000);	
	    controlador.findElement(By.xpath(".//*[@class='search']")).sendKeys(marca);
	    Thread.sleep(1000);
	    controlador.findElement(By.xpath(".//div[@class='pnl-b pad-bottom']//*[text()='"+marca+"']")).click();
	    Thread.sleep(2000);	
	}

	@Then("^imprimimos resultados$")
	public void imprimimos_resultados() throws Throwable {
	    System.out.println("Se obtuvieron "+controlador.findElement(By.xpath(".//h1[@class='rsHdr']")).getText());
	}
	
	@Then("^ordenar por precio ascendente$")
	public void ordenar_por_precio_ascendente() throws Throwable {

		//ordenamos de manera ascendente
		controlador.findElement(By.xpath(".//*[@id='DashSortByContainer']/ul[1]/li/div/a")).click();
		Thread.sleep(1000);	
		controlador.findElement(By.xpath(".//*[@id='SortMenu']/li[3]/a")).click();
				
	}
	
	@Then("^obtener \"([^\"]*)\" productos$")
	public void obtener_productos(int cantidad) throws Throwable {
		                                  
		
		String Producto ="";
		String Precio ="";
		
		matriz=new String[cantidad][2];
		
		for(int f=0;f<cantidad;f++) {
			int c=f+1;
			Producto=controlador.findElement(By.xpath(".//*[@id='GalleryViewInner']//li["+c+"]//div[@class='gvtitle']//h3")).getText();
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Producto: "+Producto);
			Precio=controlador.findElement(By.xpath(".//*[@id='GalleryViewInner']//li["+c+"]//div[@class='gvprices']//span[1]")).getText();
			System.out.println("Precio: "+Precio);
			System.out.println("----------------------------------------------------------------------");
			matriz[f][0]=Producto;			
			matriz[f][1]=Precio;            
        }		
	}

	@Then("^se debe cerrar el navegador$")
	public void se_debe_cerrar_el_navegador() throws Throwable {
	    controlador.quit();
	}
}
