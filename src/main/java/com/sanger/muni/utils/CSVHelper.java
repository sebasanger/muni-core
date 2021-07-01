package com.sanger.muni.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import com.sanger.muni.model.Concepto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.model.TipoConcepto;
import com.sanger.muni.model.TipoLiquidacion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CSVHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static Set<Liquidacion> csvToLiquidaciones(InputStream is) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Set<Liquidacion> liquidaciones = new HashSet<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                LocalDate fecha = LocalDate.parse("10/" + csvRecord.get("periodo"),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                Liquidacion liquidacion = liquidaciones.stream()
                        .filter(liquid -> liquid.getPeriodo().equals(fecha)
                                && liquid.getLegajo().toString().equals(csvRecord.get("legajo")))
                        .findFirst().orElse(new Liquidacion());

                liquidacion.setLegajo(Long.parseLong(csvRecord.get("legajo")));

                liquidacion.setPeriodo(fecha);

                if (Integer.parseInt(csvRecord.get("tipo")) == 1) {
                    liquidacion.setTipoLiquidacion(TipoLiquidacion.SUELDO);
                } else if (Integer.parseInt(csvRecord.get("tipo")) == 2) {
                    liquidacion.setTipoLiquidacion(TipoLiquidacion.AGUINALDO);
                } else if (Integer.parseInt(csvRecord.get("tipo")) == 3) {
                    liquidacion.setTipoLiquidacion(TipoLiquidacion.HORAS_EXTRAS);
                } else if (Integer.parseInt(csvRecord.get("tipo")) == 4) {
                    liquidacion.setTipoLiquidacion(TipoLiquidacion.AYUDA_ESCOLAR);
                }

                LiquidacionConcepto liquidacionConcepto = new LiquidacionConcepto();
                Concepto concepto = new Concepto();

                concepto.setId(Long.parseLong(csvRecord.get("concepto")));
                concepto.setDescripcion(csvRecord.get("descrip"));

                if (Integer.parseInt(csvRecord.get("acumulador")) == 1) {
                    concepto.setTipoConcepto(TipoConcepto.REMUNERATIVO);
                } else if (Integer.parseInt(csvRecord.get("acumulador")) == 2) {
                    concepto.setTipoConcepto(TipoConcepto.NO_REMUNERATIVO);
                } else if (Integer.parseInt(csvRecord.get("acumulador")) == 3) {
                    concepto.setTipoConcepto(TipoConcepto.DEDUCCION);
                } else if (Integer.parseInt(csvRecord.get("acumulador")) == 4) {
                    concepto.setTipoConcepto(TipoConcepto.APORTE_PATRONAL);
                }

                liquidacionConcepto.setConcepto(concepto);

                liquidacionConcepto.setCantidad(Float.parseFloat(csvRecord.get("cantidad")));
                liquidacionConcepto.setImporte(Double.parseDouble(csvRecord.get("importe")));

                liquidacion.addLiquidacionConcepto(liquidacionConcepto);

                liquidaciones.add(liquidacion);

            }

            return liquidaciones;
        } catch (Exception e) {

            System.err.println(e);
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}