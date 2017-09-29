package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.RepoRepository;

import domain.Repo;

@Component
@Transactional
public class StringToRepoConverter implements Converter<String, Repo>{
	
	@Autowired
	RepoRepository repoRepository;
	
	@Override
	public Repo convert(String text){
		Repo result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = repoRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
