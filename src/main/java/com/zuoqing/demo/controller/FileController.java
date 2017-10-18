package com.zuoqing.demo.controller;

import com.zuoqing.demo.entity.Girl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "file")
public class FileController {

    @RequestMapping(value = "excel")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*HttpSession session = request.getSession();
        ExamCountPJ examCountPJ = (ExamCountPJ)session.getAttribute("examCountPJ");
        examCountPJ.setDownloadExcel(true);*/
        String fileName = "";
       /* if(examCountPJ.countAVG){
            fileName = "平均分成绩单-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }else{
            fileName = "成绩单-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }*/

        fileName += "平均分.xls";

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("成绩单");
        HSSFRow header = sheet.createRow(0);
        HSSFCell cell = header.createCell(0);
        cell.setCellValue("考生ID");
        cell = header.createCell(1);
        cell.setCellValue("考生姓名");
        cell = header.createCell(2);

        List<Girl> list = new ArrayList<>();
        Girl girl111 = new Girl("A",111);
        list.add(girl111);
        Girl girl1 = new Girl("B",222);
        list.add(girl1);
        Girl girl2 = new Girl("C",333);
        list.add(girl2);

        //平均分排序导出
            for(int i = 0; i < list.size(); i ++){
                Girl girl = list.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(girl.getAge());
                cell = row.createCell(1);
                cell.setCellValue(girl.getCupSize());
            }


        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859_1"));
        workbook.write(response.getOutputStream());
        workbook.close();

    }

}
