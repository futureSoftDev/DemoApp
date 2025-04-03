package uz.company.employeemanagementsystem.filter;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseFilter extends SimpleFilter {

    Long companyId;
}
