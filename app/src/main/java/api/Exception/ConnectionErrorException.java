/************************
 * Class name: ConnectionErrorException (.java)
 *
 * Purpose: Exception class that should occur when there is some problem with internet connection.
 ************************/

package api.Exception;

public class ConnectionErrorException extends Exception {

    // Creating the exception object default
    public ConnectionErrorException() {
        /* Nothing to do! */
    }

    // Shows a error message.
    public ConnectionErrorException(String message) {
        super(message);
    }
}

