package fabio.uniasselvi.com.br.parkinglot.controllers;

import fabio.uniasselvi.com.br.parkinglot.dtos.CarroRequestDTO;
import fabio.uniasselvi.com.br.parkinglot.dtos.CarroResponseDTO;
import fabio.uniasselvi.com.br.parkinglot.dtos.VagaResponseDTO;
import fabio.uniasselvi.com.br.parkinglot.services.CarroService;
import fabio.uniasselvi.com.br.parkinglot.services.VagaService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
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
public class CarroBean {

    private String placa;
    private String modelo;
    private String cor;
    private Long vagaId;
    private List<VagaResponseDTO> vagasDisponiveis; // Lista de vagas disponíveis
    private List<CarroResponseDTO> carros;

    @Inject
    private CarroService carroService;

    @Inject
    private VagaService vagaService;

    @PostConstruct
    public void init() {
        carros = carroService.listarCarros();
        vagasDisponiveis = vagaService.listarVagasDisponiveis();
        resetarValores();
    }


    public void criarCarro() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {

            CarroRequestDTO request = new CarroRequestDTO(placa, modelo, cor, vagaId);
            carroService.criarCarro(request);
            PrimeFaces.current().resetInputs("carroForm");
            PrimeFaces.current().executeScript("PF('incluirCarro').hide();");
            PrimeFaces.current().ajax().update(":tabelaCarros");
            PrimeFaces.current().ajax().update("carroForm");
            carros = carroService.listarCarros();
            vagasDisponiveis = vagaService.listarVagasDisponiveis();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Carro criado com sucesso"));
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }

    }

    private void resetarValores() {
        placa = "";
        modelo = "";
        cor = "";
        vagaId = null;
    }
}
