package com.sgra.exportar;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sgra.modelo.Enfermedad;

@Component("/ReporteEnfermedades")
public class ExportarEnfermedadPDF extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Enfermedad> Enfermedad= (List<Enfermedad>) model.get("Enfermedad");

	
		
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 30, 20);
		document.open();
		
		/* Tabla Para El Titulo del PDF */
		PdfPTable tablaEncabezado = new PdfPTable(1);

		PdfPCell celda = new PdfPCell(new Phrase("Sistema de Gestión de Recetas Agrícolas"));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(51, 255, 196));
		celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setPadding(20);
		tablaEncabezado.addCell(celda);

		document.add(tablaEncabezado);

		PdfPTable tablaTitulo = new PdfPTable(1);

		PdfPCell celd = new PdfPCell(new Phrase("Reporte Enfermedades"));
		celd.setBorder(0);
		celd.setBackgroundColor(new Color(51, 181, 255));
		celd.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celd.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celd.setPadding(10);
		tablaTitulo.addCell(celd);

		document.add(tablaTitulo);
	
		PdfPTable tablaRA = new PdfPTable(4);


		tablaRA.addCell(new Phrase("Enfermedad"));
		tablaRA.addCell(new Phrase("Detalles"));
		tablaRA.addCell(new Phrase("Cultivo"));
		
		tablaRA.addCell(new Phrase("Variedad"));
	
		
		
		 
		Enfermedad.forEach(Lista -> {
			tablaRA.addCell(Lista.getNombre_enfermedad());
			tablaRA.addCell(Lista.getDetalles());
			tablaRA.addCell(Lista.getIdcultivo().getNombre_cultivo());
			tablaRA.addCell(Lista.getIdcultivo().getVariedad());
		
			
			
			///tablaRA.addCell(String.valueOf(Lista.getIdreceta().getDosificacion()));

			

		});

		document.add(tablaRA);
		
	}

}
