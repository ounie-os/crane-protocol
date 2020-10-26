package org.company.protocol.crane;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.property.ReportPropertyMessage;
import org.jetlinks.core.message.property.WritePropertyMessageReply;

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

    private ReportPropertyMessage handleRunData(JSONObject json) {
        JSONObject dataOBj = json.getJSONObject("data");
        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
        reportPropertyMessage.setProperties(new HashMap<>());
        reportPropertyMessage.getProperties().put("leftobstaclealarm",dataOBj.getString("leftobstaclealarm"));
        reportPropertyMessage.getProperties().put("highlimitalarm",dataOBj.getString("highlimitalarm"));
        reportPropertyMessage.getProperties().put("devicestatus",dataOBj.getString("devicestatus"));
        reportPropertyMessage.getProperties().put("farobstaclealarm",dataOBj.getString("farobstaclealarm"));
        reportPropertyMessage.getProperties().put("fingerprintfault",dataOBj.getString("fingerprintfault"));
        reportPropertyMessage.getProperties().put("windalarm",dataOBj.getString("windalarm"));
        reportPropertyMessage.getProperties().put("dipyalarm",dataOBj.getString("dipyalarm"));
        reportPropertyMessage.getProperties().put("workcycleindex",dataOBj.getString("workcycleindex"));
        reportPropertyMessage.getProperties().put("rightlimitalarm",dataOBj.getString("rightlimitalarm"));
        reportPropertyMessage.getProperties().put("pitch",dataOBj.getString("pitch"));
        reportPropertyMessage.getProperties().put("height",dataOBj.getString("height"));
        reportPropertyMessage.getProperties().put("pmonline",dataOBj.getString("pmonline"));
        reportPropertyMessage.getProperties().put("rightforbiddenlinealarm",dataOBj.getString("rightforbiddenlinealarm"));
        reportPropertyMessage.getProperties().put("weight",dataOBj.getString("weight"));
        reportPropertyMessage.getProperties().put("torquerate",dataOBj.getString("torquerate"));
        reportPropertyMessage.getProperties().put("volume",dataOBj.getString("volume"));
        reportPropertyMessage.getProperties().put("windsensorfault",dataOBj.getString("windsensorfault"));
        reportPropertyMessage.getProperties().put("pm25",dataOBj.getString("pm25"));
        reportPropertyMessage.getProperties().put("nearforbiddenlinealarm",dataOBj.getString("nearforbiddenlinealarm"));
        reportPropertyMessage.getProperties().put("nearobstaclealarm",dataOBj.getString("nearobstaclealarm"));
        reportPropertyMessage.getProperties().put("acfault",dataOBj.getString("acfault"));
        reportPropertyMessage.getProperties().put("lowlimitalarm",dataOBj.getString("lowlimitalarm"));
        reportPropertyMessage.getProperties().put("rotationsensorfault",dataOBj.getString("rotationsensorfault"));
        reportPropertyMessage.getProperties().put("dipxalarm",dataOBj.getString("dipxalarm"));
        reportPropertyMessage.getProperties().put("leftlimitalarm",dataOBj.getString("leftlimitalarm"));
        reportPropertyMessage.getProperties().put("leftforbiddenlinealarm",dataOBj.getString("leftforbiddenlinealarm"));
        reportPropertyMessage.getProperties().put("controlstatus",dataOBj.getString("controlstatus"));
        reportPropertyMessage.getProperties().put("roll",dataOBj.getString("roll"));
        reportPropertyMessage.getProperties().put("range",dataOBj.getString("range"));
        reportPropertyMessage.getProperties().put("heightsensorfault",dataOBj.getString("heightsensorfault"));
        reportPropertyMessage.getProperties().put("realtimeid",dataOBj.getString("realtimeid"));
        reportPropertyMessage.getProperties().put("weightsensorfault",dataOBj.getString("weightsensorfault"));
        reportPropertyMessage.getProperties().put("rangesensorfault",dataOBj.getString("rangesensorfault"));
        reportPropertyMessage.getProperties().put("rightobstaclealarm",dataOBj.getString("rightobstaclealarm"));
        reportPropertyMessage.getProperties().put("hitchstatus",dataOBj.getString("hitchstatus"));
        reportPropertyMessage.getProperties().put("nearlimitalarm",dataOBj.getString("nearlimitalarm"));
        reportPropertyMessage.getProperties().put("farforbiddenlinealarm",dataOBj.getString("farforbiddenlinealarm"));
        reportPropertyMessage.getProperties().put("rotation",dataOBj.getString("rotation"));
        reportPropertyMessage.getProperties().put("pm10",dataOBj.getString("pm10"));
        reportPropertyMessage.getProperties().put("updownstatus",dataOBj.getString("updownstatus"));
        reportPropertyMessage.getProperties().put("lowobstaclealarm",dataOBj.getString("lowobstaclealarm"));
        reportPropertyMessage.getProperties().put("obstaclestatus",dataOBj.getString("obstaclestatus"));
        reportPropertyMessage.getProperties().put("torquealarm",dataOBj.getString("torquealarm"));
        reportPropertyMessage.getProperties().put("forbidstatus",dataOBj.getString("forbidstatus"));
        reportPropertyMessage.getProperties().put("imei",dataOBj.getString("imei").toUpperCase());
        reportPropertyMessage.getProperties().put("windspeed",dataOBj.getString("windspeed"));
        reportPropertyMessage.getProperties().put("time",dataOBj.getString("time"));
        reportPropertyMessage.getProperties().put("farlimitalarm",dataOBj.getString("farlimitalarm"));
        reportPropertyMessage.setDeviceId(dataOBj.getString("imei").toUpperCase());
        return reportPropertyMessage;
    }

    private ReportPropertyMessage handleBasicData(JSONObject json)
    {
        JSONObject dataObj= json.getJSONObject("data");
        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
        reportPropertyMessage.setProperties(new HashMap<>());
        if ( null == dataObj.getString("towercount")) {
            reportPropertyMessage.getProperties().put("Imei",dataObj.getString("imei").toUpperCase());
            reportPropertyMessage.getProperties().put("Time",dataObj.getString("time"));
            reportPropertyMessage.getProperties().put("Name",dataObj.getString("name"));
            reportPropertyMessage.getProperties().put("RopeRate",dataObj.getString("roperate"));
            reportPropertyMessage.getProperties().put("RelatedX",dataObj.getString("relatedx"));
            reportPropertyMessage.getProperties().put("RelatedY",dataObj.getString("relatedy"));
            reportPropertyMessage.getProperties().put("GpsX",dataObj.getString("gpsx"));
            reportPropertyMessage.getProperties().put("GpsY",dataObj.getString("gpsy"));
            reportPropertyMessage.getProperties().put("FirstHeight",dataObj.getString("firstheight"));
            reportPropertyMessage.getProperties().put("NodeHeight",dataObj.getString("nodeheight"));
            reportPropertyMessage.getProperties().put("NodeCount",dataObj.getString("nodecount"));
            reportPropertyMessage.getProperties().put("BodyHeight",dataObj.getString("bodyheight"));
            reportPropertyMessage.getProperties().put("TopHeight",dataObj.getString("topheight"));
            reportPropertyMessage.getProperties().put("ForeLength",dataObj.getString("forelength"));
            reportPropertyMessage.getProperties().put("RearLength",dataObj.getString("rearlength"));
            reportPropertyMessage.getProperties().put("LeftLimit",dataObj.getString("leftlimit"));
            reportPropertyMessage.getProperties().put("RightLimit",dataObj.getString("rightlimit"));
            reportPropertyMessage.getProperties().put("NearLimit",dataObj.getString("nearlimit"));
            reportPropertyMessage.getProperties().put("FarLimit",dataObj.getString("farlimit"));
            reportPropertyMessage.getProperties().put("HighLimit",dataObj.getString("highlimit"));
            reportPropertyMessage.getProperties().put("TorqueLimit",dataObj.getString("torquelimit"));
            reportPropertyMessage.getProperties().put("WindLimit",dataObj.getString("windlimit"));
            reportPropertyMessage.getProperties().put("PitchLimit",dataObj.getString("pitchlimit"));
            reportPropertyMessage.getProperties().put("RollLimit",dataObj.getString("rolllimit"));
            reportPropertyMessage.getProperties().put("TelNo", dataObj.getString("telno"));
        }
        else
        {
            reportPropertyMessage.getProperties().put("towercount", dataObj.getString("towercount"));
            reportPropertyMessage.getProperties().put("groupid", dataObj.getString("groupid"));
            reportPropertyMessage.getProperties().put("imei", dataObj.getString("imei").toUpperCase());
            reportPropertyMessage.getProperties().put("time", dataObj.getString("time"));
            reportPropertyMessage.getProperties().put("groupdata", dataObj.getString("groupdata"));
        }

        reportPropertyMessage.setDeviceId(json.getString("imei"));
        return reportPropertyMessage;
    }

    private ReportPropertyMessage handleAlarmData(JSONObject json)
    {
        JSONObject dataObj = json.getJSONObject("data");
        ReportPropertyMessage reportPropertyMessage = ReportPropertyMessage.create();
        reportPropertyMessage.setProperties(new HashMap<>());
        reportPropertyMessage.setDeviceId(json.getString("imei"));
        reportPropertyMessage.getProperties().put("dipy", dataObj.getString("dipy"));
        reportPropertyMessage.getProperties().put("margin", dataObj.getString("margin"));
        reportPropertyMessage.getProperties().put("dipx", dataObj.getString("dipx"));
        reportPropertyMessage.getProperties().put("windvelocity", dataObj.getString("windvelocity"));
        reportPropertyMessage.getProperties().put("action_type", dataObj.getString("action_type"));
        reportPropertyMessage.getProperties().put("rotation", dataObj.getString("rotation"));
        reportPropertyMessage.getProperties().put("torque", dataObj.getString("torque"));
        reportPropertyMessage.getProperties().put("alarm_level", dataObj.getString("alarm_level"));
        reportPropertyMessage.getProperties().put("workcyclecode", dataObj.getString("workcyclecode"));
        reportPropertyMessage.getProperties().put("hangweight", dataObj.getString("hangweight"));
        reportPropertyMessage.getProperties().put("alarm_type", dataObj.getString("alarm_type"));
        reportPropertyMessage.getProperties().put("time", dataObj.getString("time"));
        reportPropertyMessage.getProperties().put("height", dataObj.getString("height"));
        return reportPropertyMessage;
    }
}
