package com.dashulan.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class FileWriteIntegrationConfig {

    @Bean
    public IntegrationFlow fileWriterFlow(){
        return IntegrationFlows.from(MessageChannels.direct("textInChannel"))
                .<String,String>transform(t->t.toUpperCase())
                .handle(Files.outboundAdapter(new File("/temp/sia5/files"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))
                .get();

    }
}
