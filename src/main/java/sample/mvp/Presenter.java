package sample.mvp;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.Color;

/**
 * @author eguchi
 */
public class Presenter{

    private boolean notifyEvent;
    private IView view;
    private Model model;

    public Presenter( IView view ){
        this.view = view;
    }


    public void setModel( Model model ){
        this.model = model;
    }

    public Model getModel(){
        return model;
    }

    public boolean isNotifyEvent(){
        return notifyEvent;
    }

    public void heightChanged( DocumentEvent e ){
        String text = getTextWithDocumentEvent( e );
        heightChanged( text );

    }

    public void heightChanged( String newHeight ){
        notifyEvent = true;
        try{
            model.setHeight( newHeight );
            updateColor();
        } finally{
            notifyEvent = false;
        }
    }

    public void weightChanged( DocumentEvent e ){
        String text = getTextWithDocumentEvent( e );
        weightChanged( text );

    }

    public void weightChanged( String newWeight ){
        notifyEvent = true;
        try{
            model.setWeight( newWeight );
            updateColor();
        } finally{
            notifyEvent = false;
        }
    }

    private void updateColor(){

        float bmi;
        try{
            bmi = Float.parseFloat( model.getBMI() );
        } catch ( NumberFormatException ignored ){
            return;
        }

        Color bmiColor;
        if( bmi < 18.5f ){
            bmiColor = Color.WHITE;
        } else if( bmi < 20.0f ){
            bmiColor = Color.YELLOW;
        } else if( bmi < 30.0f ){
            bmiColor = Color.ORANGE;
        } else{
            bmiColor = Color.RED;
        }

        view.setBMIBackground( bmiColor );
    }

    private String getTextWithDocumentEvent( DocumentEvent e ){
        try{
            return e.getDocument().getText( 0, e.getDocument().getLength() );
        } catch ( BadLocationException ignored ){
            return "";
        }
    }

}
