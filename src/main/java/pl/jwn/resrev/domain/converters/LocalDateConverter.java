package pl.jwn.resrev.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

//TODO Czy to jest ostatecznie używane?
//TODO java.sql.Data nie powinniśmy używać, zostawić to w bebechach jdbc/hibernate
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate!=null) ? Date.valueOf(localDate) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return (date != null) ? date.toLocalDate() : null;
    }
}
