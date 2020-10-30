package org.company.protocol.crane;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.property.ReportPropertyMessage;

import java.util.HashMap;
import java.util.Map;

@Slf4j(topic = "system.crane")
public class UrlTopicMessageCodec {

    protected DeviceMessage doDecode(String deviceId, String url, JSONObject payload)
    {
        DeviceMessage message = null;

        if (url.startsWith("/rundata"))
        {
            message = handleRunData(payload);
        }
        else if (url.startsWith("/basicdata"))
        {
            message = handleBasicData(payload);
        }
        else if (url.startsWith("/alarmdata"))
        {
            message = handleAlarmData(payload);
        }
        else
        {
            log.error("no match url. url = {}, data = {}", url, payload.toJSONString());
            return message;
        }

        return message;
    }

//    private ReportPropertyMessage handleRunData(JSONObject json) {
//        JSONObject dataOBj = json.getJSONObject("data");
//        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
//        reportPropertyMessage.setProperties(new HashMap<>());
//        reportPropertyMessage.getProperties().put("leftobstaclealarm",dataOBj.getString("leftobstaclealarm"));
//        reportPropertyMessage.getProperties().put("highlimitalarm",dataOBj.getString("highlimitalarm"));
//        reportPropertyMessage.getProperties().put("devicestatus",dataOBj.getString("devicestatus"));
//        reportPropertyMessage.getProperties().put("farobstaclealarm",dataOBj.getString("farobstaclealarm"));
//        reportPropertyMessage.getProperties().put("fingerprintfault",dataOBj.getString("fingerprintfault"));
//        reportPropertyMessage.getProperties().put("windalarm",dataOBj.getString("windalarm"));
//        reportPropertyMessage.getProperties().put("dipyalarm",dataOBj.getString("dipyalarm"));
//        reportPropertyMessage.getProperties().put("workcycleindex",dataOBj.getString("workcycleindex"));
//        reportPropertyMessage.getProperties().put("rightlimitalarm",dataOBj.getString("rightlimitalarm"));
//        reportPropertyMessage.getProperties().put("pitch",dataOBj.getString("pitch"));
//        reportPropertyMessage.getProperties().put("height",dataOBj.getString("height"));
//        reportPropertyMessage.getProperties().put("pmonline",dataOBj.getString("pmonline"));
//        reportPropertyMessage.getProperties().put("rightforbiddenlinealarm",dataOBj.getString("rightforbiddenlinealarm"));
//        reportPropertyMessage.getProperties().put("weight",dataOBj.getString("weight"));
//        reportPropertyMessage.getProperties().put("torquerate",dataOBj.getString("torquerate"));
//        reportPropertyMessage.getProperties().put("volume",dataOBj.getString("volume"));
//        reportPropertyMessage.getProperties().put("windsensorfault",dataOBj.getString("windsensorfault"));
//        reportPropertyMessage.getProperties().put("pm25",dataOBj.getString("pm25"));
//        reportPropertyMessage.getProperties().put("nearforbiddenlinealarm",dataOBj.getString("nearforbiddenlinealarm"));
//        reportPropertyMessage.getProperties().put("nearobstaclealarm",dataOBj.getString("nearobstaclealarm"));
//        reportPropertyMessage.getProperties().put("acfault",dataOBj.getString("acfault"));
//        reportPropertyMessage.getProperties().put("lowlimitalarm",dataOBj.getString("lowlimitalarm"));
//        reportPropertyMessage.getProperties().put("rotationsensorfault",dataOBj.getString("rotationsensorfault"));
//        reportPropertyMessage.getProperties().put("dipxalarm",dataOBj.getString("dipxalarm"));
//        reportPropertyMessage.getProperties().put("leftlimitalarm",dataOBj.getString("leftlimitalarm"));
//        reportPropertyMessage.getProperties().put("leftforbiddenlinealarm",dataOBj.getString("leftforbiddenlinealarm"));
//        reportPropertyMessage.getProperties().put("controlstatus",dataOBj.getString("controlstatus"));
//        reportPropertyMessage.getProperties().put("roll",dataOBj.getString("roll"));
//        reportPropertyMessage.getProperties().put("range",dataOBj.getString("range"));
//        reportPropertyMessage.getProperties().put("heightsensorfault",dataOBj.getString("heightsensorfault"));
//        reportPropertyMessage.getProperties().put("realtimeid",dataOBj.getString("realtimeid"));
//        reportPropertyMessage.getProperties().put("weightsensorfault",dataOBj.getString("weightsensorfault"));
//        reportPropertyMessage.getProperties().put("rangesensorfault",dataOBj.getString("rangesensorfault"));
//        reportPropertyMessage.getProperties().put("rightobstaclealarm",dataOBj.getString("rightobstaclealarm"));
//        reportPropertyMessage.getProperties().put("hitchstatus",dataOBj.getString("hitchstatus"));
//        reportPropertyMessage.getProperties().put("nearlimitalarm",dataOBj.getString("nearlimitalarm"));
//        reportPropertyMessage.getProperties().put("farforbiddenlinealarm",dataOBj.getString("farforbiddenlinealarm"));
//        reportPropertyMessage.getProperties().put("rotation",dataOBj.getString("rotation"));
//        reportPropertyMessage.getProperties().put("pm10",dataOBj.getString("pm10"));
//        reportPropertyMessage.getProperties().put("updownstatus",dataOBj.getString("updownstatus"));
//        reportPropertyMessage.getProperties().put("lowobstaclealarm",dataOBj.getString("lowobstaclealarm"));
//        reportPropertyMessage.getProperties().put("obstaclestatus",dataOBj.getString("obstaclestatus"));
//        reportPropertyMessage.getProperties().put("torquealarm",dataOBj.getString("torquealarm"));
//        reportPropertyMessage.getProperties().put("forbidstatus",dataOBj.getString("forbidstatus"));
//        reportPropertyMessage.getProperties().put("imei",dataOBj.getString("imei").toUpperCase());
//        reportPropertyMessage.getProperties().put("windspeed",dataOBj.getString("windspeed"));
//        reportPropertyMessage.getProperties().put("time",dataOBj.getString("time"));
//        reportPropertyMessage.getProperties().put("farlimitalarm",dataOBj.getString("farlimitalarm"));
//        reportPropertyMessage.setDeviceId(dataOBj.getString("imei").toUpperCase());
//        return reportPropertyMessage;
//    }

//    private ReportPropertyMessage handleBasicData(JSONObject json)
//    {
//        JSONObject dataObj= json.getJSONObject("data");
//        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
//        reportPropertyMessage.setProperties(new HashMap<>());
//        if ( null == dataObj.getString("towercount")) {
//            reportPropertyMessage.getProperties().put("Imei",dataObj.getString("imei").toUpperCase());
//            reportPropertyMessage.getProperties().put("Time",dataObj.getString("time"));
//            reportPropertyMessage.getProperties().put("Name",dataObj.getString("name"));
//            reportPropertyMessage.getProperties().put("RopeRate",dataObj.getString("roperate"));
//            reportPropertyMessage.getProperties().put("RelatedX",dataObj.getString("relatedx"));
//            reportPropertyMessage.getProperties().put("RelatedY",dataObj.getString("relatedy"));
//            reportPropertyMessage.getProperties().put("GpsX",dataObj.getString("gpsx"));
//            reportPropertyMessage.getProperties().put("GpsY",dataObj.getString("gpsy"));
//            reportPropertyMessage.getProperties().put("FirstHeight",dataObj.getString("firstheight"));
//            reportPropertyMessage.getProperties().put("NodeHeight",dataObj.getString("nodeheight"));
//            reportPropertyMessage.getProperties().put("NodeCount",dataObj.getString("nodecount"));
//            reportPropertyMessage.getProperties().put("BodyHeight",dataObj.getString("bodyheight"));
//            reportPropertyMessage.getProperties().put("TopHeight",dataObj.getString("topheight"));
//            reportPropertyMessage.getProperties().put("ForeLength",dataObj.getString("forelength"));
//            reportPropertyMessage.getProperties().put("RearLength",dataObj.getString("rearlength"));
//            reportPropertyMessage.getProperties().put("LeftLimit",dataObj.getString("leftlimit"));
//            reportPropertyMessage.getProperties().put("RightLimit",dataObj.getString("rightlimit"));
//            reportPropertyMessage.getProperties().put("NearLimit",dataObj.getString("nearlimit"));
//            reportPropertyMessage.getProperties().put("FarLimit",dataObj.getString("farlimit"));
//            reportPropertyMessage.getProperties().put("HighLimit",dataObj.getString("highlimit"));
//            reportPropertyMessage.getProperties().put("TorqueLimit",dataObj.getString("torquelimit"));
//            reportPropertyMessage.getProperties().put("WindLimit",dataObj.getString("windlimit"));
//            reportPropertyMessage.getProperties().put("PitchLimit",dataObj.getString("pitchlimit"));
//            reportPropertyMessage.getProperties().put("RollLimit",dataObj.getString("rolllimit"));
//            reportPropertyMessage.getProperties().put("TelNo", dataObj.getString("telno"));
//        }
//        else
//        {
//            reportPropertyMessage.getProperties().put("towercount", dataObj.getString("towercount"));
//            reportPropertyMessage.getProperties().put("groupid", dataObj.getString("groupid"));
//            reportPropertyMessage.getProperties().put("imei", dataObj.getString("imei").toUpperCase());
//            reportPropertyMessage.getProperties().put("time", dataObj.getString("time"));
//            reportPropertyMessage.getProperties().put("groupdata", dataObj.getString("groupdata"));
//        }
//
//        reportPropertyMessage.setDeviceId(json.getString("imei"));
//        return reportPropertyMessage;
//    }

//    private ReportPropertyMessage handleAlarmData(JSONObject json)
//    {
//        JSONObject dataObj = json.getJSONObject("data");
//        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
//        reportPropertyMessage.setProperties(new HashMap<>());
//        reportPropertyMessage.setDeviceId(json.getString("imei"));
//        reportPropertyMessage.getProperties().put("dipy", dataObj.getString("dipy"));
//        reportPropertyMessage.getProperties().put("margin", dataObj.getString("margin"));
//        reportPropertyMessage.getProperties().put("dipx", dataObj.getString("dipx"));
//        reportPropertyMessage.getProperties().put("windvelocity", dataObj.getString("windvelocity"));
//        reportPropertyMessage.getProperties().put("action_type", dataObj.getString("action_type"));
//        reportPropertyMessage.getProperties().put("rotation", dataObj.getString("rotation"));
//        reportPropertyMessage.getProperties().put("torque", dataObj.getString("torque"));
//        reportPropertyMessage.getProperties().put("alarm_level", dataObj.getString("alarm_level"));
//        reportPropertyMessage.getProperties().put("workcyclecode", dataObj.getString("workcyclecode"));
//        reportPropertyMessage.getProperties().put("hangweight", dataObj.getString("hangweight"));
//        reportPropertyMessage.getProperties().put("alarm_type", dataObj.getString("alarm_type"));
//        reportPropertyMessage.getProperties().put("time", dataObj.getString("time"));
//        reportPropertyMessage.getProperties().put("height", dataObj.getString("height"));
//        return reportPropertyMessage;
//    }

    private ReportPropertyMessage handleAlarmData(JSONObject json)
    {
        JSONObject dataOBj = json.getJSONObject("data");
        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
        Map<String, Object> map = new HashMap<>();
        reportPropertyMessage.setProperties(new HashMap<>());
        reportPropertyMessage.setDeviceId(json.getString("imei"));
        map.put("dipy", dataOBj.getString("dipy"));
        map.put("margin", dataOBj.getString("margin"));
        map.put("dipx", dataOBj.getString("dipx"));
        map.put("windvelocity", dataOBj.getString("windvelocity"));
        map.put("action_type", dataOBj.getString("action_type"));
        map.put("rotation", dataOBj.getString("rotation"));
        map.put("torque", dataOBj.getString("torque"));
        map.put("alarm_level", dataOBj.getString("alarm_level"));
        map.put("workcyclecode", dataOBj.getString("workcyclecode"));
        map.put("hangweight", dataOBj.getString("hangweight"));
        map.put("alarm_type", dataOBj.getString("alarm_type"));
        map.put("time", dataOBj.getString("time"));
        map.put("height", dataOBj.getString("height"));
        reportPropertyMessage.getProperties().put("alarmdata", map);
        return reportPropertyMessage;
    }

    private ReportPropertyMessage handleBasicData(JSONObject json)
    {
        JSONObject dataOBj = json.getJSONObject("data");
        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
        Map<String, Object> mapBasic1 = new HashMap<>();
        Map<String, Object> mapBasic2 = new HashMap<>();
        reportPropertyMessage.setProperties(new HashMap<>());
        if ( null == dataOBj.getString("towercount"))
        {
            mapBasic1.put("Imei",dataOBj.getString("imei").toUpperCase());
            mapBasic1.put("Time",dataOBj.getString("time"));
            mapBasic1.put("Name",dataOBj.getString("name"));
            mapBasic1.put("RopeRate",dataOBj.getString("roperate"));
            mapBasic1.put("RelatedX",dataOBj.getString("relatedx"));
            mapBasic1.put("RelatedY",dataOBj.getString("relatedy"));
            mapBasic1.put("GpsX",dataOBj.getString("gpsx"));
            mapBasic1.put("GpsY",dataOBj.getString("gpsy"));
            mapBasic1.put("FirstHeight",dataOBj.getString("firstheight"));
            mapBasic1.put("NodeHeight",dataOBj.getString("nodeheight"));
            mapBasic1.put("NodeCount",dataOBj.getString("nodecount"));
            mapBasic1.put("BodyHeight",dataOBj.getString("bodyheight"));
            mapBasic1.put("TopHeight",dataOBj.getString("topheight"));
            mapBasic1.put("ForeLength",dataOBj.getString("forelength"));
            mapBasic1.put("RearLength",dataOBj.getString("rearlength"));
            mapBasic1.put("LeftLimit",dataOBj.getString("leftlimit"));
            mapBasic1.put("RightLimit",dataOBj.getString("rightlimit"));
            mapBasic1.put("NearLimit",dataOBj.getString("nearlimit"));
            mapBasic1.put("FarLimit",dataOBj.getString("farlimit"));
            mapBasic1.put("HighLimit",dataOBj.getString("highlimit"));
            mapBasic1.put("TorqueLimit",dataOBj.getString("torquelimit"));
            mapBasic1.put("WindLimit",dataOBj.getString("windlimit"));
            mapBasic1.put("PitchLimit",dataOBj.getString("pitchlimit"));
            mapBasic1.put("RollLimit",dataOBj.getString("rolllimit"));
            mapBasic1.put("TelNo", dataOBj.getString("telno"));
            reportPropertyMessage.getProperties().put("basicdata", mapBasic1);
        }
        else
        {
            mapBasic2.put("towercount", dataOBj.getString("towercount"));
            mapBasic2.put("groupid", dataOBj.getString("groupid"));
            mapBasic2.put("Imei", dataOBj.getString("imei").toUpperCase());
            mapBasic2.put("Time", dataOBj.getString("time"));
            mapBasic2.put("groupdata", dataOBj.getString("groupdata"));
            reportPropertyMessage.getProperties().put("groupdata", mapBasic1);
        }

        reportPropertyMessage.setDeviceId(json.getString("imei"));
        return reportPropertyMessage;
    }

//    private ReportPropertyMessage handleRunData(JSONObject json) {
//        JSONObject dataOBj = json.getJSONObject("data");
//        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
//        Map<String, Object> map = new HashMap<>();
//        reportPropertyMessage.setProperties(new HashMap<>());
//        map.put("leftobstaclealarm",dataOBj.getString("leftobstaclealarm"));
//        map.put("highlimitalarm",dataOBj.getString("highlimitalarm"));
//        map.put("devicestatus",dataOBj.getString("devicestatus"));
//        map.put("farobstaclealarm",dataOBj.getString("farobstaclealarm"));
//        map.put("fingerprintfault",dataOBj.getString("fingerprintfault"));
//        map.put("windalarm",dataOBj.getString("windalarm"));
//        map.put("dipyalarm",dataOBj.getString("dipyalarm"));
//        map.put("workcycleindex",dataOBj.getString("workcycleindex"));
//        map.put("rightlimitalarm",dataOBj.getString("rightlimitalarm"));
//        map.put("pitch",dataOBj.getString("pitch"));
//        map.put("height",dataOBj.getString("height"));
//        map.put("pmonline",dataOBj.getString("pmonline"));
//        map.put("rightforbiddenlinealarm",dataOBj.getString("rightforbiddenlinealarm"));
//        map.put("weight",dataOBj.getString("weight"));
//        map.put("torquerate",dataOBj.getString("torquerate"));
//        map.put("volume",dataOBj.getString("volume"));
//        map.put("windsensorfault",dataOBj.getString("windsensorfault"));
//        map.put("pm25",dataOBj.getString("pm25"));
//        map.put("nearforbiddenlinealarm",dataOBj.getString("nearforbiddenlinealarm"));
//        map.put("nearobstaclealarm",dataOBj.getString("nearobstaclealarm"));
//        map.put("acfault",dataOBj.getString("acfault"));
//        map.put("lowlimitalarm",dataOBj.getString("lowlimitalarm"));
//        map.put("rotationsensorfault",dataOBj.getString("rotationsensorfault"));
//        map.put("dipxalarm",dataOBj.getString("dipxalarm"));
//        map.put("leftlimitalarm",dataOBj.getString("leftlimitalarm"));
//        map.put("leftforbiddenlinealarm",dataOBj.getString("leftforbiddenlinealarm"));
//        map.put("controlstatus",dataOBj.getString("controlstatus"));
//        map.put("roll",dataOBj.getString("roll"));
//        map.put("range",dataOBj.getString("range"));
//        map.put("heightsensorfault",dataOBj.getString("heightsensorfault"));
//        map.put("realtimeid",dataOBj.getString("realtimeid"));
//        map.put("weightsensorfault",dataOBj.getString("weightsensorfault"));
//        map.put("rangesensorfault",dataOBj.getString("rangesensorfault"));
//        map.put("rightobstaclealarm",dataOBj.getString("rightobstaclealarm"));
//        map.put("hitchstatus",dataOBj.getString("hitchstatus"));
//        map.put("nearlimitalarm",dataOBj.getString("nearlimitalarm"));
//        map.put("farforbiddenlinealarm",dataOBj.getString("farforbiddenlinealarm"));
//        map.put("rotation",dataOBj.getString("rotation"));
//        map.put("pm10",dataOBj.getString("pm10"));
//        map.put("updownstatus",dataOBj.getString("updownstatus"));
//        map.put("lowobstaclealarm",dataOBj.getString("lowobstaclealarm"));
//        map.put("obstaclestatus",dataOBj.getString("obstaclestatus"));
//        map.put("torquealarm",dataOBj.getString("torquealarm"));
//        map.put("forbidstatus",dataOBj.getString("forbidstatus"));
//        map.put("imei",dataOBj.getString("imei").toUpperCase());
//        map.put("windspeed",dataOBj.getString("windspeed"));
//        map.put("time",dataOBj.getString("time"));
//        map.put("farlimitalarm",dataOBj.getString("farlimitalarm"));
//        reportPropertyMessage.getProperties().put("rundata", map);
//        reportPropertyMessage.setDeviceId(json.getString("imei"));
//        return reportPropertyMessage;
//    }

    private ReportPropertyMessage handleRunData(JSONObject json) {
        JSONObject dataOBj = json.getJSONObject("data");
        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
        Map<String, Object> mapReal = new HashMap<>();
        Map<String, Object> mapAlarm = new HashMap<>();
        Map<String, Object> mapPm = new HashMap<>();
        reportPropertyMessage.setProperties(new HashMap<>());
        mapAlarm.put("leftobstaclealarm",dataOBj.getString("leftobstaclealarm"));
        mapAlarm.put("highlimitalarm",dataOBj.getString("highlimitalarm"));
        mapReal.put("devicestatus",dataOBj.getString("devicestatus"));
        mapAlarm.put("farobstaclealarm",dataOBj.getString("farobstaclealarm"));
        mapAlarm.put("fingerprintfault",dataOBj.getString("fingerprintfault"));
        mapAlarm.put("windalarm",dataOBj.getString("windalarm"));
        mapAlarm.put("dipyalarm",dataOBj.getString("dipyalarm"));
        mapReal.put("workcycleindex",dataOBj.getString("workcycleindex"));
        mapAlarm.put("rightlimitalarm",dataOBj.getString("rightlimitalarm"));
        mapReal.put("pitch",dataOBj.getString("pitch"));
        mapReal.put("height",dataOBj.getString("height"));
        mapPm.put("pmonline",dataOBj.getString("pmonline"));
        mapAlarm.put("rightforbiddenlinealarm",dataOBj.getString("rightforbiddenlinealarm"));
        mapReal.put("weight",dataOBj.getString("weight"));
        mapReal.put("torquerate",dataOBj.getString("torquerate"));
        mapReal.put("volume",dataOBj.getString("volume"));
        mapAlarm.put("windsensorfault",dataOBj.getString("windsensorfault"));
        mapPm.put("pm25",dataOBj.getString("pm25"));
        mapAlarm.put("nearforbiddenlinealarm",dataOBj.getString("nearforbiddenlinealarm"));
        mapAlarm.put("nearobstaclealarm",dataOBj.getString("nearobstaclealarm"));
        mapAlarm.put("acfault",dataOBj.getString("acfault"));
        mapAlarm.put("lowlimitalarm",dataOBj.getString("lowlimitalarm"));
        mapAlarm.put("rotationsensorfault",dataOBj.getString("rotationsensorfault"));
        mapAlarm.put("dipxalarm",dataOBj.getString("dipxalarm"));
        mapAlarm.put("leftlimitalarm",dataOBj.getString("leftlimitalarm"));
        mapAlarm.put("leftforbiddenlinealarm",dataOBj.getString("leftforbiddenlinealarm"));
        mapReal.put("controlstatus",dataOBj.getString("controlstatus"));
        mapReal.put("roll",dataOBj.getString("roll"));
        mapReal.put("range",dataOBj.getString("range"));
        mapAlarm.put("heightsensorfault",dataOBj.getString("heightsensorfault"));
        mapReal.put("realtimeid",dataOBj.getString("realtimeid"));
        mapAlarm.put("weightsensorfault",dataOBj.getString("weightsensorfault"));
        mapAlarm.put("rangesensorfault",dataOBj.getString("rangesensorfault"));
        mapAlarm.put("rightobstaclealarm",dataOBj.getString("rightobstaclealarm"));
        mapAlarm.put("hitchstatus",dataOBj.getString("hitchstatus"));
        mapAlarm.put("nearlimitalarm",dataOBj.getString("nearlimitalarm"));
        mapAlarm.put("farforbiddenlinealarm",dataOBj.getString("farforbiddenlinealarm"));
        mapReal.put("rotation",dataOBj.getString("rotation"));
        mapPm.put("pm10",dataOBj.getString("pm10"));
        mapReal.put("updownstatus",dataOBj.getString("updownstatus"));
        mapAlarm.put("lowobstaclealarm",dataOBj.getString("lowobstaclealarm"));
        mapReal.put("obstaclestatus",dataOBj.getString("obstaclestatus"));
        mapAlarm.put("torquealarm",dataOBj.getString("torquealarm"));
        mapReal.put("forbidstatus",dataOBj.getString("forbidstatus"));
        mapReal.put("imei",dataOBj.getString("imei").toUpperCase());
        mapAlarm.put("imei",dataOBj.getString("imei").toUpperCase());
        mapPm.put("imei",dataOBj.getString("imei").toUpperCase());
        mapReal.put("windspeed",dataOBj.getString("windspeed"));
        mapPm.put("windspeed",dataOBj.getString("windspeed"));
        mapReal.put("time",dataOBj.getString("time"));
        mapAlarm.put("time",dataOBj.getString("time"));
        mapPm.put("time",dataOBj.getString("time"));
        mapAlarm.put("farlimitalarm",dataOBj.getString("farlimitalarm"));
        reportPropertyMessage.getProperties().put("realtime", mapReal);
        reportPropertyMessage.getProperties().put("alarm", mapAlarm);
        reportPropertyMessage.getProperties().put("pm", mapPm);
        reportPropertyMessage.setDeviceId(json.getString("imei"));
        return reportPropertyMessage;
    }

}
