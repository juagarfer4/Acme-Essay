package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PublicSession;

@Component
@Transactional
public class PublicSessionToStringConverter implements Converter<PublicSession, String>{
	
	@Override
	public String convert(PublicSession publicSession){
		String result;
		
		if(publicSession == null)
			result = null;
		
		else
			result = String.valueOf(publicSession.getId());
		
		return result;
	}

}
