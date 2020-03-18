/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.entities;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Khouloud
 */
public class Validation {
    public static boolean textFieldIsNull(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;

       
        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
        if (inputTextField.getText().isEmpty()) {
            isNull = true;
            validationString = validationText;

        }
        String isEmpty = Boolean.toString(inputTextField.getText().isEmpty());
        String nil = Boolean.toString(inputTextField.getText() == null);

        inputLabel.setText(validationString);

    
        return isNull;

    }
    
    
}
