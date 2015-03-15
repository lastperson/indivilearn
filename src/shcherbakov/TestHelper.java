package shcherbakov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestHelper {
	private static WebDriver browser = null;
	
	public static WebDriver Browser() {
		if (browser == null) {
			System.setProperty("webdriver.chrome.driver", "C:/MyWork/eclipse/shcherbakov/chromedriver.exe");
			browser = new ChromeDriver();
			browser.manage().window().maximize();
		}
		
		return browser;
	}
	
	public static WebElement findElement(String xpath) {
		int mseconds = 15000;
		int stepmSec = 100;
		int currentStep = 0;
		while (browser.findElements(By.xpath(xpath)).size() == 0 && currentStep < mseconds) {
			sleep(stepmSec);
			currentStep += stepmSec;
		}
		
		if (browser.findElements(By.xpath(xpath)).size() == 0) {
			Assert.fail("Can't find element by xPath: " + xpath + "\n in 15 seconds.");
		}
		
		return browser.findElement(By.xpath(xpath));
	}
	
	public static void sleep(long msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void assertStringInFile(String path, String searchString) {
		File file = new File(path);
		BufferedReader reader = null;
		FileReader fileReader = null;
		
		int mseconds = 5000;
		int stepmSec = 100;
		int currentStep = 0;
		while (currentStep < mseconds) {
			try {
				fileReader = new FileReader(file);
				break;
			} catch (FileNotFoundException e) {
				sleep(stepmSec);
				currentStep += stepmSec;
			}
		}
		
		if (fileReader == null) {
			Assert.fail("File " + path + " noot found in 5 seconds.");
		}
		
		reader = new BufferedReader(fileReader);
		String text = null;
		boolean passed = false;

		try {
		    while ((text = reader.readLine()) != null) {
		        if (text.trim().equals(searchString)) {
		        	passed = true;
		        	break;
		        }
		    }
		    
		    if (!passed) {
		    	Assert.fail("String '" + searchString + "' not found.");
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}
	}
}
