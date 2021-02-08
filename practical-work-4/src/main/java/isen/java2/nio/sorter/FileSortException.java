package isen.java2.nio.sorter;

public class FileSortException extends Exception {

    private static final long serialVersionUID = 498117166147641276L;

    public FileSortException() {
        super();
    }

    public FileSortException(String message, Throwable exception) {
        super(message, exception);
    }

    public FileSortException(String message) {
        super(message);
    }

    public FileSortException(Throwable exception) {
        super(exception);
    }

}
