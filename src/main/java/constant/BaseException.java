package constant;

/**
 * Created by Knigh on 2016/9/15.
 */
public class BaseException extends Exception {
    public String message;

    public BaseException(String message) {
        this.message = message;
    }
}
