/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.tests;

import edu.pointcollect.services.AddressPointCollectCrud;
import edu.pointcollect.services.CrudAddressController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;


public class PointCollect extends Application {


    public void start(final Stage stage) throws Exception {
        
       
      final Parent browser = (Parent)FXMLLoader.load(this.getClass().getResource("/edu/pointcollect/gui/CrudCategorie.fxml"));
        
        
 final AddressPointCollectCrud aa = new AddressPointCollectCrud();
  try {
           aa.list();
       } catch (IOException ex) {
           Logger.getLogger(CrudAddressController.class.getName()).log(Level.SEVERE, null, ex);
       }
 /*   
WebView browser = new WebView();
 
// Get WebEngine via WebView
WebEngine webEngine = browser.getEngine();
 
// Load page

String url = PointCollect.class.getResource("/edu/pointcollect/gui/Maps.html").toExternalForm();



webEngine.setJavaScriptEnabled(true);
webEngine.load(url);

 
// Load page
//webEngine.load("html.html");
     
webEngine.getLoadWorker().stateProperty().addListener(
new ChangeListener<Worker.State>() {
    @Override
    public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
System.out.println("oldValue: " + oldValue);
            System.out.println("newValue: " + newValue);

            if (newValue != Worker.State.SUCCEEDED) {
                return;
            }
            System.out.println("Succeeded!");
             JSObject window = (JSObject) webEngine.executeScript("window");
            System.out.println("hello: " + window);
    }
}
);
*/
        // create scene
        stage.setTitle("Web Map");
        Scene scene = new Scene(browser,1000,600, Color.web("#666970"));
        scene.getStylesheets().add(getClass().getResource("/edu/pointcollect/gui/fxml.css").toExternalForm());
        stage.setScene(scene);
        // show stage
        stage.show();
    }
 
    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }
 
    public static void main(String[] args){
        Application.launch(args);
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PointCollect extends Application {
    
  public void start(final Stage primaryStage) throws Exception {
        final Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/edu/pointcollect/gui/CrudAddress.fxml"));
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(final String[] args) {
        launch(args);
    }
    
}*/