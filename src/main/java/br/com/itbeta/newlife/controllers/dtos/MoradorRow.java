package br.com.itbeta.newlife.controllers.dtos;

import br.com.itbeta.newlife.exception.SheetImportException;
import br.com.itbeta.newlife.models.Apartamento;
import org.springframework.util.StringUtils;

public class MoradorRow {
    public final Apartamento apartamento;
    public final MoradorRowError error;
    public final String errorText;
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
            Boolean hasError,
            String errorText
    ) {
        this.apartamento = apartamento;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
        this.contatoEmerg = contatoEmerg;
        this.telefoneEmerg = telefoneEmerg;
        this.obs = obs;
        this.rowNumber = rowNumber;
        this.error = error;
        this.hasError = hasError;
        this.errorText = errorText;
    }

    public static MoradorRowBuilder builder() {
        return new MoradorRowBuilder();
    }

    public static class MoradorRowBuilder {
        private Boolean hasError = false;
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

        private String errorText;

        public MoradorRowBuilder rowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
            return this;
        }

        public MoradorRowBuilder apartamento(Apartamento apartamento) {
            String notEmptyMessage = this.assertApartamentoNotEmpty(apartamento);
            this.apartamento = apartamento;
            return this;
        }

        public MoradorRowBuilder nome(String nome) {
            String notEmptyMessage = this.assertStringNotEmpty(nome, "Nome");
            this.nome = nome;
            return this;
        }

        public MoradorRowBuilder rg(String rg) {
            String notEmptyMessage = this.assertStringNotEmpty(rg, "RG");
            if (StringUtils.hasText(notEmptyMessage)) {
                this.rg = rg;
                return this;
            }
            String wrongNumberField = this.validateNumberField(rg, "RG", 9);
            this.rg = rg;
            return this;
        }

        public MoradorRowBuilder cpf(String cpf) {
            String notEmptyMessage = this.assertStringNotEmpty(cpf, "CPF");
            if (StringUtils.hasText(notEmptyMessage)) {
                this.cpf = cpf;
                return this;
            }
            String wrongNumberField = this.validateNumberField(cpf, "CPF", 11);
            this.cpf = cpf;
            return this;
        }

        public MoradorRowBuilder telefone1(String telefone1) {
            String notEmptyMessage = this.assertStringNotEmpty(telefone1, "Telefone Principal");
            if (StringUtils.hasText(notEmptyMessage)) {
                this.telefone1 = telefone1;
                return this;
            }
            String wrongNumberField = this.validateNumberField(telefone1, "Telefone Principal", 11);
            this.telefone1 = telefone1;
            return this;
        }

        public MoradorRowBuilder telefone2(String telefone2) {
            if (telefone2 != null) {
                String wrongNumberField = this.validateNumberField(telefone2, "Telefone Secundário", 11);
                this.telefone2 = telefone2;
                return this;
            }
            this.telefone2 = telefone2;
            return this;
        }

        public MoradorRowBuilder email(String email) {
            String notEmptyMessage = this.assertStringNotEmpty(email, "Email");
            if (StringUtils.hasText(notEmptyMessage)) {
                this.email = email;
                return this;
            }
            String validateEmail = this.validateEmail(email);
            errorText = validateEmail;
            this.email = email;
            return this;
        }

        public MoradorRowBuilder contatoEmerg(String contatoEmerg) {
            this.contatoEmerg = contatoEmerg;
            return this;
        }

        public MoradorRowBuilder telefoneEmerg(String telefoneEmerg) {
            if (telefoneEmerg != null) {
                String wrongNumberField = this.validateNumberField(telefoneEmerg, "Telefone de Emergência", 11);
                this.telefoneEmerg = telefoneEmerg;
                return this;
            }
            this.telefoneEmerg = telefoneEmerg;
            return this;
        }

        public MoradorRowBuilder obs(String obs) {
            this.obs = obs;
            return this;
        }

        private String assertStringNotEmpty(String input, String field) {
            if (!StringUtils.hasText(input)) {
                this.hasError = true;
                throw new SheetImportException("O campo " + field + " é obrigatório! Falha na linha: " + (rowNumber + 1));
            }
            return "";
        }

        private String assertApartamentoNotEmpty(Apartamento apto) {
            if (apto == null) {
                this.hasError = true;
                throw new SheetImportException("O campo Apartamento não pode ser vazio! Falha na linha: " + (rowNumber + 1));
            }
            return "";
        }

        private String validateNumberField(String input, String field, int length) {
            if (input.length() != length) {
                this.hasError = true;
                throw new SheetImportException("O campo " + field + " deve possuir " + length + " dígitos. Falha na linha: " + (rowNumber + 1));
            }
            try {
                Long.parseLong(input);
                return "";
            } catch (Exception e) {
                this.hasError = true;
                throw new SheetImportException("O campo " + field + " deve possuir apenas números. Falha na linha: " + (rowNumber + 1));
            }
        }

        private String validateEmail(String input) {
            if (input != null) {
                if (!input.contains("@")) {
                    this.hasError = true;
                    throw new SheetImportException("Insira um email válido. Falha na linha: " + (rowNumber + 1));
                } else if (input.indexOf("@") != input.lastIndexOf("@")) {
                    this.hasError = true;
                    throw new SheetImportException("O email não pode conter mais de um caractere @. Falha na linha: " + (rowNumber + 1));
                }
                return "";
            }
            this.hasError = true;
            throw new SheetImportException("O campo email não pode ser vazio. Falha na linha: " + (rowNumber + 1));
        }

        public MoradorRow build() {
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
                    this.rowNumber + 1,
                    this.errorBuilder.build(),
                    this.hasError,
                    this.errorText
            );
        }
    }
}
