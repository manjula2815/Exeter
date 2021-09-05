package exeter;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPopupMenu.Separator;

public class ExMain {
	private final static String COMMA_DELIMITER = ",";
	private final static String inputFile = "D:\\Projects\\exeter\\t8.shakespeare.txt";
	private final static String outputFile = "D:\\Projects\\Manjula\\exeter\\t8.shakespeare.translated.txt";
	private final static String frenchDict = "D:\\Projects\\exeter\\french_dictionary.csv";
	private final static String frequencyFile = "D:\\Projects\\Manjula\\exeter\\frequency.csv";

	public static void main(String[] args) throws IOException {

		Map frenchdict = readWords(frenchDict);
		ExMain ex = new ExMain();
		ex.convertPara(frenchdict);

	}

	private static Map readWords(String filename) throws IOException {
		Map dict = new HashMap();

		Path pathtoFile = Paths.get(filename);

		try (BufferedReader br = Files.newBufferedReader(pathtoFile, StandardCharsets.UTF_8)) {
			String line = br.readLine();

			while (line != null) {

				String[] attributes = line.split(COMMA_DELIMITER);
				dict.put(attributes[0], attributes[1]);

				line = br.readLine();

			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		return dict;

	}

	public void convertPara(Map dict) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		Map<String, Integer> freq = new HashMap<String, Integer>();
		List<Words> c = new ArrayList<Words>();
		String line = br.readLine();
		while (line != null) {
			String[] splited = line.split(" ");
			String Modified = "";

			for (String s : splited) {

				if (dict.containsKey(s)) {

					String french = (String) dict.get(s);

					Modified += french + " ";
					if (freq.containsKey(s)) {
						int value = (int) freq.get(s) + 1;
						freq.put(s, value);

					} else {
						freq.put(s, 1);
					}

				} else {
					Modified += s + " ";
				}
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true));

			bw.write(Modified + "\n");
			bw.close();
			line = br.readLine();
		}
		BufferedWriter fw = new BufferedWriter(new FileWriter(frequencyFile));
		char Separator = ',';
		fw.append("English Word").append(Separator).append("French Word").append(Separator).append("Frequency")
				.append(System.lineSeparator());

		for (String s : freq.keySet()) {
			fw.append(s).append(Separator).append((String) dict.get(s)).append(Separator)
					.append((String.valueOf(freq.get(s)))).append(System.lineSeparator());
			System.out.println(s + "," + (String) dict.get(s) + "," + (int) freq.get(s));
//			fw.append(s + "," + dict.get(s) + "," + freq.get(s) + "\n");

		}

		fw.close();

	}

}
