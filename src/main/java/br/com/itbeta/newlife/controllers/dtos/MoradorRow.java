package br.com.itbeta.newlife.controllers.dtos;

import br.com.itbeta.newlife.models.Apartamento;

public class MoradorRow {
    public final Apartamento apartamento;

    public final MoradorRowError error;

    public final String nome;

    public final String rg;

    public final String cpf;

    public final String telefone1;

    public final String telefone2;

    public final String email;

    public final String contatoEmerg;

    public final String telefoneEmerg;

    public final String obs;

    public final int rowNumber;

    public final Boolean hasError;

    private MoradorRow(
            Apartamento apartamento,
            String nome,
            String rg,
            String cpf,
            String telefone1,
            String telefone2,
            String email,
            String contatoEmerg,
            String telefoneEmerg,
            String obs,
            int rowNumber,
            MoradorRowError error,
            Boolean hasError
    ){
        this.apartamento =apartamento;
        this.nome =nome;
        this.rg =rg;
        this.cpf =cpf;
        this.telefone1 =telefone1;
        this.telefone2 =telefone2;
        this.email =email;
        this.contatoEmerg =contatoEmerg;
        this.telefoneEmerg =telefoneEmerg;
        this.obs =obs;
        this.rowNumber =rowNumber;
        this.error =error;
        this.hasError =hasError;
    }

    public static MoradorRowBuilder builder(){
        return new MoradorRowBuilder();
    }

    public static class MoradorRowBuilder{
        private Boolean hasError=false;
        private MoradorRowError.MoradorRowErrorBuilder errorBuilder = MoradorRowError.builder();
        private int rowNumber;
        private Apartamento apartamento;
        private String nome;
        private String rg;
        private String cpf;
        private String telefone1;
        private String telefone2;
        private String email;
        private String contatoEmerg;
        private String telefoneEmerg;
        private String obs;

        public MoradorRowBuilder apartamento(Apartamento apartamento){
            this.apartamento = apartamento;
            return this;
        }

        public MoradorRowBuilder nome(String nome){
            this.nome = nome;
            return this;
        }

        public MoradorRowBuilder rg(String rg){
            this.rg = rg;
            return this;
        }

        public MoradorRowBuilder cpf(String cpf){
            this.cpf = cpf;
            return this;
        }

        public MoradorRowBuilder telefone1(String telefone1){
            this.telefone1 = telefone1;
            return this;
        }

        public MoradorRowBuilder telefone2(String telefone2){
            this.telefone2 = telefone2;
            return this;
        }

        public MoradorRowBuilder email(String email){
            this.email = email;
            return this;
        }

        public MoradorRowBuilder contatoEmerg(String contatoEmerg){
            this.contatoEmerg = contatoEmerg;
            return this;
        }

        public MoradorRowBuilder telefoneEmerg(String telefoneEmerg){
            this.telefoneEmerg =telefoneEmerg;
            return this;
        }

        public MoradorRowBuilder obs(String obs){
            this.obs = obs;
            return this;
        }

        public MoradorRow build(){
            return new MoradorRow(
                    this.apartamento,
                    this.nome,
                    this.rg,
                    this.cpf,
                    this.telefone1,
                    this.telefone2,
                    this.email,
                    this.contatoEmerg,
                    this.telefoneEmerg,
                    this.obs,
                    this.rowNumber+1,
                    this.errorBuilder.build(),
                    this.hasError
            );
        }
    }
}
