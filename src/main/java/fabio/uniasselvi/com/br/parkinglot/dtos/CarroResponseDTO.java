package fabio.uniasselvi.com.br.parkinglot.dtos;

import fabio.uniasselvi.com.br.parkinglot.entities.Vaga;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarroResponseDTO {

    private Long id;
    private String placa;
    private String modelo;
    private String cor;
    private Vaga vaga;

}
