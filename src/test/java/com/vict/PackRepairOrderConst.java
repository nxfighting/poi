package com.vict;
import com.vict.poi.domain.DataFormat;
import com.vict.poi.domain.ExcelDataConfig;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gf
 *
 */
public class PackRepairOrderConst {



	private static String [] sheetNames =null;
    private static Object [] sheetClazzRefs =null;
	/**
	  * 维修单号	电池包编码	VIN码	车牌号	
	  * 维修来源	维修网点信息	维修类型	维修时间	
	  * 更换前编码	更换后编码	去向企业名称	
	  * 去向企业统一社会信用代码	备注
	 */
	private static Map<String,String> headerMap =new LinkedHashMap<String,String>();
	private static Map<String, DataFormat> fmtMap = new LinkedHashMap<String,DataFormat>();
	private static Map<String,DataFormat> moduleFormatMap = null;
    private static Map<String,DataFormat> batteryFormatMap = null;
	private static String[][] headArray = new String[][] {
		new String[] {"id","维修单号"},
		new String[] {"batteryPackageCode","电池包编码"},
		new String[] {"vin","VIN码"},
		new String[] {"carNo","车牌号"},
		new String[] {"repairFromDisplay","维修来源"},
		new String[] {"repairInternetInfo","维修网点信息"},
		new String[] {"repairTypeDisplay","维修类型"},
		new String[] {"repairTime","维修时间"},
		new String[] {"beforeRepairCode","更换前编码"},
		new String[] {"afterRepairCode","更换后编码"},
		new String[] {"repairToCompanyName","去向企业名称"},
		new String[] {"repairToCompanyCode","去向企业统一社会信用代码"},
		new String[] {"comments","备注"},
		
	};
	static {
		//packRepairOrderConst = new PackRepairOrderConst();
		sheetNames = new String[] {
			"电池维修信息","电池模块","电池单体"	
		};
		sheetClazzRefs= new Object[]{
            new BatteryPackageRepairSheetDTO(),
            new EnModuleDTO(),
            new EnBatteryDTO()
        };
		headerMap.put("", "");
		fmtMap.put("维修单号", new DataFormat("repairOrderNo", "维修单号", true, "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{10,50}$", "维修单号格式错误，维修单号由字符和数字组成！"));
		fmtMap.put("电池包编码", new DataFormat("batteryPackageCode", "电池包编码", true, "^[0-9A-Z]{24}$", "电池包编码格式有误,电池包编码由24数字字母组合而成！"));
		fmtMap.put("VIN码", new DataFormat("vin", "VIN码", false, "^.{1,50}$", "长度，不得大于50字符！"));
		fmtMap.put("车牌号", new DataFormat("carNo", "车牌号", false, "^.{1,50}$", "长度，不得大于50字符！"));
		fmtMap.put("维修来源", new DataFormat("repairFrom", "维修来源", true, "^[0-9]+(.[0-9]{1,4})?$", "维修来源只允许输入数字代码！"));
		fmtMap.put("维修网点信息", new DataFormat("repairInternetInfo", "维修网点信息", true, "^.{1,100}$", "长度，不得大于100字符！"));
		fmtMap.put("维修类型", new DataFormat("repairType", "维修类型", true, "^[0-9]+(.[0-9]{1,4})?$", "维修类型只允许输入数字代码！"));
		fmtMap.put("维修时间", new DataFormat("repairTime", "维修时间", true, null, ""));
		fmtMap.put("更换前编码", new DataFormat("beforeRepairCode", "更换前编码", true, "^.{1,100}$", ""));
		fmtMap.put("更换后编码", new DataFormat("afterRepairCode", "更换后编码", true, "^.{1,100}$", "长度，不得大于100字符！"));
		fmtMap.put("去向企业名称", new DataFormat("repairToCompanyName", "去向企业名称", true, "^.{1,100}$", "长度，不得大于100字符！"));
		fmtMap.put("去向企业统一社会信用代码", new DataFormat("repairToCompanyCode", "去向企业统一社会信用代码", true, "^.{1,100}$", "长度，不得大于100字符！"));
		fmtMap.put("备注", new DataFormat("comments", "备注", false, null, ""));
		
	}
    static {
        moduleFormatMap = new LinkedHashMap<>();
        moduleFormatMap.put("所属电池包编码", new DataFormat("batteryPackCode", "所属电池包编码", true, "^[0-9A-Z]{24}$", "电池包编码格式有误"));
        moduleFormatMap.put("模块编码", new DataFormat("moduleCode", "模块编码", true, "^[0-9A-Z]{24}$", "模块编码格式有误"));
        moduleFormatMap.put("额定能量(kWh)", new DataFormat("ratedEnergy", "额定能量(kWh)", true, "^[0-9]+(.[0-9]{1,4})?$", "额定能量有误"));
        moduleFormatMap.put("冷却方式", new DataFormat("coolMode", "冷却方式", true, null, null));
        moduleFormatMap.put("所含电芯数", new DataFormat("monomerNum", "所含电芯数", true, "^[0-9]+(.[0-9]{1,4})?$", "所含电芯数为数值"));
        moduleFormatMap.put("模块压差", new DataFormat("pressureDifference", "模块压差", true, "^[0-9]+(.[0-9]{1,4})?$", "模块压差有误"));
        moduleFormatMap.put("绝缘电阻(Ω)", new DataFormat("insulationResistance", "绝缘电阻(Ω)", true, "^[0-9]+(.[0-9]{1,4})?$", "绝缘电阻有误"));
        moduleFormatMap.put("循环寿命(次)", new DataFormat("cycleLife", "循环寿命(次)", true, "^[0-9]+(.[0-9]{1,4})?$", "循环寿命有误"));
        moduleFormatMap.put("额定容量(Ah)", new DataFormat("ratedCapacity", "额定容量(Ah)", true, "^[0-9]+(.[0-9]{1,4})?$", "额定容量有误"));
        moduleFormatMap.put("标称电压(V)", new DataFormat("nominalVoltage", "标称电压(V)", true, "^[0-9]+(.[0-9]{1,4})?$", "标称电压有误"));
        moduleFormatMap.put("模块内阻DCR/ACR", new DataFormat("dcrAcr", "模块内阻DCR/ACR", true, "^[0-9]+(.[0-9]{1,4})?$", "模块内阻DCR/ACR有误"));
        moduleFormatMap.put("额定质量(kg)", new DataFormat("ratedQuality", "额定质量(kg)", true, "^[0-9]+(.[0-9]{1,4})?$", "额定质量有误"));
        moduleFormatMap.put("单体串并联方式", new DataFormat("monomerMode", "单体串并联方式", true, null, ""));
        moduleFormatMap.put("尺寸(mm)", new DataFormat("size", "尺寸(mm)", true, null, ""));
        moduleFormatMap.put("型号", new DataFormat("model", "型号", true, null, ""));
        moduleFormatMap.put("Pack位置号", new DataFormat("position", "Pack位置号", true, null, ""));
    }
    static {
        batteryFormatMap = new LinkedHashMap<>();
        batteryFormatMap.put("所属电池包编码", new DataFormat("batteryPackCode", "所属电池包编码", true, "^[0-9A-Z]{24}$", "所属电池包编码有误"));
        batteryFormatMap.put("所属电池模块编码", new DataFormat("moduleCode", "所属电池模块编码", true, "^[0-9A-Z]{24}$", "所属模块编码有误"));
        batteryFormatMap.put("单体电池编码", new DataFormat("batteryCode", "单体电池编码", true, "^[0-9A-Z]{24}$", "单体电池编码有误"));
        batteryFormatMap.put("电解液类型", new DataFormat("electrolyteType", "电解液类型", true, null, ""));
        batteryFormatMap.put("型号", new DataFormat("model", "型号", true, null, ""));
        batteryFormatMap.put("模块位置号", new DataFormat("locationNum", "模组位置号", true, null, ""));
        batteryFormatMap.put("外形",new DataFormat("appearance", "外形", true, null, ""));
        batteryFormatMap.put("尺寸(mm)", new DataFormat("size", "尺寸(mm)", true, null, ""));
        batteryFormatMap.put("额定容量(Ah)", new DataFormat("ratedCapacity", "额定容量(Ah)", true, "^[0-9]+(.[0-9]{1,4})?$", "额定容量有误"));
        batteryFormatMap.put("标称电压(V)", new DataFormat("nominalVoltage", "标称电压(V)", true, "^[0-9]+(.[0-9]{1,4})?$", "标称电压有误"));
        batteryFormatMap.put("额定质量(kg)", new DataFormat("ratedQuality", "额定质量(kg)", true, "^[0-9]+(.[0-9]{1,4})?$", "额定质量有误"));
        batteryFormatMap.put("额定能量(kWh)", new DataFormat("ratedEnergy", "额定能量(kWh)", true, "^[0-9]+(.[0-9]{1,4})?$", "额定能量有误"));
        batteryFormatMap.put("单体内阻", new DataFormat("internalResistance", "单体内阻", true, "^[0-9]+(.[0-9]{1,4})?$", "单体内阻有误"));
        batteryFormatMap.put("循环寿命(次)", new DataFormat("cycleLife", "循环寿命(次)", true, "^[0-9]+(.[0-9]{1})?$", "循环寿命有误"));
        batteryFormatMap.put("正极材料", new DataFormat("cathodeMaterial", "正极材料", true, null, ""));
        batteryFormatMap.put("负极材料", new DataFormat("negativeMaterial", "负极材料", true, null, ""));
        batteryFormatMap.put("隔膜类型", new DataFormat("septumType", "隔膜类型", true, null, ""));
    }
    /**
     * @Author gf.zhou
     * @Description  电池包导入模板基本配置信息
     * @Date  2018/11/21
     * @Param []
     * @return java.util.List<com.aiways.energyassets.service.util.ExcelDataConfig>
     */
    public static List<ExcelDataConfig> getBatteryRepairXlsConfig(){
        List<ExcelDataConfig> configList  = new ArrayList<ExcelDataConfig>();
        int index=0;
        Map[] maps = new Map[]{fmtMap,moduleFormatMap,batteryFormatMap};
        int [] startNumber =new int[]{1,0,0};
        for(String sheet:sheetNames) {
            ExcelDataConfig config = new ExcelDataConfig(
                sheet, sheetClazzRefs[index], maps[index]
            );
            config.setStartRowNum(startNumber[index]);
            configList.add(config);
            index++;
        }
        return configList;
    }
	public static String[] getSheetNames() {
		return sheetNames;
	}
	public static Map<String, String> getHeaderMap() {
		if(headerMap.size()==0) {
			for(String[] headerRef:headArray) {
				headerMap.put(headerRef[0], headerRef[1]);
			}
		}
		return headerMap;
	}
	public static Map<String, DataFormat> getFmtMap() {
		return fmtMap;
	}
	
}
