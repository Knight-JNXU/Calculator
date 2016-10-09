package constant;

import org.apache.log4j.Logger;

/**
 * Created by Knigh on 2016/9/15.
 */
public class MyException extends BaseException {
    private static final long serialVersionUID = 3152616724785436891L;
    private static final Logger log = Logger.getLogger(MyException.class);
    public MyException(String message) {
        super(message);
    }
}
