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
import com.sanger.muni.model.UserEntity;
import com.sanger.muni.model.UserRole;

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

    public static Set<Liquidacion> csvToLiquidaciones(InputStream conceptos, InputStream maestros) {

        try (BufferedReader fileReaderCocneptos = new BufferedReader(new InputStreamReader(conceptos, "UTF-8"));
                BufferedReader fileReaderMaestros = new BufferedReader(new InputStreamReader(maestros, "UTF-8"));
                CSVParser csvParserConceptos = new CSVParser(fileReaderCocneptos,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
                CSVParser csvParserMaestros = new CSVParser(fileReaderMaestros,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

        ) {

            Set<Liquidacion> liquidaciones = new HashSet<>();

            Iterable<CSVRecord> csvRecordsConceptos = csvParserConceptos.getRecords();

            Iterable<CSVRecord> csvRecordsMaestros = csvParserMaestros.getRecords();

            Set<UserRole> role = new HashSet<>();
            role.add(UserRole.USER);

            for (CSVRecord csvRecordConceptos : csvRecordsConceptos) {

                LocalDate fecha = LocalDate.parse("10/" + csvRecordConceptos.get("periodo"),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                Liquidacion liquidacion = liquidaciones.stream()
                        .filter(liquid -> liquid.getPeriodo().equals(fecha)
                                && liquid.getLegajo().toString().equals(csvRecordConceptos.get("legajo")))
                        .findFirst().orElse(new Liquidacion());

                liquidacion.setLegajo(Long.parseLong(csvRecordConceptos.get("legajo")));

                liquidacion.setPeriodo(fecha);

                if (Integer.parseInt(csvRecordConceptos.get("tipo")) == 1) {
                    liquidacion.setTipoLiquidacion(TipoLiquidacion.SUELDO);
                } else if (Integer.parseInt(csvRecordConceptos.get("tipo")) == 2) {
                    liquidacion.setTipoLiquidacion(TipoLiquidacion.AGUINALDO);
                } else if (Integer.parseInt(csvRecordConceptos.get("tipo")) == 3) {
                    liquidacion.setTipoLiquidacion(TipoLiquidacion.HORAS_EXTRAS);
                } else if (Integer.parseInt(csvRecordConceptos.get("tipo")) == 4) {
                    liquidacion.setTipoLiquidacion(TipoLiquidacion.AYUDA_ESCOLAR);
                }

                LiquidacionConcepto liquidacionConcepto = new LiquidacionConcepto();
                Concepto concepto = new Concepto();

                concepto.setId(Long.parseLong(csvRecordConceptos.get("concepto")));
                concepto.setDescripcion(csvRecordConceptos.get("descrip"));

                if (Integer.parseInt(csvRecordConceptos.get("acumulador")) == 1) {
                    concepto.setTipoConcepto(TipoConcepto.REMUNERATIVO);
                } else if (Integer.parseInt(csvRecordConceptos.get("acumulador")) == 2) {
                    concepto.setTipoConcepto(TipoConcepto.NO_REMUNERATIVO);
                } else if (Integer.parseInt(csvRecordConceptos.get("acumulador")) == 3) {
                    concepto.setTipoConcepto(TipoConcepto.DEDUCCION);
                } else if (Integer.parseInt(csvRecordConceptos.get("acumulador")) == 4) {
                    concepto.setTipoConcepto(TipoConcepto.APORTE_PATRONAL);
                }

                liquidacionConcepto.setConcepto(concepto);

                liquidacionConcepto.setCantidad(Float.parseFloat(csvRecordConceptos.get("cantidad")));
                liquidacionConcepto.setImporte(Double.parseDouble(csvRecordConceptos.get("importe")));

                for (CSVRecord csvRecordMaestros : csvRecordsMaestros) {
                    if (liquidacion.getLegajo() == Long.parseLong(csvRecordMaestros.get("eleg"))) {
                        liquidacion.setSeccion(csvRecordMaestros.get("esec"));
                        /*
                         * if (csvRecordMaestros.get("permanente").equals("1")) {
                         * liquidacion.setTipoContratacion(TipoContratacion.PLANTA_PERMANENTE); } else {
                         * liquidacion.setTipoContratacion(TipoContratacion.CONTRATADO); }
                         */
                        liquidacion.setNumeroCuenta(csvRecordMaestros.get("banco"));
                        liquidacion.setCategoria(Short.parseShort(csvRecordMaestros.get("ecatnew")));
                        liquidacion.setArea(csvRecordMaestros.get("dire"));

                        UserEntity user = new UserEntity();
                        user.setFullName(csvRecordMaestros.get("enom"));
                        user.setDireccion(csvRecordMaestros.get("edom"));
                        user.setCuil(csvRecordMaestros.get("cuil"));
                        user.setDni(csvRecordMaestros.get("edoc"));
                        user.setNumeroLegajo(csvRecordMaestros.get("eleg"));

                        user.setRoles(role);
                        liquidacion.setUser(user);
                    }
                }

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