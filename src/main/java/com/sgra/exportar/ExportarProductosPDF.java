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
import com.sgra.modelo.Producto;

@Component("/ReporteProductos")
public class ExportarProductosPDF extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Producto>Cultivos=(List<Producto>)model.get("Productos");
	
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

		PdfPCell celd = new PdfPCell(new Phrase("Reporte de Productos"));
		celd.setBorder(0);
		celd.setBackgroundColor(new Color(51, 181, 255));
		celd.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celd.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celd.setPadding(10);
		tablaTitulo.addCell(celd);

		document.add(tablaTitulo);
	
		PdfPTable tablaP=new PdfPTable(6);
		
		tablaP.addCell(new Phrase("+ Producto: "));
		tablaP.addCell(new Phrase("+ Tipo: "));
		tablaP.addCell(new Phrase("+ Detalles: "));
		tablaP.addCell(new Phrase("+ Contenido:"));
		tablaP.addCell(new Phrase("+ Unidad:"));
		tablaP.addCell(new Phrase("+ Costo: "));
		Cultivos.forEach(ListProd->{
		
			tablaP.addCell(ListProd.getNombre_producto());
			tablaP.addCell(ListProd.getTipoproducto());
			tablaP.addCell(ListProd.getDetalles());
			tablaP.addCell(String.valueOf(ListProd.getContenido()));
			tablaP.addCell(ListProd.getUnidad());
			tablaP.addCell(String.valueOf(ListProd.getCosto()));
		});
		
		document.add(tablaP);
	
	}
}
