package fabio.uniasselvi.com.br.parkinglot.services;

import fabio.uniasselvi.com.br.parkinglot.dtos.CarroRequestDTO;
import fabio.uniasselvi.com.br.parkinglot.dtos.CarroResponseDTO;
import fabio.uniasselvi.com.br.parkinglot.entities.Carro;
import fabio.uniasselvi.com.br.parkinglot.entities.Vaga;
import fabio.uniasselvi.com.br.parkinglot.exceptions.BusinessException;
import fabio.uniasselvi.com.br.parkinglot.repositories.CarroRepository;
import fabio.uniasselvi.com.br.parkinglot.repositories.VagaRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CarroService {

    @Inject
    VagaRepository vagaRepository;

    @Inject
    CarroRepository carroRepository;

    @Transactional
    public void criarCarro(CarroRequestDTO request) throws BusinessException {

        Carro carro = setCampos(request);

        carroRepository.criarCarro(carro);
    }

    public List<CarroResponseDTO> listarCarros() {
        List<Carro> carros = carroRepository.listarCarros();

        return carros.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Carro setCampos(CarroRequestDTO request) throws BusinessException {
        Carro carro = new Carro();

        Vaga vaga = vagaRepository.getById(request.getVagaId());
        if (vaga == null) {
            throw new BusinessException("Vaga não encontrada!");
        }
        if (vaga.getOcupada()) {
            throw new BusinessException("Vaga com o número : " + vaga.getNumero() + " já está ocupada!");
        }

        carro.setCor(request.getCor());
        carro.setPlaca(request.getPlaca());
        carro.setModelo(request.getModelo());
        carro.setVaga(vaga);

        vaga.setOcupada(true);
        vagaRepository.atualizarVaga(vaga);

        return carro;
    }

    public CarroResponseDTO convertToDTO(Carro carro) {
        CarroResponseDTO dto = new CarroResponseDTO();

        dto.setId(carro.getId());
        dto.setVaga(carro.getVaga());
        dto.setPlaca(carro.getPlaca());
        dto.setModelo(carro.getModelo());
        dto.setCor(carro.getCor());

        return dto;
    }
}
