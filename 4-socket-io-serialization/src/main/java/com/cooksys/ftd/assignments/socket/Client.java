package com.cooksys.ftd.assignments.socket;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     * @throws IOException 
     * @throws JAXBException 
     */
    public static void main(String[] args) throws IOException, JAXBException {
    	
    	Config cf = Utils.loadConfig("config/config.xml", Utils.createJAXBContext());
        
    	//Config cf = Utils.loadConfig("config/config.xml", Utils.createJAXBContext());
    	RemoteConfig rc = cf.getRemote();
    	
        Socket info = new Socket(rc.getHost(), rc.getPort());
        DataInputStream intro = new DataInputStream(info.getInputStream());
        
        Student kth = (Student) JAXBContext.newInstance(Student.class).createUnmarshaller().unmarshal(intro);
        
        System.out.println(kth.toString());

    	info.close();
        
    	JAXBContext context = JAXBContext.newInstance(Config.class);
    	JAXBContext studentContext = JAXBContext.newInstance(Student.class);
    	
    		
   	
    	String LH = "localhost";
    	int Port = 9597;
    	
    	Marshaller M = context.createMarshaller();
    	
    	Config C = new Config();
    	LocalConfig LC = new LocalConfig();
    	RemoteConfig RC = new RemoteConfig();
    	        	
    	RC.setPort(Port);
    	RC.setHost(LH);
    	RC.setPort(Port);
    	    	
    	C.setLocal(LC);
    	C.setRemote(RC);
    	C.setStudentFilePath("config/student.xml");
    	
    	FileOutputStream BH = new FileOutputStream(new File("config/config.xml"));
    	
   	
    	M.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    	
    	M.marshal(C, BH);
    	
    	
    	//Create student
    	Student jjk = new Student();
		jjk.setFirstName("JK");
		jjk.setLastName("J");
		jjk.setFavoriteLanguage("English");
		jjk.setFavoriteParadigm("HYYH");    
    	
    	FileOutputStream fos = new FileOutputStream(new File("config/student.xml"));
    	
    	studentContext.createMarshaller().marshal(jjk,fos);
    	
    	/*Config cf = Utils.loadConfig("config/config.xml", Utils.createJAXBContext());
    	RemoteConfig rc = cf.getRemote();
    	
        Socket info = new Socket(rc.getHost(), rc.getPort());
        DataInputStream intro = new DataInputStream(info.getInputStream());
        
        Student kth = (Student) JAXBContext.newInstance(Student.class).createUnmarshaller().unmarshal(intro);
        
        System.out.println(kth.toString());

    	info.close();*/
        
    	
    	
    	
    }
}
