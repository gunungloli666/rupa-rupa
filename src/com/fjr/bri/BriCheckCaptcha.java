package com.fjr.bri;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BriCheckCaptcha extends Application {
	
	private int id; 
	
	private int number; 

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int  getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Class.forName("org.postgresql.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 

		PreparedStatement stmt = connection.prepareStatement("select id, md5, lokasi, keterangan from md5_bri "
				+ "where keterangan = 0 order by id asc ");
		ResultSet rs = stmt.executeQuery();
		
		Group root = new Group (); 
		Scene  sc = new Scene( root, 400, 500); 
		
		HBox box = new HBox(); 
		box.setSpacing(5);
		
		Button b1 = new Button("NEXT") ; 
	
		box.getChildren().add(b1); 
		
		
		TextField field = new TextField();
		field.setPrefWidth(100);
		field.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> obs, String oldText, String newText) {
				// TODO Auto-generated method stub
				if(newText.length() >4 ) {
					field.setText(oldText);
				}else {
					field.setText(newText);
				}
			}
		});
		
		box.getChildren().add(field); 
		
		Label l = new Label(); 
		l.setPrefWidth(30);
		box.getChildren().add(l);
		
		ImageView  imageView = new ImageView();
		
		box.getChildren().add(imageView);
		root.getChildren().add(box);
		primaryStage.setScene(sc);
		primaryStage.show();
		
		if( rs.next()) {
			String path  = rs.getString("lokasi"); 
			int id = rs.getInt ("id"); 
			setId(id);
			File ff = new File(path); 
			Image im = new Image(ff.toURI().toString()); 
			imageView.setImage(im);
			
			setNumber(0);
			l.setText(Integer.toString(getNumber()));
		}
		
		final PreparedStatement stmt1 = connection.prepareStatement
				("update  md5_bri set kode = ? , keterangan = 1 where id = ? ");

		
		b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				String kode = field.getText(); 
				int id = getId();
				try {
					stmt1.setString(1, kode); 
					stmt1.setInt(2, id);
					stmt1.executeUpdate();
					
					setNumber(getNumber() + 1);
				}catch (Exception e) {
					e.printStackTrace();
				}
				field.setText("");
				try {
					if( rs.next()) {
						String path  = rs.getString("lokasi"); 
						int idx = rs.getInt ("id"); 
						setId(idx);
						File ff = new File(path); 
						Image im = new Image(ff.toURI().toString()); 
						imageView.setImage(im);
						
						l.setText(Integer.toString(getNumber()));
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
			
	}
	
	public static void main(String[] args) {

		launch(args);
		
	}

}
