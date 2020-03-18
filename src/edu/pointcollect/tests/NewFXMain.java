/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Khouloud
 */
public class NewFXMain  {
    
    
        public static void main(String[] args)
        {
        
        try {

			ObjectMapper mapper = new ObjectMapper();

			// read JSON from a file
			Map<String, Object> map = mapper.readValue(
					new File("address.json"), 
					new TypeReference<Map<String, Object>>() {
			});

			System.out.println(map.get("addrees"));
			System.out.println(map.get("lat"));

			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) map.get("markers");

			for (String msg : list) {
				System.out.println(msg);
			}

		} catch (JsonGenerationException ee) {
			ee.printStackTrace();
		} catch (JsonMappingException ee) {
			ee.printStackTrace();
		} catch (IOException ee) {
			ee.printStackTrace();
		}
        
        }
      
      
 
}