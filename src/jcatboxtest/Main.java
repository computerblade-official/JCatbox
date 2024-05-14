package jcatboxtest;

import java.io.IOException;

import com.puterblade.jcatbox.*;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("hello");
		JCatbox jc = new JCatbox();
		
		System.out.println(jc.uploadFileToAccount("4d96d1458a97fb2ed682cd5a2", "C:\\Users\\Ayush\\Pictures\\Screenshots\\Please.png"));
	}
}
