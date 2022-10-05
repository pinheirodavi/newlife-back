package br.com.itbeta.newlife.repositories.projections;

public interface MoradorDetails {
    Long getIdApto();
    String getMorador();
    String getRg();
    String getCpf();
    String getTel1();
    String getTel2();
    String getEmail();
    String getContatoEmergencia();
    String getTelEmergencia();
    String getObs();
}
