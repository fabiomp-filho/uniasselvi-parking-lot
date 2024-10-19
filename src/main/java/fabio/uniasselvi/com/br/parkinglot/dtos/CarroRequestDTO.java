package fabio.uniasselvi.com.br.parkinglot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarroRequestDTO {

    private String placa;

    private String modelo;

    private String cor;

    private Long vagaId;
}
