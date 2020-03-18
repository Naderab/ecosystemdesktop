/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.tools;

import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.jfoenix.controls.JFXButton;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.services.EventCRUD;
import static java.lang.Thread.sleep;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Nader
 */
public class Calendar {

    ObservableList<Evenement> observableList = FXCollections.observableArrayList();
    
    public CalendarView displayCalendar() {
        EventCRUD eventcrud = new EventCRUD();
        observableList = eventcrud.displayEventPublie();
        Stage ps = null;
        
        com.calendarfx.model.Calendar birthdays = new com.calendarfx.model.Calendar("User");
        
        for (Evenement e : observableList) {
            
            Entry entry = new Entry();
            
            entry.setTitle(e.getNom());
            
            entry.setLocation(e.getAdresse());
            
            java.time.LocalDate start = e.getDateDebut().toLocalDate();
            
            java.time.LocalDate end = e.getDateFin().toLocalDate();

            //   LocalTime t1 =  LocalTime.of(time.getHour()+1,00);
            entry.setInterval(start, end);//erval(day, time, day, t1);

            System.out.println("Date : " + start + " Time : " + end);
            
            birthdays.addEntry(entry);
            
            birthdays.setStyle(com.calendarfx.model.Calendar.Style.STYLE1);
            
        }
        
        CalendarView calendarView = new CalendarView();
        //    EventHandler<CalendarEvent>Hendler =

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(birthdays);
        
        calendarView.getCalendarSources().addAll(myCalendarSource);
        
        calendarView.setRequestedTime(LocalTime.now());
        
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });
                    
                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                }
            }
        ;
        };

         updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        
        calendarView.setMaxWidth(1740);
        calendarView.setNodeOrientation(NodeOrientation.INHERIT);
        
        StackPane sp = new StackPane();
        JFXButton button = new JFXButton("Annuler Rendez-Vous");
        button.setStyle("-fx-background-color: Red");
        VBox vb = new VBox();

        /* EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                   @Override
                             public void handle(MouseEvent e) {
                       try {
                           System.out.println("Hello World");

                           System.out.println("id Evenement : " + id);

                           Object nelement= (Node)FXMLLoader.load(getClass().getResource("/Interfaces/UIPlanning/ModifierRdvUserScene.fxml"));

                           Node element = (Node)nelement;










                          sp.getChildren().clear();

                          sp.getChildren().add(element);

                       } catch (IOException ex) {
                           Logger.getLogger(EvenementManager.class.getName()).log(Level.SEVERE, null, ex);
                       }
                             }
                          };
             button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);*/
 /* .getChildren().add(calendarView);

         sp.getChildren().add(vb);
         sp.setAlignment(Pos.TOP_LEFT);*/
        return (calendarView);
    }
}
