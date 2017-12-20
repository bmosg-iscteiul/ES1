package antiSpamFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Compiler_Main {

	public static void main(String[] args) {

		Process process;
		try {

			// Compile R file
			process = new ProcessBuilder(
					"C:\\Program Files\\R\\R-3.4.3\\bin\\RScript.exe","HV.Boxplot.R").directory(new File("experimentBaseDirectory\\AntiSpamStudy\\R")).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			System.out.printf("Output of running %s is:", Arrays.toString(args));


			while ((line = br.readLine()) != null) {
				System.out.println(line);

			}

			//Compile latex File
			process = new ProcessBuilder(
					"C:\\Program Files\\MiKTeX 2.9\\miktex\\bin\\x64\\pdflatex.exe","AntiSpamStudy.tex").directory(new File("experimentBaseDirectory\\AntiSpamStudy\\latex\\")).start();
			is = process.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			System.out.printf("Output of running %s is:", Arrays.toString(args));

			while ((line = br.readLine()) != null) {
				System.out.println(line);

			} 

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
