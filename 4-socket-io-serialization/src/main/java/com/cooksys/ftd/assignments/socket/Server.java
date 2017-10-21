package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) {
    		
    	try {
    		
    		//JAXBContext jbc = JAXBContext.newInstance(Student.class);
			Unmarshaller um = jaxb.createUnmarshaller();
			Student c = (Student) um.unmarshal(new File(studentFilePath));
			//Student c = (Student) um.unmarshal(new File("config/student.xml"));
			return c;
			
		} catch (JAXBException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        return null; // TODO
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     */
    public static void main(String[] args) {
    	
    	
    	System.out.println("Waiting for connection...");
    	
       		    	
    	 try {
  
    		 Config cf = Utils.loadConfig("config/config.xml", Utils.createJAXBContext());
    		 ServerSocket ss = new ServerSocket(cf.getLocal().getPort());
    		 Socket clientSocket = ss.accept();
    		 
    		 System.out.println("Connected");
    		 
    		 DataOutputStream outro = new DataOutputStream(clientSocket.getOutputStream());
    		 JAXBContext student = JAXBContext.newInstance(Student.class);
    		 
    		 Student jjk = loadStudent("config/student.xml", student);
    		 
    		 student.createMarshaller().marshal(jjk, outro);
    		 
 	         outro.close();
 	         ss.close();
 	        
 	         
    	 }catch (Exception je){
    		 je.printStackTrace();
    		 
    	 }
         
       
    }
}
