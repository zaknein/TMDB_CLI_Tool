package com.zaknein.TMDB_CLI_Tool.comandos;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


public class TypeCommand {
    
    @Parameter(
        names = "--type",
        description = "Type of movies",
        required = true
    )

    private String type;

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
