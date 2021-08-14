package io.fsdev.ipldashboard.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoTeamFoundException extends RuntimeException {
    
    public NoTeamFoundException(){

    }

    public NoTeamFoundException(String teamName){
        super(teamName + " Not found in DB");
    }
    
}
