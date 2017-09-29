package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Essay;

@Component
@Transactional
public class EssayToStringConverter implements Converter<Essay, String>{
	
	@Override
	public String convert(Essay essay){
		String result;
		
		if(essay == null)
			result = null;
		
		else
			result = String.valueOf(essay.getId());
		
		return result;
	}

}
