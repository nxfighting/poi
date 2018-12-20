package com.vict;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vict.poi.client.PoiExportManager;
import com.vict.poi.client.PoiWorkbookManager;
import com.vict.poi.domain.ExcelDataConfig;
import com.vict.poi.domain.ExcelExportDataConfig;
import com.vict.poi.domain.ResolveExcelDTO;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    //@Test
    public  void testA() throws IOException {
        String filePath = "d:/batterypack_repair_template.xlsx";
        InputStream stream = new FileInputStream(new File(filePath));
        List<ExcelDataConfig> configList = PackRepairOrderConst.getBatteryRepairXlsConfig();
        ResolveExcelDTO dto = PoiWorkbookManager.read(stream, "xlsx", configList);
        // filePath = "d:/A.xlsx";
        // stream = new FileInputStream(new File(filePath));
        dto = PoiWorkbookManager.readWithCheck(stream, "xlsx", configList);
    }
    @Test
    public  void export() throws IOException {
        List<ExcelExportDataConfig> configList = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            ExcelExportDataConfig exportDataConfig = new ExcelExportDataConfig();
            exportDataConfig.setSheetName("测试"+j);
            Map<String,String> map  = new LinkedHashMap<>();
            map.put("id","ID");
            map.put("name","Name");
            map.put("key","Security Key");
            map.put("pass","Password");
            exportDataConfig.setHeadsMap(map);
            JSONArray array = new JSONArray();
            for (int i = 0; i < 10000; i++) {
                JSONObject json = new JSONObject();
                json.put("id",i);
                json.put("name", UUID.randomUUID().toString());
                json.put("key", UUID.randomUUID().toString());
                json.put("pass", UUID.randomUUID().toString());
                array.add(json);
            }
            exportDataConfig.setExportDataArray(array);

            configList.add(exportDataConfig);
        }

        Workbook workbook = PoiExportManager.exportExcelX(configList,null,40);

        OutputStream outputStream =new FileOutputStream(new File(String.format("D:/%s.xlsx",new Date().getTime())));
        workbook.write(outputStream);
    }
}
