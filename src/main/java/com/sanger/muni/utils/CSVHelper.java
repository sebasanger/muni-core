package com.sanger.muni.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import com.sanger.muni.model.Concepto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.model.TipoConcepto;
import com.sanger.muni.model.UserEntity;

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

        String tipo = "eleg,esec,edir,ecat,ecatnew,enom,edom,edoc,efing,banco,cuil,dire";

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Set<Liquidacion> liquidaciones = new HashSet<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                Liquidacion liquidacion = new Liquidacion();
                UserEntity user = new UserEntity();
                user.setNumeroLegajo(csvRecord.get("eleg"));
                user.setFullName(csvRecord.get("enom"));
                user.setCuil(csvRecord.get("cuil"));

                liquidacion.setUser(user);

                liquidaciones.add(liquidacion);

            }

            return liquidaciones;
        } catch (Exception e) {

            System.err.println(e);
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static Set<LiquidacionConcepto> csvToLiquidacionConcepto(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Set<LiquidacionConcepto> liquidacionesConceptos = new HashSet<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                LiquidacionConcepto liquidacionConcepto = new LiquidacionConcepto();

                Concepto concepto = new Concepto();

                concepto.setId(Long.parseLong(csvRecord.get("concepto")));
                concepto.setDescripcion(csvRecord.get("descrip"));
                liquidacionConcepto.setConcepto(concepto);

                if (Integer.parseInt(csvRecord.get("tipo")) == 1) {
                    concepto.setTipoConcepto(TipoConcepto.REMUNERATIVO);
                } else if (Integer.parseInt(csvRecord.get("tipo")) == 2) {
                    concepto.setTipoConcepto(TipoConcepto.NO_REMUNERATIVO);
                } else if (Integer.parseInt(csvRecord.get("tipo")) == 3) {
                    concepto.setTipoConcepto(TipoConcepto.DEDUCCION);
                }

                liquidacionConcepto.setConcepto(concepto);

                liquidacionConcepto.setCantidad(Float.parseFloat(csvRecord.get("cantidad")));
                liquidacionConcepto.setImporte(Double.parseDouble(csvRecord.get("importe")));

                liquidacionesConceptos.add(liquidacionConcepto);

            }

            return liquidacionesConceptos;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}