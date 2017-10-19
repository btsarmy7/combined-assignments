
	
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {
	
	
		public static void main(String[] args) {

			String filename = "newFile.txt";
			String workingDirectory = System.getProperty("user.dir");
			BufferedWriter bw = null;
			FileWriter fw = null;
			String FileName = workingDirectory + File.separator + filename;

			try {

				String content = "This is the content to write into file\n";

				fw = new FileWriter(FileName);
				bw = new BufferedWriter(fw);
				bw.write(content);

				System.out.println("Done");

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}

		}

}


