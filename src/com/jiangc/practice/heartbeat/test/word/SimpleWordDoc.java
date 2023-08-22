package com.jiangc.practice.heartbeat.test.word;

import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/doc/export/")
public class SimpleWordDoc {

	@RequestMapping(value = "word" ,method = RequestMethod.GET)
	public void export(HttpServletResponse response) throws Exception {
		   try {
		       XWPFDocument xdoc = new XWPFDocument();//����xdoc
		       XWPFParagraph xpara = xdoc.createParagraph();//��������
		       xpara.setAlignment(ParagraphAlignment.CENTER);//����
		       XWPFParagraph xpara1 = xdoc.createParagraph();
		       xpara1.setAlignment(ParagraphAlignment.RIGHT);//����
		       XWPFTable xtable = xdoc.createTable(7, 4);//7��4�б��
		       XWPFRun run = xpara.createRun();
		       run.setFontSize(20);//�����С
		       run.setText("��ͷ");
		       run.addCarriageReturn();
		       XWPFRun run2 = xpara1.createRun();
		       run2.setFontSize(10);//�����С
		       run2.setText("����ʱ��");
		       XWPFTableRow comTableRowOne = xtable.getRow(0);
		       centerTableContent(xtable);
		       comTableRowOne.getCell(0).setText("��������");
		       comTableRowOne.getCell(1).setText("��������");
		       for (int p = 0; p <= 6; p++) {
		           
		    	   comTableRowOne.setHeight(650);//�����и�
		           XWPFTableRow comTableRow = xtable.getRow(p);
		           if (p != 2 && p != 3) {
		               for (int j = 1; j <= 3; j++) {
		                   // �Ե�Ԫ����кϲ���ʱ��,Ҫ��־��Ԫ���Ƿ�Ϊ���,�����Ƿ�Ϊ�����ϲ�
		                   if (j == 1) {
		                       // �������
		                       comTableRow.getCell(j).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
		                   } else {
		                       // �����ϲ�
		                       comTableRow.getCell(j).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
		                   }
		               }
		           }
		       }
		       XWPFTableRow comTableRowTwo = xtable.getRow(1);
		       comTableRowTwo.getCell(0).setText("����ص�");
		       comTableRowTwo.getCell(1).setText("����ص�");
		       XWPFTableRow comTableRowThree = xtable.getRow(2);
		       for (int j = 3; j <= 4; j++) {
		           for (int k = 0; k <= 3; ++k) {
		               //���õ�Ԫ����
		               XWPFTableCell cell30 = xtable.getRow(j).getCell(k);
		               CTTcPr tcpr30 = cell30.getCTTc().addNewTcPr();
		               CTTblWidth cellW30 = tcpr30.addNewTcW();
		               cellW30.setType(STTblWidth.DXA);
		               cellW30.setW(BigInteger.valueOf(4000));
		           }
		       }
		       comTableRowThree.getCell(0).setText("����������");
		       comTableRowThree.getCell(1).setText("����������");
		       comTableRowThree.getCell(2).setText("�����¼��");
		       comTableRowThree.getCell(3).setText("�����¼��");
		       XWPFTableRow comTableRowFour = xtable.getRow(3);
		       comTableRowFour.getCell(0).setText("Ӧ������");
		       comTableRowFour.getCell(1).setText("ʵ����������");
		       comTableRowFour.getCell(2).setText("ʵ������");
		       comTableRowFour.getCell(3).setText("ʵ����������");
		       XWPFTableRow comTableRowSeven = xtable.getRow(4);
		       comTableRowSeven.getCell(0).setText("ȱϯ��Ա��ȱϯԭ��");
		       comTableRowSeven.getCell(1).setText("ȱϯ��Ա��ȱϯԭ�����");
		       XWPFTableRow comTableRowFive = xtable.getRow(5);
		       comTableRowFive.getCell(0).setText("��ϯ��Ա");
		       comTableRowFive.getCell(1).setText("����");
		       XWPFTableRow comTableRowSix = xtable.getRow(6);
		       comTableRowSix.getCell(0).setText("��ϯ��Ա");
		       comTableRowSix.getCell(1).setText("����");
		       //������������������
		       // File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		       // String desktopPath = desktopDir.getAbsolutePath();
		       // System.err.println(desktopPath);
		       // OutputStream os = new FileOutputStream(desktopPath + "\\���鵼��.docx");
		       // xdoc.write(os);
		       // os.close();
		       //���ص��ͻ���
		       String fileNameURL = URLEncoder.encode("���鵼��woord���.docx", "UTF-8");
		       response.setCharacterEncoding("UTF-8");
		       //response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		       response.setHeader("Content-disposition", "attachment;filename=" + fileNameURL + ";" + "filename*=utf-8''" + fileNameURL);
		       response.setContentType("application/octet-stream");
		       //ˢ�»���
		       response.flushBuffer();
		       OutputStream ouputStream = response.getOutputStream();
		       //workbook��Excelд�뵽response��������У���ҳ�����ظ�Excel�ļ�
		       xdoc.write(ouputStream);
		       ouputStream.flush();
		       ouputStream.close();
		   }catch (Exception e){
		       System.err.println("������������");
		   }

		}

		/**
		 * @return void
		 * @Author ahy
		 * @Description ������ݾ��У��˷���Ϊ���ξ���ÿ����������
		 * @Date 15:52 2020.12.15
		 * @Param [xtable]
		 */
		public void centerTableContent(XWPFTable xtable) {
		    for (int i = 0; i < xtable.getNumberOfRows(); i++) {
		        for (int j = 0; j < 4; j++) {
		            XWPFTableCell cell = xtable.getRow(i).getCell(j);
		            CTTc cttc = cell.getCTTc();
		            CTTcPr ctPr = cttc.addNewTcPr();
		            ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
		            cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
		        }
		    }
		}

}
