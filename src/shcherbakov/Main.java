package shcherbakov;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(JUnit4.class)
public class Main {

	@Test
	public void main() throws AWTException {
		TestHelper.Browser().get("http://www.ex.ua/764567494067");
		
		TestHelper.findElement("//a[text()='load']").click();
		
		TestHelper.assertStringInFile("C:/Users/Oleksii/Downloads/file.txt", "<We are looking hasd for this string>");
		//StringSelection ss = new StringSelection("C:\\MyWork\\AUT5\\newRepo\\.gitignore");
        //Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		//Robot robot = new Robot();


		//robot.keyPress(KeyEvent.VK_ENTER);
        //robot.keyRelease(KeyEvent.VK_ENTER);
		
		
	}
	
	@After
	public void after() {
		TestHelper.Browser().quit();
	}
}
