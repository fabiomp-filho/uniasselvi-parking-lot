package fabio.uniasselvi.com.br.parkinglot.controllers;

import fabio.uniasselvi.com.br.parkinglot.dtos.VagaRequestDTO;
import fabio.uniasselvi.com.br.parkinglot.dtos.VagaResponseDTO;
import fabio.uniasselvi.com.br.parkinglot.services.VagaService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
@Getter
@Setter
public class VagaBean {

    private Integer numero;

    private List<VagaResponseDTO> vagas;

    @Inject
    private VagaService vagaService;

    @PostConstruct
    public void init() {
        vagas = vagaService.listarVagas();
    }


    public void criarVaga() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {

            VagaRequestDTO vagaRequestDTO = new VagaRequestDTO(numero);
            vagaService.criarVaga(vagaRequestDTO);
            PrimeFaces.current().resetInputs("vagaForm");
            PrimeFaces.current().executeScript("PF('incluirVaga').hide();");
            PrimeFaces.current().ajax().update(":tabelaVagas");
            vagas = vagaService.listarVagas();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Vaga criada com sucesso"));
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }

    }
}
