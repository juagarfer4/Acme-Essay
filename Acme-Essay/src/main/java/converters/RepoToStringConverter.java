package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Repo;

@Component
@Transactional
public class RepoToStringConverter implements Converter<Repo, String>{
	
	@Override
	public String convert(Repo repo){
		String result;
		
		if(repo == null)
			result = null;
		
		else
			result = String.valueOf(repo.getId());
		
		return result;
	}

}
