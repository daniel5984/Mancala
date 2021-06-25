/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import mancala.MudarLayout;
import mancala.Buraco;
import mancala.FxDemo;
import mancala.Tabuleiro;

/**
 * FXML Controller class
 *
 * @author DanielSilva
 */
public class TabuleiroController implements Initializable {

    @FXML
    private ImageView buraco_6;
    @FXML
    private ImageView buraco_5;
    @FXML
    private ImageView buraco_7;
    @FXML
    private ImageView buraco_4;
    @FXML
    private ImageView buraco_8;
    @FXML
    private ImageView buraco_3;
    @FXML
    private ImageView buraco_9;
    @FXML
    private ImageView buraco_2;
    @FXML
    private ImageView buraco_10;
    @FXML
    private ImageView buraco_1;
    @FXML
    private ImageView buraco_11;
    @FXML
    private ImageView buraco_0;
    @FXML
    private ImageView buraco_12;
    @FXML
    private ImageView buraco_13;
    @FXML
    private ImageView voltar_butao;
    
    private HashMap<ImageView, Buraco> imagemBuracoAss;
    @FXML
    private ImageView avatar1;
    @FXML
    private ImageView avatar2;
    @FXML
    private Label estado;
    private Tabuleiro tabuleiro;
    @FXML
    private StackPane sementesInicio;
    @FXML
    private StackPane principalView;
    @FXML
    private StackPane boardpane;
    @FXML
    private Rectangle rectangulo;
    @FXML
    private Button btn_iniciar;
    
    private ImageView[] imagensBuraco;
    @FXML
    private HBox tabuleiro_layout;

 
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {//960
         centrarJanela(FxDemo.getMainStage(), 800, 540);
         
       this.imagensBuraco = new ImageView[]{buraco_0, buraco_1, buraco_2, buraco_3, buraco_4, buraco_5, buraco_6, buraco_7, buraco_8, buraco_9, buraco_10, buraco_11, buraco_12, buraco_13};
        
        //System.out.println(imagensBuraco[0].localToScene(imagensBuraco[0].getX(),imagensBuraco[0].getLayoutY()));
        // imagensBuraco[0].localToScreen(imagensBuraco[0].getBoundsInLocal());
       // System.out.println(imagensBuraco[1].localToParent(imagensBuraco[1].localToParent(imagensBuraco[1].localToParent(imagensBuraco[1].getBoundsInLocal()))));
       // System.out.println(imagensBuraco[0].getBoundsInParent().getMaxY());
       
        imagemBuracoAss = new HashMap<>(); //Associar cada imagem do buraco a um objeto da class Buraco
        tabuleiro = new Tabuleiro(imagensBuraco, estado, avatar1, avatar2);
        
	tabuleiro.populateMarbles(sementesInicio);//Popular as sementes no stack pane sementesInicio para depois animar para 4 sementes para cada buraco
        
        for (int i = 0; i < imagensBuraco.length; i++){
            imagemBuracoAss.put(imagensBuraco[i], tabuleiro.getSlot(i));
        }
        //sementesInicio.addEventFilter(MouseEvent.ANY, e -> System.out.println("X: "+ e.getX()+" Y:"+e.getY()));

     

      
     
      
   
      //Bounds boundsInScreen = selectedSlot.getImageView().localToScene(selectedSlot.getImageView().getBoundsInLocal());
     }
    
    
     @FXML
    private void iniciar(MouseEvent event) {
        
        //btn_iniciar.disableProperty();
        //rectangulo.disableProperty();
        principalView.getChildren().remove(btn_iniciar);
        principalView.getChildren().remove(rectangulo);
       
        Bounds boundsInScreen = this.imagensBuraco[0].localToScene(this.imagensBuraco[0].getBoundsInLocal());
        double x =boundsInScreen.getMinX()+boundsInScreen.getWidth()/2;
        double y =boundsInScreen.getMinY()+boundsInScreen.getHeight()/2;
        //System.out.println(imagensBuraco[0].getId()+"ImagensBuraco -> X:"+x+"  Y"+y);
        
        for(ImageView img :imagensBuraco){
            Bounds b =   sementesInicio.sceneToLocal(img.localToScene(img.getBoundsInLocal()));
         System.out.println(img.getId()+"Minx"+b.getMinX()+"Miny"+b.getMinY()); 
        }
        
         
         
        /*
          imagemBuracoAss.forEach((img,buraco)->{
             Buraco selectedSlot =buraco;
          Bounds boundsInScreen = selectedSlot.getImageView().localToScene(selectedSlot.getImageView().getBoundsInLocal());
         double x =boundsInScreen.getMinX()+boundsInScreen.getWidth()/2;
         double y =boundsInScreen.getMinY()+boundsInScreen.getHeight()/2;
         System.out.println(selectedSlot.getImageView().getId()+"  X:"+x+"  Y"+y);
         });  
          
         */ 
          
          /*
         Bounds boundsInScreen = selectedSlot.getImageView().localToScene(selectedSlot.getImageView().getBoundsInLocal());
         double x =boundsInScreen.getMinX()+boundsInScreen.getWidth()/2;
         double y =boundsInScreen.getMinY()+boundsInScreen.getHeight()/2;
         System.out.println(selectedSlot.getImageView().getId()+"  X:"+x+"  Y"+y);
      */
        
    }
 

    @FXML
    private void voltar(MouseEvent event) {
        new MudarLayout("FxMenu").load();
    }

    @FXML
    private void rato_saiu(MouseEvent e) {
       Buraco selectedSlot = imagemBuracoAss.get((ImageView) e.getSource());
		mudaOpacidade(selectedSlot.getImageView(), 0.3f);
        
             //  System.out.println("Saiu");
    }

    @FXML
    private void rato_entrou(MouseEvent e) {
        Buraco selectedSlot = imagemBuracoAss.get((ImageView) e.getSource());
        mudaOpacidade(selectedSlot.getImageView(), 1.0f);
       // System.out.println("Entrou");
        Bounds boundsInScreen = selectedSlot.getImageView().localToScene(selectedSlot.getImageView().getBoundsInLocal());
        
        double x =boundsInScreen.getMinX()+boundsInScreen.getWidth()/2;
        double y =boundsInScreen.getMinY()+boundsInScreen.getHeight()/2;
        System.out.println(selectedSlot.getImageView().getId()+"  X:"+x+"  Y"+y);
        
            //maxx - width/2 para obter  cordenadas x no meio do node
            //maxy - height/2 para obter coordenada y no meio do node

         
    }

    
    /**
    * Esta class atribui transisção de opacidade de cada buraco quando o rato entra
    * @param node
    * @param scale 
    */
       private void mudaOpacidade(ImageView node, float scale) {
		FadeTransition obj = new FadeTransition(Duration.millis(100), node);
		obj.setToValue(scale);
		obj.setCycleCount(1);
		obj.play();
	}

   
       
       
    private void centrarJanela(Stage stage, double width, double height) {
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((screenBounds.getWidth() - width) / 2);
		stage.setY((screenBounds.getHeight() - height) / 2);
	}

  
}


