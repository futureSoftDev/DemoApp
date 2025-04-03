package uz.company.employeemanagementsystem.filter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleFilter {

    Integer page = 0;

    Integer size = 10;

    String search = "";

    String orderBy = "id";

    @JsonIgnore
    public int getStart() {
        return this.getPage() * this.getSize();
    }

    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(this.getPage(), this.getSize());
    }

    public String getSearch() {
        return "%" + this.search + "%";
    }
}
