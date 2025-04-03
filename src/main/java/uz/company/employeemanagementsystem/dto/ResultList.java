package uz.company.employeemanagementsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResultList<T> {

    private List<T> list = new ArrayList<>();
    private Long total;
}
