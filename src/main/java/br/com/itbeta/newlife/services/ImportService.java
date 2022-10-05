package br.com.itbeta.newlife.services;

import br.com.itbeta.newlife.controllers.dtos.MoradorRow;
import br.com.itbeta.newlife.models.Apartamento;
import br.com.itbeta.newlife.models.Morador;
import br.com.itbeta.newlife.repositories.ApartamentoRepository;
import br.com.itbeta.newlife.repositories.MoradorRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImportService {

    private List<Apartamento> apartamentoList = new ArrayList<>();

    private final ApartamentoRepository apartamentoRepository;

    private final MoradorRepository moradorRepository;

    public void importSheet(
            InputStream inputStream
    ) throws IOException,
            SAXException,
            OpenXML4JException {
        List<MoradorRow> moradorRows = this.parseSheet(inputStream);

        this.registerMoradores(moradorRows);
    }

    public List<MoradorRow> parseSheet(InputStream inputStream)
            throws IOException, SAXException, OpenXML4JException {
        this.apartamentoList = this.apartamentoRepository.findAll();
        OPCPackage pkg = OPCPackage.open(inputStream);
        SheetParser<List<MoradorRow>> sheetParser = new SheetParser<>(pkg, 10, rowInfo -> {
            String[] row = rowInfo.row;
            ArrayList<MoradorRow> rows = new ArrayList<>();
            MoradorRow moradorRow = MoradorRow
                    .builder()
                    .apartamento(this.getApto(row[0]))
                    .nome(row[1])
                    .rg(row[2])
                    .cpf(row[3])
                    .telefone1(row[4])
                    .telefone2(row[5])
                    .email(row[6])
                    .contatoEmerg(row[7])
                    .telefoneEmerg(row[8])
                    .obs(row[9])
                    .build();
            rows.add(moradorRow);
            return rows;
        });

        Map<Boolean, List<MoradorRow>> rows = sheetParser
                .process()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(row -> row.hasError));
        List<MoradorRow> wrongRows = rows.get(true);
        List<MoradorRow> rightRows = rows.get(false);
        return rightRows;
    }

    public void registerMoradores(List<MoradorRow> moradorRows) {
        int moradorSize = moradorRows.size();

        ArrayList<Morador> allMoradores = new ArrayList<>(moradorSize);

        long count = 0;

        for (MoradorRow moradorRow : moradorRows) {
            Morador morador = Morador.builder()
                    .apartamento(moradorRow.apartamento)
                    .nome(moradorRow.nome)
                    .rg(moradorRow.rg)
                    .cpf(moradorRow.cpf)
                    .tel1(moradorRow.telefone1)
                    .tel2(moradorRow.telefone2)
                    .email(moradorRow.email)
                    .contatoEmergencia(moradorRow.contatoEmerg)
                    .telEmergencia(moradorRow.telefoneEmerg)
                    .obs(moradorRow.obs)
                    .build();
            allMoradores.add(morador);
            count++;
            System.out.println(count);
        }
        System.out.println("TAMANHO: "+allMoradores.size());
        List<Morador> moradores = this.moradorRepository.saveAll(allMoradores);
    }

    public Apartamento getApto(String numeroApto) {
        for (Apartamento apartamento : this.apartamentoList) {
            if (numeroApto != null)
                if (apartamento.getNumApto() == Long.parseLong(numeroApto)) {
                    return apartamento;
                }
        }
        return null;
    }

}
