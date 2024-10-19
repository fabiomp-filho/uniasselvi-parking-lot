package fabio.uniasselvi.com.br.parkinglot.dtos;

import fabio.uniasselvi.com.br.parkinglot.entities.Carro;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VagaResponseDTO {

    private Long id;
    private Integer numero;
    private String ocupada;
    private Carro carro;
}
