package br.com.batalha.util;

import br.com.batalha.model.PersonagemModel;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class PersonagemUtil {
	
	//Variáveis final
	final String arquivo = "/media/joao/HD/Scorpion/Projetos/Desenvolvimento(Java e JavaWeb)/BatalhaMortal/src/java/br/com/batalha/controller/charcters_stats.csv";
	
	public PersonagemUtil(){
		
	}
	
	public List<PersonagemModel> carregaCSV() throws IOException{
		List<PersonagemModel> personagemDtos = new ArrayList<>();
		Reader reader = Files.newBufferedReader(Paths.get(arquivo));
		CSVReader leitor = new CSVReaderBuilder(reader).withSkipLines(1).build();
		List<String[]> csv = leitor.readAll();
		Integer count = 0;
		
		for (String[] linhas : csv){
			PersonagemModel personagemDto = new PersonagemModel();
	        for (String coluna : linhas){
	        	if(count == 0)
	        		personagemDto.setNome(coluna);
	        	if(count == 1)
	        		personagemDto.setAlinhamento(coluna);
	        	if(count == 2)
	        		personagemDto.setInteligencia(Integer.valueOf(coluna));
	        	if(count == 3)
	        		personagemDto.setForca(Integer.valueOf(coluna));
	        	if(count == 4)
	        		personagemDto.setDestreza(Integer.valueOf(coluna));
	        	if(count == 5)
	        		personagemDto.setDefesa(Integer.valueOf(coluna));
	        	if(count == 6)
	        		personagemDto.setPoder(Integer.valueOf(coluna));
	        	if(count == 7)
	        		personagemDto.setCombate(Integer.valueOf(coluna));
	        	
	        	count++;
	        	
	        	if(count == 8){
	        		personagemDtos.add(personagemDto);
	        		personagemDto = null;
	        		count = 0;
	        	}
	        }
		}
		
		leitor.close();
		
		return personagemDtos;
	}
}