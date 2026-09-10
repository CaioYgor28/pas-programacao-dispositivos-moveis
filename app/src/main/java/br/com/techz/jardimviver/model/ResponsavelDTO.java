package br.com.techz.jardimviver.model;

/**
 * Objeto de transferência de dados (DTO) utilizado entre a interface
 * e a camada de persistência local.
 */
public class ResponsavelDTO {
    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private int idade;
    private String endereco;

    public ResponsavelDTO() {
    }

    public ResponsavelDTO(long id, String nome, String sobrenome, String email, int idade, String endereco) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
        this.endereco = endereco;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
