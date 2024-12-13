package es.aketzagonzalez.jasperListaNaciones;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import es.aketzagonzalez.db.ConexionBBDD;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class MainApp {
   
    public static void main(String[] args) {
        ConexionBBDD db;
        try {
            db = new ConexionBBDD();
            // Cargar el reporte desde la ruta
            InputStream reportStream = db.getClass().getResourceAsStream("/jasper/PaisesUD3_ej1.jasper");
            if (reportStream == null) {
                System.out.println("El archivo no está ahí");
                return;
            } else {
                System.out.println("El archivo se ha encontrado");
            }
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("IMAGE_PATH", db.getClass().getResource("/imagenes/").toString());
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, db.getConnection());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
