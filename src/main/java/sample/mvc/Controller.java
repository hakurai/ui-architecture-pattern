package sample.mvc;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

/**
 * @author eguchi
 */
public class Controller{

    private boolean notifyEvent;
    private Model model;

    public Controller(){
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
        String text = getTextFromDocumentEvent( e );
        heightChanged( text );
    }

    public void heightChanged( String newHeight ){
        notifyEvent = true;
        try{
            model.setHeight( newHeight );
        } finally{
            notifyEvent = false;
        }
    }

    public void weightChanged( DocumentEvent e ){
        String text = getTextFromDocumentEvent( e );
        weightChanged( text );
    }

    public void weightChanged( String newWeight ){
        notifyEvent = true;
        try{
            model.setWeight( newWeight );
        } finally{
            notifyEvent = false;
        }
    }

    private String getTextFromDocumentEvent( DocumentEvent e ){
        try{
            return e.getDocument().getText( 0, e.getDocument().getLength() );
        } catch ( BadLocationException ignored ){
            return "";
        }
    }

}
