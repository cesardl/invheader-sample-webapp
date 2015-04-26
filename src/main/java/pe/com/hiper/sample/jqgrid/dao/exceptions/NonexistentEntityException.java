package pe.com.hiper.sample.jqgrid.dao.exceptions;

/**
 *
 * @author Cesardl
 */
public class NonexistentEntityException extends Exception {

    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonexistentEntityException(String message) {
        super(message);
    }
}
