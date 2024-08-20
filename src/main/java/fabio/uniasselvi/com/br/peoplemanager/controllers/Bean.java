package fabio.uniasselvi.com.br.peoplemanager.controllers;

import lombok.Getter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean
@RequestScoped
@Getter
public class Bean {

    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void enviar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            facesContext.addMessage(null,
                    new FacesMessage("Enviado com sucesso", "Nome: " + nome));
        } else {
            System.err.println("FacesContext is not available.");
        }
    }
}
