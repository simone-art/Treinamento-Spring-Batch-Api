package com.example.demospringbatchapi;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    //Definir as variáveis Job e step tendo em conta que dentro do job o step está
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    //Código que configura um job chamado jobRestService
    //RunIdIncrementer permite múltiplas execuções
    @Bean
    public Job job(){
     return this.jobBuilderFactory.get("JobRestService")
             .incrementer(new RunIdIncrementer())
             .start(step1())
             .build();
    }

    //Definir o step usando tasklet
    //Trecho de código que tem um step chamado step1 que imprime uma mensagem de texto no log.
    @Bean
    public Step step1(){
     return this.stepBuilderFactory.get("step1").tasklet((stepContribution, chunkContext) -> {
         System.out.println("Step1 rodou com sucesso!");
         return RepeatStatus.FINISHED;
     }).build();
    }


}
