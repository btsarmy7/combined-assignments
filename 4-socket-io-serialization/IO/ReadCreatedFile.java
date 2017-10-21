import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

		
		
		/*ServerSocket socket = null;
		Socket sock = null;
		InputStream in = null;
		FileOutputStream st = null;
		File f = null;
		
		System.out.println("Waiting...");
		
		try {
			socket = new ServerSocket(7);
			sock = socket.accept();
			System.out.println("Accepted");
			
			f = new File("dfajsld.txt");
			st = new FileOutputStream(f);
			in = sock.getInputStream();
			for(int i = 0; i < 5; i++) {
				st.write(in.read());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			in.close();
			socket.close();
			sock.close();
			st.close();
		}*/
		
		
		}
}
