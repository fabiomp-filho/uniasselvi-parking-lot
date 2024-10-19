package fabio.uniasselvi.com.br.parkinglot.services;

import fabio.uniasselvi.com.br.parkinglot.dtos.VagaRequestDTO;
import fabio.uniasselvi.com.br.parkinglot.dtos.VagaResponseDTO;
import fabio.uniasselvi.com.br.parkinglot.entities.Vaga;
import fabio.uniasselvi.com.br.parkinglot.exceptions.BusinessException;
import fabio.uniasselvi.com.br.parkinglot.repositories.VagaRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class VagaService {

    @Inject
    private VagaRepository vagaRepository;

    @Transactional
    public void criarVaga(VagaRequestDTO vagaRequestDTO) throws BusinessException {

        if (vagaRepository.vagaComMesmoNumeroExiste(vagaRequestDTO.getNumero())) {
            throw new BusinessException("Já existe uma vaga com o número " + vagaRequestDTO.getNumero());
        }

        Vaga vaga = new Vaga();
        vaga.setNumero(vagaRequestDTO.getNumero());
        vaga.setOcupada(false);
        vagaRepository.criarVaga(vaga);
    }

    public List<VagaResponseDTO> listarVagas() {

        List<Vaga> vagas = vagaRepository.listarVagas();

        return vagas.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    public List<VagaResponseDTO> listarVagasDisponiveis() {
        List<Vaga> vagas = vagaRepository.listarVagas();

        return vagas.stream()
                .filter(vaga -> !vaga.getOcupada())
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private VagaResponseDTO convertToResponseDTO(Vaga vaga) {
        VagaResponseDTO dto = new VagaResponseDTO();
        dto.setId(vaga.getId());
        dto.setNumero(vaga.getNumero());
        dto.setOcupada(vaga.getOcupada() ? "Ocupada" : "Livre");
        dto.setCarro(vaga.getCarro());
        return dto;
    }
}
