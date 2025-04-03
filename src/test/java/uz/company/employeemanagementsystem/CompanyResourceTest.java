package uz.company.employeemanagementsystem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uz.company.employeemanagementsystem.dto.CompanyDTO;
import uz.company.employeemanagementsystem.dto.CompanyDetailDTO;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.service.CompanyService;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CompanyService companyService;

    @Test
    void create() throws Exception {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName("TEST company");
        companyDTO.setAddress("TEST address");
        companyDTO.setBrand("TEST brand");
        Mockito.when(companyService.create(companyDTO)).thenReturn(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/company")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(result -> MockMvcResultMatchers.status().isOk());
    }

    @Test
    void update() throws Exception {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName("TEST company update");
        companyDTO.setAddress("TEST address update");
        companyDTO.setBrand("TEST brand update");
        Mockito.when(companyService.update(1L, companyDTO)).thenReturn(1L);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/company")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(result -> MockMvcResultMatchers.status().isOk());
    }

    @Test
    void get() throws Exception {
        Mockito.when(companyService.get(1L)).thenReturn(new CompanyDetailDTO());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/company/{id}","id")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(result -> MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getList() throws Exception {
        BaseFilter filter = new BaseFilter();
        filter.setCompanyId(1L);
        filter.setSearch("TEST");
        Mockito.when(companyService.getList(filter)).thenReturn(new PageImpl<>(List.of(), filter.getPageable(), filter.getSize()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/company")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(result -> MockMvcResultMatchers.status().isOk());
    }
}
