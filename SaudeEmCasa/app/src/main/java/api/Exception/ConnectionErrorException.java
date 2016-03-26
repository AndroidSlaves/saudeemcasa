package api.Exception;

/**
 * Created by lucas on 10/23/15.
 */
public class ConnectionErrorException extends Exception{
    public ConnectionErrorException(){
    /*Nothing to do here*/
    }
    public ConnectionErrorException(String message){
        super(message);
    }
}
