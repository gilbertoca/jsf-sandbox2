package jsf.sandbox.model;

import java.util.Objects;

/**
 *
 * @author gilberto.andrade
 */
public class Sacado {
    private String cpf;
    private String nome;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;

    public Sacado() {
    }

    public Sacado(String cpf, String nome, String logradouro, String complemento, String bairro, String cidade, String cep) {
        this.cpf = cpf;
        this.nome = nome;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sacado other = (Sacado) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Sacado[");
        builder.append("bairro=").append(bairro);
        builder.append(", cep=").append(cep);
        builder.append(", cidade=").append(cidade);
        builder.append(", complemento=").append(complemento);
        builder.append(", cpf=").append(cpf);
        builder.append(", logradouro=").append(logradouro);
        builder.append(", nome=").append(nome);
        builder.append("]");
        return builder.toString();
    }
    
}
