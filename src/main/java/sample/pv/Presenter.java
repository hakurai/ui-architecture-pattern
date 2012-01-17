package sample.pv;

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


    public void heightChanged( DocumentEvent e ){
        String text = getTextWithDocumentEvent( e );
        heightChanged( text );


    }

    public void heightChanged( String newHeight ){
        notifyEvent = true;
        try{
            model.setHeight( newHeight );

            updateView();
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
            updateView();
        } finally{
            notifyEvent = false;
        }
    }

    private void updateView(){

        if( !notifyEvent ){
            view.setHeight( model.getHeight() );
            view.setWeight( model.getWeight() );
        }
        view.setBmi( model.getBmi() );

        float bmi;
        try{
            bmi = Float.parseFloat( model.getBmi() );
        } catch ( NumberFormatException ignored ){
            return;
        }

        Color bmiColor;
        if( bmi < 18.5f ){
            bmiColor = Color.WHITE;
        } else if( bmi < 20.0f ){
            bmiColor = Color.YELLOW;
        } else if( bmi < 30 ){
            bmiColor = Color.ORANGE;
        } else{
            bmiColor = Color.RED;
        }

        view.setBmiBackground( bmiColor );
    }

    private String getTextWithDocumentEvent( DocumentEvent e ){
        try{
            return e.getDocument().getText( 0, e.getDocument().getLength() );
        } catch ( BadLocationException ignored ){
            return "";
        }
    }

}
