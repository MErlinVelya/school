package com.veresklia.entity.exception;

import java.sql.SQLException;

public class DaoLevelException extends RuntimeException {

    public DaoLevelException (String errorMessage, SQLException error){
        super(errorMessage, error);
    }
}
