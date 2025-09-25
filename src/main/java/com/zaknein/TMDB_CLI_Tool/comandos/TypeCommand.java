package com.zaknein.TMDB_CLI_Tool.comandos;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


@Parameters(
    commandNames = "type",
    commandDescription = "select the type"
)
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
}
