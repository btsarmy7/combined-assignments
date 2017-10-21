package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     * @throws FileNotFoundException 
     */
    public static JAXBContext createJAXBContext() throws FileNotFoundException {
    	
    	
    	JAXBContext pkgContent;
    	
		try {
			pkgContent = JAXBContext.newInstance(Config.class, Student.class);
			return pkgContent;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        return null; // TODO
    }

    /**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     */
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) {
    	
    	
    	 try {
    		 //JAXBContext jbc = Utils.createJAXBContext();
    		 Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
    		 File f = new File(configFilePath);
    		// File f = new File("config/config.xml");
    		 Config c = (Config) jaxbUnmarshaller.unmarshal(f);
    		 
    		 return c;
    		 
    	 }catch (Exception je){
    		 je.printStackTrace();
    		 
    	 }
    	
  
    	
        return null; 
    }
    
}
