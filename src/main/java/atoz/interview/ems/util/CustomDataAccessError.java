package atoz.interview.ems.util;

import org.springframework.dao.DataAccessException;

public class CustomDataAccessError extends DataAccessException {

    public CustomDataAccessError(String msg, Throwable cause) {
        super(msg, cause);
    }
}