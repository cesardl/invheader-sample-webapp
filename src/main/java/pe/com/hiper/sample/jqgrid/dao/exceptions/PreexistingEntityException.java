package pe.com.hiper.sample.jqgrid.dao.exceptions;

/**
 *
 * @author Cesardl
 */
public class PreexistingEntityException extends Exception {

    public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public PreexistingEntityException(String message) {
        super(message);
    }
}
