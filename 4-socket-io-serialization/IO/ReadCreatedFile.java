import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadCreatedFile {
	

		public static void main(String[] args) {
			
			String filename = "newFile.txt";
			String workingDirectory = System.getProperty("user.dir");
			String FileName = workingDirectory + File.separator + filename;

			BufferedReader br = null;
			FileReader fr = null;

			try {

				//br = new BufferedReader(new FileReader(FILENAME));
				fr = new FileReader(FileName);
				br = new BufferedReader(fr);

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(sCurrentLine);
				}

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (br != null)
						br.close();

					if (fr != null)
						fr.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}

		}

}
